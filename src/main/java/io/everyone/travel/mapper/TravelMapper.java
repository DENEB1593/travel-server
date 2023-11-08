package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.domain.Travel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelMapper {
    public static Travel of(TravelWriteRequest from) {
        return Travel.builder()
                .title(from.getTitle())
                .startAt(from.getStartAt())
                .endAt(from.getEndAt())
                .plans(from.getPlans().stream().map(PlanMapper::of).toList())
                .build();
    }

}
