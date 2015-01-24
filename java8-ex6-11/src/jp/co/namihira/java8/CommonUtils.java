/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CommonUtils {

    /**
     * 指定したuntil関数が受け入れる値を生成するまで、action関数を非同期に繰り返します。
     *
     * @param action　処理
     * @param until　終了条件
     * @return　フューチャー
     *
     * @throws NullPointerException 引数がnullの場合
     */
    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until){
        Objects.requireNonNull(action);
        Objects.requireNonNull(until);

        return CompletableFuture
                .supplyAsync(action)
                .thenComposeAsync((t) -> {
                    return until.test(t) ? CompletableFuture.completedFuture(t) : repeat(action, until);
                });
    }

}
