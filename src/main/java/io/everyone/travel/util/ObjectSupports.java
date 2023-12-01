package io.everyone.travel.util;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.function.Function;

@UtilityClass
public final class ObjectSupports {

    public <T, R> R ifNotNull(T target, Function<T, R> then) {

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
