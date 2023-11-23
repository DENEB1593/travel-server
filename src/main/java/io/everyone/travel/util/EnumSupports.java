package io.everyone.travel.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.EnumSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumSupports {

    public static <E extends Enum<E>> E byEnumName(Class<E> enumClz, String name) {

        if (name == null || name.isEmpty()) {
            return null;
        }

        return EnumSet.allOf(enumClz).stream()
            .filter(e -> e.name().equals(name))
            .findFirst()
            .orElse(null);
    }

}
