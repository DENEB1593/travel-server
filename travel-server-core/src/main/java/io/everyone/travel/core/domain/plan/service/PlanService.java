package io.everyone.travel.core.domain.plan.service;

import io.everyone.travel.core.domain.plan.entity.Plan;
import io.everyone.travel.core.domain.plan.dto.UpdatePlan;
import io.everyone.travel.core.domain.plan.dto.WritePlan;
import io.everyone.travel.core.domain.plan.mapper.PlanMapper;
import io.everyone.travel.core.domain.travel.entity.Travel;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.domain.plan.repo.PlanRepository;
import io.everyone.travel.core.domain.travel.service.TravelService;
import io.everyone.travel.core.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.springframework.util.Assert.isTrue;


@Transactional
@Service
@RequiredArgsConstructor
public class PlanService {

    private final TravelService travelService;
    private final PlanRepository planRepository;

    public Plan save(WritePlan writePlan) {
        Travel travel = travelService
            .findById(writePlan.travelId())
            .orElseThrow(NotFoundException::forTravel);

        this.validateWritePlan(writePlan, travel);

        Plan plan = PlanMapper.writeRequestToPlan(writePlan);
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

    public Plan updatePlan(UpdatePlan updatePlan) {
        Plan plan = planRepository
            .findById(updatePlan.planId())
            .orElseThrow(NotFoundException::forPlan);

        plan.updateFromRequest(updatePlan.title(), updatePlan.memo());

        planRepository.save(plan);

        return plan;
    }



    public void deleteById(Long planId) {
        planRepository.findById(planId)
            .ifPresentOrElse(
                it -> planRepository.deleteById(it.getId()),
                () -> { throw NotFoundException.forPlan(); }
            );
    }


    private void validateWritePlan(WritePlan writePlan, Travel travel) {
        isTrue(writePlan.title().length() <= 200, "계획명을 200자 이하여야 합니다");
        isTrue(DateUtils.isOnOrAfter(writePlan.startAt(), writePlan.endAt()), "계획종료일자는 시작일자 이후여야 합니다");
        isTrue(
            DateUtils.isBetween(writePlan.startAt(), travel.getStartAt(), travel.getEndAt()),
            "계획시작일자는 여행 기간 내 포함되어야 합니다"
        );
        isTrue(
            DateUtils.isBetween(writePlan.endAt(), travel.getStartAt(), travel.getEndAt()),
            "계획종료일자는 여행 기간 내 포함되어야 합니다"
        );
    }


}
