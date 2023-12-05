package io.everyone.travel.core.domain.plan.service;

import io.everyone.travel.core.domain.plan.Plan;
import io.everyone.travel.core.domain.travel.Travel;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.domain.plan.repo.PlanRepository;
import io.everyone.travel.core.domain.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final TravelService travelService;
    private final PlanRepository planRepository;

    @Transactional
    public Plan save(String title, String memo, LocalDateTime startAt, LocalDateTime endAt, Long travelId) {
        Travel travel = travelService
            .findById(travelId)
            .orElseThrow(NotFoundException::forTravel);

        Plan plan = Plan.builder()
            .title(title)
            .memo(memo)
            .startAt(startAt)
            .endAt(endAt)
            .build();

        plan.setTravel(travel);

        planRepository.save(plan);

        return plan;
    }

    @Transactional(readOnly = true)
    public Set<Plan> findByTravelId(Long travelId) {
        return travelService
            .findById(travelId)
            .map(Travel::getPlans)
            .orElseThrow(NotFoundException::forTravel);
    }

    @Transactional(readOnly = true)
    public Optional<Plan> findByPlanId(Long planId) {
        return planRepository.findById(planId);
    }

    @Transactional
    public Plan updatePlan(Long planId, String title, String memo, LocalDateTime startAt, LocalDateTime endAt) {
        Plan plan = planRepository
            .findById(planId)
            .orElseThrow(NotFoundException::forPlan);

        plan.updateFromRequest(title, memo);

        planRepository.save(plan);

        return plan;
    }



    @Transactional
    public void deleteById(Long planId) {
        planRepository.findById(planId)
            .ifPresentOrElse(
                (plan) -> planRepository.deleteById(plan.getId()),
                () -> { throw NotFoundException.forPlan(); }
            );

    }

}
