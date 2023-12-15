package io.everyone.travel.core.domain.plan.mapper;

import io.everyone.travel.core.domain.plan.dto.WritePlan;
import io.everyone.travel.core.domain.plan.entity.Plan;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlanMapper {

    public Plan writeRequestToPlan(WritePlan request) {
        return Plan.builder()
            .title(request.title())
            .memo(request.memo())
            .startAt(request.startAt())
            .endAt(request.endAt())
            .build();
    }

}
