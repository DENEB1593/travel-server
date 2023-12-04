package io.everyone.travel.api.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Schema(name = "계획 수정 응답")
@Builder
public record PlanUpdateResponse(

    @Schema(description = "계획 ID", example = "1")
    Long id,

    @Schema(description = "계획 제목", example = "망고 먹기")
    String title,

    @Schema(description = "계획 메모", example = "방콕 시장 마켓에서 구매")
    String memo,

    @Schema(description = "계획 시작일", example = "2022-04-10T14:00:00")
    LocalDateTime startAt,

    @Schema(description = "계획 종료일", example = "2022-04-11T14:00:00")
    LocalDateTime endAt,

    @Schema(description = "여행 ID", example = "1")
    Long travelId,

    @Schema(description = "계획 생성일", example = "2022-04-10T14:00:00")
    LocalDateTime createdAt,

    @Schema(description = "계획 수정일", example = "2022-04-10T14:00:00")
    LocalDateTime modifiedAt

) {

}
