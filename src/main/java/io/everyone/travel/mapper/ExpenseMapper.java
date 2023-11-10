package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.TravelView;
import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.domain.Expense;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpenseMapper {

    /**
     * 지출쓰기요청 -> 지출엔티티 변환
     */
    public static Expense toEntity(TravelWriteRequest.ExpenseWriteRequest from) {
        return Expense.builder()
                .amt(from.getAmt())
                .build();
    }

    /**
     * 지출엔티티 -> 지출쓰기응답 변환
     */
    public static TravelWriteResponse.ExpenseWriteResponse toResponse(Expense from) {
        return TravelWriteResponse.ExpenseWriteResponse.builder()
                .amt(from.getAmt())
                .build();
    }

    /**
     * 지출엔티티 -> 지출뷰모델 변환
     */
    public static TravelView.ExpenseView toView(Expense from) {
        return TravelView.ExpenseView.builder()
                .id(from.getId())
                .amt(from.getAmt())
                .build();
    }

}
