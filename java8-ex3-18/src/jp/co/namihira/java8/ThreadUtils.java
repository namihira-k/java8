/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.function.Function;

public class ThreadUtils {

    /**
     * 指定された処理についてのFunctionを作成します。
     * チェックされる例外は無視され、RuntimeExcetionとしてスローされます。
     *
     * @param f　処理
     * @return　Function
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     */
    public static <V, U> Function<V, U> unchecked(ExCallable<V, U> f) {
        if (f == null) {
            throw new IllegalArgumentException("f must not be null");
        }

        return (v) -> {
            try {
                return f.call(v);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            catch (Throwable t) {
                throw t;
            }
        };
    }

}