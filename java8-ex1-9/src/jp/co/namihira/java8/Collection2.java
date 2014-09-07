/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {

    public default void forEachIf(Consumer<T> action, Predicate<T> filter) {
        for (T t: this) {
            if (filter.test(t)) {
                action.accept(t);
            }
        }
    }

}