package io.everyone.travel.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class TravelWriteRequest {

    @NotNull(message = "여행 시작일은 필수 값입니다")
    LocalDateTime startAt;

    @NotNull(message = "여행 종료일은 필수 값입니다")
    LocalDateTime endAt;

    @NotBlank(message = "여행 명은 필수 값입니다")
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
