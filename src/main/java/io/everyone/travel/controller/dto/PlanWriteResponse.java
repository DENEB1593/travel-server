package io.everyone.travel.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlanWriteResponse(

    Long id,

    String title,

    String memo,

    LocalDateTime startAt,

    LocalDateTime endAt,

    Long travelId,

    LocalDateTime createdAt,

    LocalDateTime modifiedAt

) {

}
