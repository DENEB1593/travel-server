package io.everyone.travel.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(name = "계획 수정 요청", description = "계획 수정을 요청한다")
public record PlanUpdateRequest(
    @Schema(description = "계획 제목", example = "망고 먹기")
    @NotBlank(message = "계획 제목을 필수 값입니다")
    String title,
    @Schema(description = "계획 메모", example = "방콕 시장 마켓에서 구매")
    String memo,
    @Schema(description = "계획 시작일", example = "2022-04-10T14:00:00")
    @NotNull(message = "계획 시작일은 필수 값입니다")
    LocalDateTime startAt,
    @Schema(description = "계획 종료일", example = "2022-04-11T14:00:00")
    @NotNull(message = "계획 종료일은 필수 값입니다")
    LocalDateTime endAt
) {
}
