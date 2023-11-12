package io.everyone.travel.controller.dto;

import io.everyone.travel.domain.enums.Nation;
import io.swagger.v3.oas.annotations.media.Schema;
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

    Nation nation;

    List<PlanView> plans;

    List<ExpenseView> expenses;

    @Value
    @Builder
    public static class PlanView {

        Long id;

        String title;

        String memo;

        LocalDateTime startAt;

        LocalDateTime endAt;


    }

    @Value
    @Builder
    public static class ExpenseView {

        Long id;

        BigDecimal amt;
        
    }

}
