/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {

    /**
     * filterで指定された条件に適合する値に対して、actionを実行します。
     *
     * @exception IllegalArgumentException nullが指定された場合
     *
     * @param action 処理
     * @param filter 条件
     */
    public default void forEachIf(Consumer<T> action, Predicate<T> filter) {
        if (action == null || filter == null) {
            throw new IllegalArgumentException("args must not be null.");
        }

        for (T t: this) {
            if (filter.test(t)) {
                action.accept(t);
            }
        }
    }

}