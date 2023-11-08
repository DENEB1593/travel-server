package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.PlanWriteRequest;
import io.everyone.travel.domain.Plan;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanMapper {

    public static Plan of(PlanWriteRequest from) {
        return Plan.builder()
                .content(from.getContent())
                .build();
    }
    
}
