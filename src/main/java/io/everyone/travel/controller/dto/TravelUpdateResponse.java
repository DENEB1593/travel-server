package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class TravelUpdateResponse {

    Long id;

    LocalDateTime startAt;

    LocalDateTime endAt;

    String title;

    NationModel nation;
    
    LocalDateTime createdAt;

    LocalDateTime modifiedAt;

}
