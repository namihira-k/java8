/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.function.Consumer;

public class ThreadUtils {

    /**
     * 指定された処理を並列で実行します。どちらかのメソッドが例外をスローしたらhandlerが呼び出されます。
     *
     * @param first 処理
     * @param second　処理
     * @param handler　どちらかのメソッドが例外をスローしたら呼び出されるhandler
     *
     * @throws IllegalArgumentException いずれからの引数がnullの場合
     *
     */
    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        if (first == null || second == null || handler == null) {
            throw new IllegalArgumentException("args must not be null");
        }

        new Thread(() -> {
            try {
                first.run();
            } catch (Throwable t) {
                synchronized(handler) {
                    handler.accept(t);
                }
            }
        }).start();

        new Thread(() -> {
            try {
                second.run();
            } catch (Throwable t) {
                synchronized(handler) {
                    handler.accept(t);
                }
            }
        }).start();
    }

}