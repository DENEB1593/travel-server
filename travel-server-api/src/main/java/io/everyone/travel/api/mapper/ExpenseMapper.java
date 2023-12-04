package io.everyone.travel.api.mapper;

import io.everyone.travel.api.controller.dto.ExpenseUpdateResponse;
import io.everyone.travel.api.controller.dto.ExpenseView;
import io.everyone.travel.api.controller.dto.ExpenseWriteRequest;
import io.everyone.travel.api.controller.dto.ExpenseWriteResponse;
import io.everyone.travel.core.domain.Expense;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExpenseMapper {

    /**
     * 지출쓰기요청 -> 지출엔티티 변환
     */
    public Expense toEntity(ExpenseWriteRequest from) {
        return Expense.builder()
            .amt(from.amt())
            .spendAt(from.spendAt())
            .build();
    }

    /**
     * 지출엔티티 -> 지출쓰기응답 변환
     */
    public ExpenseWriteResponse toWriteResponse(Expense from) {
        return ExpenseWriteResponse.builder()
            .id(from.getId())
            .amt(from.getAmt())
            .spendAt(from.getSpendAt())
            .travelId(from.getId())
            .createdAt(from.getCreatedAt())
            .modifiedAt(from.getModifiedAt())
            .build();
    }

    /**
     * 지출엔티티 -> 지출수정응답 변환
     */
    public ExpenseUpdateResponse toUpdateResponse(Expense from) {
        return ExpenseUpdateResponse.builder()
            .id(from.getId())
            .amt(from.getAmt())
            .spendAt(from.getSpendAt())
            .travelId(from.getId())
            .createdAt(from.getCreatedAt())
            .modifiedAt(from.getModifiedAt())
            .build();
    }

    /**
     * 지출엔티티 -> 지출뷰모델 변환
     */
    public static ExpenseView toView(Expense from) {
        return ExpenseView.builder()
            .id(from.getId())
            .amt(from.getAmt())
            .spendAt(from.getSpendAt())
            .travelId(from.getId())
            .createdAt(from.getCreatedAt())
            .modifiedAt(from.getModifiedAt())
            .build();
    }

}
