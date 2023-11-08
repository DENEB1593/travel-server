package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class TravelWriteResponse {
    LocalDateTime startAt;

    LocalDateTime endAt;

    String title;

    List<TravelWriteResponse.PlanWriteResponse> plans; // 계획 목록

    List<TravelWriteResponse.ExpenseWriteResponse> expenses; // 지출 목록

    LocalDateTime createdAt;

    @Value
    @Builder
    public static class PlanWriteResponse {
        String content;
    }

    @Value
    @Builder
    public static class ExpenseWriteResponse {
        BigDecimal amt;
    }
}
