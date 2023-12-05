package io.everyone.travel.api.web.expense.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(name = "지출 작성 응답", description = "지출 작성 응답")
@Builder
public record ExpenseWriteResponse(

    @Schema(description = "지출 ID", example = "1")
    Long id,

    @Schema(description = "지출 비용", example = "10,000")
    BigDecimal amt,

    @Schema(description = "지출 일자", example = "2022-04-10T14:00:00")
    LocalDateTime spendAt,

    @Schema(description = "여행 ID", example = "1")
    Long travelId,

    @Schema(description = "지출 생성일", example = "2022-04-10T14:00:00")
    LocalDateTime createdAt,

    @Schema(description = "지출 수정일", example = "2022-04-15T15:00:00")
    LocalDateTime modifiedAt

) {

}
