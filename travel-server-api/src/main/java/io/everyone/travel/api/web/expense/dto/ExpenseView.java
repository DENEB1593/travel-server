package io.everyone.travel.api.web.expense.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class ExpenseView {

    Long id;

    BigDecimal amt;

    LocalDateTime spendAt;

    Long travelId;

    LocalDateTime createdAt;

    LocalDateTime modifiedAt;

}
