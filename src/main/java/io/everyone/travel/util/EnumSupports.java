package io.everyone.travel.util;

import java.util.EnumSet;

public final class EnumSupports {

    private EnumSupports() { }

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
