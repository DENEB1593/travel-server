package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class PlanView {

    Long id;

    String title;

    String memo;

    LocalDateTime startAt;

    LocalDateTime endAt;

}
