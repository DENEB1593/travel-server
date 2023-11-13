package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ExpenseWriteResponse {

    BigDecimal amt;

}
