package io.everyone.travel.core.domain.travel.dto;

import io.everyone.travel.core.domain.travel.Nation;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
public record WriteTravel(
    LocalDateTime startAt,
    LocalDateTime endAt,
    String title,
    String nation,
    MultipartFile thumbnail
) {
}
