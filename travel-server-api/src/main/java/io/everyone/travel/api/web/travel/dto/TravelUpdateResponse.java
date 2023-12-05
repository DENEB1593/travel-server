package io.everyone.travel.api.web.travel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TravelUpdateResponse(

    @Schema(description = "여행 ID", example = "1")
    Long id,

    @Schema(description = "여행 시작일", type = "string", example = "2022-03-31T00:00:00")
    LocalDateTime startAt,

    @Schema(description = "여행 종료일", type = "string", example = "2022-04-10T14:00:00")
    LocalDateTime endAt,

    @Schema(description = "여행명", example = "태국 여행")
    String title,

    @Schema(description = "국가 코드", example = "TH")
    NationModel nation,

    @Schema(description = "여행 생성일", example = "2022-04-10T14:00:00")
    LocalDateTime createdAt,

    @Schema(description = "여행 수정일", example = "2022-04-15T15:00:00")
    LocalDateTime modifiedAt

) {

}
