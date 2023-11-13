package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ExpenseView {

    Long id;

    BigDecimal amt;

}
