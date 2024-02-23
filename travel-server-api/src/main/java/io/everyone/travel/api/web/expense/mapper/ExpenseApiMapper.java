package io.everyone.travel.api.web.expense.mapper;

import io.everyone.travel.api.web.expense.dto.*;
import io.everyone.travel.core.domain.expense.dto.UpdateExpense;
import io.everyone.travel.core.domain.expense.dto.WriteExpense;
import io.everyone.travel.core.domain.expense.entity.Expense;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExpenseApiMapper {

    /**
     * 지출쓰기요청 -> 지출엔티티 변환
     */
    public WriteExpense toWriteExpense(ExpenseWriteRequest from) {
        return WriteExpense.builder()
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

    public static UpdateExpense toUpdateExpense(ExpenseUpdateRequest request) {
        return UpdateExpense.builder()
            .amt(request.amt())
            .spendAt(request.spendAt())
            .travelId(request.travelId())
            .build();
    }

}
