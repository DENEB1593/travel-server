package io.everyone.travel.api.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TravelWriteResponse(

    Long id,

    LocalDateTime startAt,

    LocalDateTime endAt,

    String title,

    NationModel nation,

    LocalDateTime createdAt,

    LocalDateTime modifiedAt

) {

}
