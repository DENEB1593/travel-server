package io.everyone.travel.core.domain.plan.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record WritePlan(
    String title,
    String memo,
    LocalDateTime startAt,
    LocalDateTime endAt,
    Long travelId
) {
}
