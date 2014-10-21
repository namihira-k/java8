/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

/**
 * スレッドに関するユーティリティクラス
 */
public class ThreadUtils {

    /**
     * 第1引数のRunnableを実行した後に第2引数のRunnableを実行するRunnableを返却します。
     * @param first 最初に実行する処理
     * @param second　2番目に実行する処理
     * @return
     */
    public static Runnable andThen(Runnable first, Runnable second) {
        Runnable runner = () -> {
            first.run();
            second.run();
        };
        return runner;
    }

}