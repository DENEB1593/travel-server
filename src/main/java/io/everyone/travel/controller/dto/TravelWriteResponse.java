package io.everyone.travel.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.everyone.travel.domain.enums.Nation;
import io.swagger.v3.oas.annotations.media.Schema;
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

    Nation nation;
    
    LocalDateTime createdAt;

    List<TravelWriteResponse.PlanWriteResponse> plans; // 계획 목록

    List<TravelWriteResponse.ExpenseWriteResponse> expenses; // 지출 목록


    @Value
    @Builder
    public static class PlanWriteResponse {

        String title;

        String memo;

        LocalDateTime startAt;

        LocalDateTime endAt;

    }

    @Value
    @Builder
    public static class ExpenseWriteResponse {

        BigDecimal amt;

    }
}
