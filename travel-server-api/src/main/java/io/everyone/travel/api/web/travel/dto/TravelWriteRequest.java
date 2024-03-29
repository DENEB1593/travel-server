package io.everyone.travel.api.web.travel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Schema(description = "여행 정보 작성")
public record TravelWriteRequest(

    @Schema(description = "여행 시작일", type = "string", example = "2022-03-31T00:00:00")
    @NotNull(message = "여행 시작일은 필수 값입니다")
    LocalDateTime startAt,

    @Schema(description = "여행 종료일", type = "string", example = "2022-04-10T14:00:00")
    @NotNull(message = "여행 종료일은 필수 값입니다")
    LocalDateTime endAt,

    @Schema(description = "여행명", example = "태국 여행")
    @NotBlank(message = "여행 명은 필수 값입니다")
    String title,

    @Schema(description = "국가 코드", example = "TH")
    @NotBlank(message = "국가 코드는 필수 값입니다")
    String nation
) {

}
