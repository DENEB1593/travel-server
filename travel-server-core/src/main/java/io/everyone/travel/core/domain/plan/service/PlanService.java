package io.everyone.travel.core.domain.plan.service;

import io.everyone.travel.core.domain.plan.Plan;
import io.everyone.travel.core.domain.plan.dto.UpdatePlan;
import io.everyone.travel.core.domain.plan.dto.WritePlan;
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
    public Plan save(WritePlan writePlan) {
        Travel travel = travelService
            .findById(writePlan.travelId())
            .orElseThrow(NotFoundException::forTravel);

        Plan plan = Plan.builder()
            .title(writePlan.title())
            .memo(writePlan.memo())
            .startAt(writePlan.startAt())
            .endAt(writePlan.endAt())
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
    public Plan updatePlan(UpdatePlan updatePlan) {
        Plan plan = planRepository
            .findById(updatePlan.planId())
            .orElseThrow(NotFoundException::forPlan);

        plan.updateFromRequest(updatePlan.title(),updatePlan.memo());

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
