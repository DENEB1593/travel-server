package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.domain.Travel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelMapper {
    public static Travel toEntity(TravelWriteRequest from) {
        return Travel.builder()
                .title(from.getTitle())
                .startAt(from.getStartAt())
                .endAt(from.getEndAt())
                .plans(from.getPlans().stream().map(PlanMapper::toEntity).toList())
                .expenses(from.getExpenses().stream().map(ExpenseMapper::toEntity).toList())
                .build();
    }

    public static TravelWriteResponse toResponse(Travel from) {
        return TravelWriteResponse.builder()
                .title(from.getTitle())
                .startAt(from.getStartAt())
                .endAt(from.getEndAt())
                .createdAt(from.getCreatedAt())
                // plans
                .plans(from.getPlans().stream().map(PlanMapper::toResponse).toList())
                // expenses
                .expenses(from.getExpenses().stream().map(ExpenseMapper::toResponse).toList())
                .build();
    }

}
