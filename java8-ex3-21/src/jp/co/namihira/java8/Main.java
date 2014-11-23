/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * static メソッドである<T, U> Future<U> map(Future<T>, Function<T, U>)を提供しなさい。
 * Futureインタフェースのすべてのメソッドを実装した無名クラスのオブジェクトを返しなさい。
 * getメソッドで、関数を呼び出しなさい。
 */
/**
 * A.
 * - ログ
 * Expect : 4
 * Result : 4
 * - isDone : true
 * - isCancelled : false
 */

package jp.co.namihira.java8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main<T> {

    public static void main(String[] args) {
        // prepare
        final String str = "hoge";
        final ExecutorService executor =  Executors.newSingleThreadExecutor();
        final Future<String> f = executor.submit(() -> str);

        // action
        final Future<Integer> result = CommonUtils.map(
                f,
                (t) -> {
                    return t.length();
                });

        // check
        try {
            System.out.println("Expect : " + str.length());
            System.out.println("Result : " + result.get().intValue());
            System.out.println("- isDone : " + result.isDone());
            System.out.println("- isCancelled : " + result.isCancelled());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}