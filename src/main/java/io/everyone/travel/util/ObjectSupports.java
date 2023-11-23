package io.everyone.travel.util;

import java.util.Collection;
import java.util.function.Function;

public final class ObjectSupports {

    private ObjectSupports() { }

    public static <T, R> R ifNotNull(T target, Function<T, R> then) {

        if (target == null) {
            return null;
        }

        if (target instanceof CharSequence && ((CharSequence) target).isEmpty()) {
            return null;
        }

        if (target instanceof Collection<?> && ((Collection<?>) target).isEmpty()) {
            return null;
        }

        return then.apply(target);
    }

}
