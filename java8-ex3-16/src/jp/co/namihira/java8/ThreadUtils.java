/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ThreadUtils {

    /**
     * 指定された処理を非同期に順次実行します。
     *
     * @param first 最初に実行する処理
     * @param second 最初の処理結果または発生した例外に対する処理
     */
    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second){
        if (first == null || second == null) {
            throw new IllegalArgumentException("args must not be null");
        }

        Thread t = new Thread() {
            public void run() {
                try {
                    T result = first.get();
                    second.accept(result, null);
                } catch (Throwable t) {
                    second.accept(null, t);
                }
            }
        };
        t.start();
    }

    /**
     * 指定された処理を非同期に順次実行します。
     *
     * @param first 最初に実行する処理
     * @param second 最初の処理結果に対する処理
     * @param handler　処理中に発生したThrowableに対する処理ハンドラ
     */
    public static <T> void doInOrderAsync(Supplier<T> first, Consumer<T> second, Consumer<Throwable> handler){
        Thread t = new Thread() {
            public void run() {
                try {
                    T result = first.get();
                    second.accept(result);
                } catch (Throwable t) {
                    handler.accept(t);
                }
            }
        };
        t.start();
    }

}