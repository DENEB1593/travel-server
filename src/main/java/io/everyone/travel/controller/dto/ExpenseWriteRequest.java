package io.everyone.travel.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

@Schema(name = "지출 작성 요청", description = "지출 작성을 요청한다")
public record ExpenseWriteRequest(
    @Schema(description = "지출 비용", example = "4500")
    @NotNull(message = "지출 비용은 필수 값입니다")
    BigDecimal amt,

    @Schema(description = "여행 ID", example = "1")
    @NotNull(message = "여행 아이디는 필수 값입니다")
    Long travelId
) {

}
