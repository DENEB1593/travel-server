package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class TravelView {

    Long id;

    LocalDateTime startAt;

    LocalDateTime endAt;

    String title;

    List<PlanView> plans; // 계획 목록

    List<ExpenseView> expenses; // 지출 목록

    @Value
    @Builder
    public static class PlanView {

        Long id;

        String content;

    }

    @Value
    @Builder
    public static class ExpenseView {

        Long id;

        BigDecimal amt;
        
    }

}
