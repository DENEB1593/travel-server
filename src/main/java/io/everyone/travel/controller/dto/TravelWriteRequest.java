package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class TravelWriteRequest {

    LocalDateTime startAt;

    LocalDateTime endAt;

    String title;

    List<PlanWriteRequest> plans; // 계획

}
