package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class ExpenseWriteResponse {

    Long id;

    BigDecimal amt;

    LocalDateTime createdAt;

    LocalDateTime modifiedAt;

}
