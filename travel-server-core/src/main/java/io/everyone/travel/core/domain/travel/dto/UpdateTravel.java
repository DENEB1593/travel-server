package io.everyone.travel.core.domain.travel.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
public record UpdateTravel(
    Long travelId,
    LocalDateTime startAt,
    LocalDateTime endAt,
    String title,
    String nation,
    MultipartFile thumbnail
) {
}
