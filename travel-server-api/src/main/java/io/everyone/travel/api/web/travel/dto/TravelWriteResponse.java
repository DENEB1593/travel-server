package io.everyone.travel.api.web.travel.dto;

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
