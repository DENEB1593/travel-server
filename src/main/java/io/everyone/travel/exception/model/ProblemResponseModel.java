package io.everyone.travel.exception.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

/**
 * RFC 7807 기반 모델
 *
 */
@Schema(name = "오류 모델", description = "RFC 7807 기반 오류 모델을 표현")
public record ProblemResponseModel(
    @Schema(description = "오류명", example = "NOT_FOUND")
    String title,
    @Schema(description = "HTTP 상태 코드", example = "404")
    int status,
    @Schema(description = "오류내용", example = "코드값은 필수 입니다")
    String detail
) {

}
