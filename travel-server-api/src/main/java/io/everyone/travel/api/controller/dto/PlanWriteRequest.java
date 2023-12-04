package io.everyone.travel.api.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(name = "계획 작성 요청", description = "계획 작성을 요청한다")
public record PlanWriteRequest(
    @Schema(description = "계획 제목", example = "망고 먹기🥭🥭")
    @NotBlank(message = "계획 제목을 필수 값입니다")
    String title,
    @Schema(description = "계획 메모", example = "방콕 시장 마켓에서 구매")
    String memo,
    @Schema(description = "계획 시작일", example = "2022-04-10T14:00:00")
    @NotNull(message = "계획 시작일은 필수 값입니다")
    LocalDateTime startAt,
    @Schema(description = "계획 종료일", example = "2022-04-11T14:00:00")
    @NotNull(message = "계획 종료일은 필수 값입니다")
    LocalDateTime endAt,

    @Schema(description = "여행 ID", example = "1")
    @NotNull(message = "여행 아이디는 필수 값입니다")
    Long travelId
) {

}
