package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class PlanWriteResponse {

    String title;

    String memo;

    LocalDateTime startAt;

    LocalDateTime endAt;

}
