package io.everyone.travel.core.util;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

@UtilityClass
public class DateUtils {

    /**
     * val1이 val2보다 이후 인지(당시 포함) 확인한다.
     * ex)
     *  target이 10월 10일 14:00 인경우
     *  src가 10월 10일 14:00 인경우
     *  target은 true이다.
     */
    public boolean isOnOrAfter(@Nonnull LocalDateTime src, @Nonnull LocalDateTime target) {
        return target.isAfter(src) || target.isEqual(src);
    }

}
