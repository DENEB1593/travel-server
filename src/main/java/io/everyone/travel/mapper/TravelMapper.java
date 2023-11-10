package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.domain.Expense;
import io.everyone.travel.domain.Plan;
import io.everyone.travel.domain.Travel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelMapper {
    public static Travel toEntity(TravelWriteRequest from) {

        Travel entity = Travel.builder()
                .title(from.getTitle())
                .startAt(from.getStartAt())
                .endAt(from.getEndAt())
                .build();

        // 계획 정보를 저장 한다.
        List<Plan> plans = from.getPlans().stream().map(PlanMapper::toEntity).toList();
        entity.setPlans(plans);

        // 지출 정보를 저장 한다.
        List<Expense> expenses = from.getExpenses().stream().map(ExpenseMapper::toEntity).toList();
        entity.setExpenses(expenses);

        return entity;
    }

    public static TravelWriteResponse toResponse(Travel from) {
        return TravelWriteResponse.builder()
                .title(from.getTitle())
                .startAt(from.getStartAt())
                .endAt(from.getEndAt())
                .createdAt(from.getCreatedAt())
                .plans(from.getPlans().stream().map(PlanMapper::toResponse).toList())
                .expenses(from.getExpenses().stream().map(ExpenseMapper::toResponse).toList())
                .build();
    }

}
