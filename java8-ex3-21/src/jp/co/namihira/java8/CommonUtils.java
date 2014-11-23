/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class CommonUtils {

    /**
     * 指定されたfutureの要素に対して処理を追加した新規のFutureを返却します。
     *
     * @param future 処理対象
     * @param function 処理内容
     * @return 処理が追加された新規のFuture
     *
     * @throws IllegalArgumentException 引数のいずれかがnullの場合。
     */
    public static <T, U> Future<U> map(Future<T> future, Function<T, U> function){
        if (future == null || function == null) {
            throw new IllegalArgumentException("args must not be null");
        }

        return new Future<U>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return future.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return function.apply(future.get());
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return function.apply(future.get(timeout, unit));
            }
        };
    }
}