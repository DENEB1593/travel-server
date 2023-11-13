package io.everyone.travel.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.EnumSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumUtil {

    public static <E extends Enum<E>> E byEnumName(Class<E> enumClz, String name) {

        if (ObjectUtils.isEmpty(name)) {
            return null;
        }

        return EnumSet.allOf(enumClz).stream()
            .filter(e -> e.name().equals(name))
            .findFirst()
            .orElse(null);
    }

}
