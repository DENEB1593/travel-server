package io.everyone.travel.core.domain.plan.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UpdatePlan(
    Long planId,
    String title,
    String memo,
    LocalDateTime startAt,
    LocalDateTime endAt
) {
}
