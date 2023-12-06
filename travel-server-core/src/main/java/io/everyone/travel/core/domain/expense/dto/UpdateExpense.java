package io.everyone.travel.core.domain.expense.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record UpdateExpense(
    BigDecimal amt,

    LocalDateTime spendAt,

    Long travelId
) {
}
