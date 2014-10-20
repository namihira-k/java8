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
        Runnable runner = ThreadUtils.andThen(
                    () -> System.out.println("1st Runnable start"),
                    () -> System.out.println("2st Runnable start")
                );

        // check
        new Thread(runner).start();
        // -> standard output
        //   1st Runnable start
        //   2st Runnable start
        //    or
        //   2st Runnable start
        //   1st Runnable start
    }

}