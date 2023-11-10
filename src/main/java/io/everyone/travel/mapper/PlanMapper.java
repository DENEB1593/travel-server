package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.TravelView;
import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.domain.Plan;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanMapper {

    public static Plan toEntity(TravelWriteRequest.PlanWriteRequest from) {
        return Plan.builder()
                .content(from.getContent())
                .build();
    }

    public static TravelWriteResponse.PlanWriteResponse toResponse(Plan from) {
        return TravelWriteResponse.PlanWriteResponse.builder()
                .content(from.getContent())
                .build();
    }

    public static TravelView.PlanView toView(Plan from) {
        return TravelView.PlanView.builder()
                .id(from.getId())
                .content(from.getContent())
                .build();
    }
}
