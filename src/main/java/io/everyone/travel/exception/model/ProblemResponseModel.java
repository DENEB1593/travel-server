package io.everyone.travel.exception.model;

import lombok.Builder;
import lombok.Value;

/**
 * Spring Problem 기반 모델
 */
@Value
@Builder
public class ProblemResponseModel {

    String title;   //오류 제목

    int status;     //상태 코드

    String detail;  //오류 메세지

}
