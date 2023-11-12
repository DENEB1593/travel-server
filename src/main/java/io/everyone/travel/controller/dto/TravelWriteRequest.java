package io.everyone.travel.controller.dto;

import io.everyone.travel.domain.enums.Nation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "여행 정보 작성")
@Value
@Builder
public class TravelWriteRequest {

    @Schema(description = "여행 시작일", example = "2023-11-10T12:27:21.042Z")
    @NotNull(message = "여행 시작일은 필수 값입니다")
    LocalDateTime startAt;

    @Schema(description = "여행 종료일", example = "2023-11-17T10:00:00.00Z")
    @NotNull(message = "여행 종료일은 필수 값입니다")
    LocalDateTime endAt;

    @Schema(description = "여행명", example = "태국 여행")
    @NotBlank(message = "여행 명은 필수 값입니다")
    String title;

    @Schema(description = "국가 코드", example = "TH")
    @NotBlank(message = "국가 코드는 필수 값입니다")
    String nation;

    @Schema(description = "계획 목록")
    List<PlanWriteRequest> plans;

    @Schema(description = "지출 목록")
    List<ExpenseWriteRequest> expenses;

    @Value
    @Builder
    public static class PlanWriteRequest {

        @Schema(description = "계획 제목", example = "망고 먹기")
        String title;

        @Schema(description = "계획 메모", example = "방콕 시장 마켓에서 구매")
        String memo;

        @Schema(description = "계획 시작일", example = "2023-11-17T10:00:00.00Z")
        LocalDateTime startAt;

        @Schema(description = "계획 종료일", example = "2023-11-23T10:00:00.00Z")
         LocalDateTime endAt;

    }

    @Value
    @Builder
    public static class ExpenseWriteRequest {

        @Schema(description = "지출 비용", example = "4500")
        BigDecimal amt;

    }

}
