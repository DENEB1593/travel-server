package io.everyone.travel.api.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(name = "지출 수정 요청", description = "지출 수정을 요청한다")
public record ExpenseUpdateRequest(
    @Schema(description = "지출 비용", example = "4500")
    @NotNull(message = "지출 비용은 필수 값입니다")
    BigDecimal amt,

    @Schema(description = "지출 일자", example = "2022-04-10T14:00:00")
    @NotNull(message = "지출 일자는 필수 값입니다")
    LocalDateTime spendAt,

    @Schema(description = "여행 ID", example = "1")
    @NotNull(message = "여행 아이디는 필수 값입니다")
    Long travelId
) {

}
