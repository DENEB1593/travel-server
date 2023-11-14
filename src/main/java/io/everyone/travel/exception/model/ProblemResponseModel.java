package io.everyone.travel.exception.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

/**
 * Spring Problem 기반 모델
 */
@Schema(name = "오류 모델", description = "RFC 7807 기반 오류 모델을 표현")
@Value
@Builder
public class ProblemResponseModel {

    @Schema(description = "오류명", example = "NOT_FOUND")
    String title;   //오류 제목

    @Schema(description = "HTTP 상태 코드", example = "404")
    int status;     //상태 코드

    @Schema(description = "오류내용", example = "코드값은 필수 입니다")
    String detail;  //오류 메세지

}
