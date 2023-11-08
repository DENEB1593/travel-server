package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class TravelWriteRequest {

    LocalDateTime startAt;

    LocalDateTime endAt;

    String title;

    List<PlanWriteRequest> plans; // 계획 목록

    List<ExpenseWriteRequest> expenses; // 지출 목록

    @Value
    @Builder
    public static class PlanWriteRequest {
        String content;
    }

    @Value
    @Builder
    public static class ExpenseWriteRequest {
        BigDecimal amt;
    }

}
