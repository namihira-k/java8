/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * CompletableFutureに対するflatMap操作は存在しますか？
 * 存在するなら、それは何ですか？
 */
/**
 * A.
 * - CompletableFutureに対するflatMap操作は存在しますか？
 * 存在する。
 *
 * - 存在するなら、それは何ですか？
 * 以下の関数を利用することで、最初に得られた結果に対する関数の適用（T -> G<U>）が実現できる。
 * <U> CompletableFuture<U> thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
 * <U> CompletableFuture<U> thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn)
 *
 * 参考：https://docs.oracle.com/javase/jp/8/api/java/util/concurrent/CompletableFuture.html
 *
 */

package jp.co.namihira.java8;

public class Main<T> {

    public static void main(String[] args) {
        // prepar
        // - nothing

        // action
        // - nothing

        // check
        // - nothing
    }

}