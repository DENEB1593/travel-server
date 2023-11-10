package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.TravelView;
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


    /**
     * 여행쓰기요청 -> 여행엔티티 변환
     */
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

    /**
     * 여행엔티티 -> 여행작성응답 변환
     */
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

    /**
     * 여행엔티티 -> 여행뷰모델 변환
     */
    public static TravelView toView(Travel from) {
        return TravelView.builder()
                .id(from.getId())
                .title(from.getTitle())
                .startAt(from.getStartAt())
                .endAt(from.getEndAt())
                .plans(from.getPlans().stream().map(PlanMapper::toView).toList())
                .expenses(from.getExpenses().stream().map(ExpenseMapper::toView).toList())
                .build();
    }

}
