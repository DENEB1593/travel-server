package io.everyone.travel.service;

import io.everyone.travel.controller.dto.PlanUpdateRequest;
import io.everyone.travel.controller.dto.PlanWriteRequest;
import io.everyone.travel.domain.Plan;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.exception.NotFoundException;
import io.everyone.travel.mapper.PlanMapper;
import io.everyone.travel.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final TravelService travelService;
    private final PlanRepository planRepository;

    @Transactional
    public Plan save(PlanWriteRequest request) {
        Travel travel = travelService
            .findById(request.travelId())
            .orElseThrow(() -> new NotFoundException("여행 정보가 조회되지 않습니다"));

        Plan plan = PlanMapper.toEntity(request);
        plan.setTravel(travel);

        planRepository.save(plan);

        return plan;
    }

    @Transactional(readOnly = true)
    public Set<Plan> findByTravelId(Long travelId) {
        return travelService
            .findById(travelId)
            .map(Travel::getPlans)
            .orElseThrow(() -> new NotFoundException(String.format("travel not found with id [%d]", travelId)));
    }

    @Transactional
    public Optional<Plan> findByPlanId(Long planId) {
        return planRepository.findById(planId);
    }

    @Transactional
    public Plan updatePlan(Long planId, PlanUpdateRequest request) {
        Plan plan = planRepository
            .findById(planId)
            .orElseThrow(() -> new NotFoundException("계획 정보가 조회되지 않습니다"));

        plan.updateFromRequest(request);

        planRepository.save(plan);

        return plan;
    }



    @Transactional
    public void deleteById(Long planId) {
        planRepository.findById(planId)
            .ifPresentOrElse(
                (plan) ->
                    planRepository.deleteById(plan.getId()),
                () -> {
                    throw new NotFoundException("계획 정보가 조회되지 않습니다");
                }
            );

    }

}
