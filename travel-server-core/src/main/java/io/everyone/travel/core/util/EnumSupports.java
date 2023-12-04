package io.everyone.travel.core.util;

import lombok.experimental.UtilityClass;

import java.util.EnumSet;

@UtilityClass
public class EnumSupports {
    public <E extends Enum<E>> E byEnumName(Class<E> enumClz, String name) {

        if (name == null || name.isEmpty()) {
            return null;
        }

        return EnumSet.allOf(enumClz).stream()
            .filter(e -> e.name().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

}
