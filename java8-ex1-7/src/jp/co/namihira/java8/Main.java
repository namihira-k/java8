/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 2つのRunnableインスタンスをパラメータとして受け取り、最初のRunnableを実行した後に
 * 2つ目のRunnableを実行するRunnableを返すように、staticメソッド andThenを書きなさい。
 * mainメソッドでは、andThenへの呼び出しに2つのラムダ式を渡して、返されたインスタンスを実行しなさい。
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        Runnable runner = andThen(
                    () -> System.out.println("1st Runnable run() called"),
                    () -> System.out.println("2st Runnable run() called")
                );

        // check
        runner.run();
    }

    private static Runnable andThen(Runnable first, Runnable second) {
        Runnable runner = () -> {
            first.run();
            second.run();
        };
        return runner;
    }

}