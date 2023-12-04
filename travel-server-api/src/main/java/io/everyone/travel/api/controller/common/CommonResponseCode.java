package io.everyone.travel.api.controller.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseCode {

    OK("0000", "ok"),

    ERROR("9999", "error")
    ;


    private final String code;

    private final String defaultMessage;

}
