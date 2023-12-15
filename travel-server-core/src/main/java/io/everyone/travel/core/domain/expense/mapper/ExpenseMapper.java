package io.everyone.travel.core.domain.expense.mapper;

import io.everyone.travel.core.domain.expense.dto.WriteExpense;
import io.everyone.travel.core.domain.expense.entity.Expense;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExpenseMapper {

    public Expense writeRequestToExpense(WriteExpense request) {
        return Expense.builder()
            .amt(request.amt())
            .spendAt(request.spendAt())
            .build();
    }
}
