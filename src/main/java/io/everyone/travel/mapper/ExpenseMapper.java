package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.TravelView;
import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.domain.Expense;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpenseMapper {

    public static Expense toEntity(TravelWriteRequest.ExpenseWriteRequest from) {
        return Expense.builder()
                .amt(from.getAmt())
                .build();
    }

    public static TravelWriteResponse.ExpenseWriteResponse toResponse(Expense from) {
        return TravelWriteResponse.ExpenseWriteResponse.builder()
                .amt(from.getAmt())
                .build();
    }

    public static TravelView.ExpenseView toView(Expense from) {
        return TravelView.ExpenseView.builder()
                .amt(from.getAmt())
                .build();
    }
}
