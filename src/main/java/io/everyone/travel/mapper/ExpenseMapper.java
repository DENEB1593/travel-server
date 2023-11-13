package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.*;
import io.everyone.travel.domain.Expense;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpenseMapper {

    /**
     * 지출쓰기요청 -> 지출엔티티 변환
     */
    public static Expense toEntity(ExpenseWriteRequest from) {
        return Expense.builder()
                .amt(from.getAmt())
                .build();
    }

    /**
     * 지출엔티티 -> 지출쓰기응답 변환
     */
    public static ExpenseWriteResponse toResponse(Expense from) {
        return ExpenseWriteResponse.builder()
                .amt(from.getAmt())
                .build();
    }

    /**
     * 지출엔티티 -> 지출뷰모델 변환
     */
    public static ExpenseView toView(Expense from) {
        return ExpenseView.builder()
                .id(from.getId())
                .amt(from.getAmt())
                .build();
    }

}
