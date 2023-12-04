package io.everyone.travel.api.controller.common;

import lombok.Value;
import org.springframework.util.ObjectUtils;

@SuppressWarnings("rawtypes")
@Value
public class CommonResponse<T> {

    String code;

    String message;

    T data;

    private CommonResponse(CommonResponseCode code, String message, T data) {
        this.code = code.getCode();
        this.message = ObjectUtils.isEmpty(message) ? code.getDefaultMessage() : message;
        this.data = data;
    }

    public static <T> CommonResponse<T> OK(String message, T data) {
        return new CommonResponse<T>(CommonResponseCode.OK, message, data);
    }

    public static <T> CommonResponse<T> ERROR(String message, T data) {
        return new CommonResponse<T>(CommonResponseCode.ERROR, message, data);
    }


    /*------------- OVER LOADING START -------------*/
    public static <T> CommonResponse <T> OK(T data) {
        return OK(null, data);
    }

    public static CommonResponse OK(String message) {
        return OK(message, null);
    }

    public static CommonResponse ERROR(String message) {
        return ERROR(message, null);
    }

        public static <T> CommonResponse <T> ERROR(T data) {
        return ERROR(null, data);
    }
    /*------------- OVER LOADING END -------------*/


}
