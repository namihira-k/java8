/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 71ページの3.8節「例外の取り扱い」のdoInOrderAsyncを実装し、
 * 2つ目のパラメータはBitConsumer<T, Throwable>としなさい。
 * うまいユースケースを示しなさい。
 * 3つ目のパラメータは必要ですか？
 */
/**
 * A.
 * - うまいユースケースを示しなさい。
 * 単語一覧を精査し、NGワードがあった場合WARNログを出力し、なかった場合はその単語をINFOログに出力する。
 *
 * - 3つ目のパラメータは必要ですか？
 * secondにて発生するThrowableに対してハンドリングをしたい場合に、3つ目のパラメータとしてハンドラーを設ける必要がある。
 *
 */

package jp.co.namihira.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(ThreadUtilsTest.class.getName());

    public static void main(String[] args) {
        excuteNormalCase();
        excuteExceptionCase();
    }

    private static void excuteNormalCase(){
        LOGGER.log(Level.INFO, "called excuteNormalCase()");
        // prepare
        final List<String> input = Arrays.asList("hoge", "foo", "test");
        final String ng = "password";
        final String errorMessage = "There are NG words in input";
        final CountDownLatch latch = new CountDownLatch(1);

        // action
        ThreadUtils.doInOrderAsync(
                () -> {
                    final long count = input.stream().filter((i) -> i.equals(ng)).count();
                    if (count != 0) {
                        throw new IllegalArgumentException(errorMessage);
                    }
                    return input;
                },
                (words, t) -> {
                    try {
                        if (t != null) {
                            LOGGER.log(Level.WARNING, t.getMessage());
                            return;
                        }
                        LOGGER.log(Level.INFO, words.stream().collect(Collectors.joining(",")));
                    } finally {
                        latch.countDown();
                    }
                }
         );

        // check
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // - checked standard output;
    }

    private static void excuteExceptionCase(){
        LOGGER.log(Level.INFO, "called excuteExceptionCase()");
        // prepare
        final List<String> input = Arrays.asList("hoge", "foo", "password");
        final String ng = "password";
        final String errorMessage = "There are NG words in input";

        // action
        ThreadUtils.doInOrderAsync(
                () -> {
                    final long count = input.stream().filter((i) -> i.equals(ng)).count();
                    if (count != 0) {
                        throw new IllegalArgumentException(errorMessage);
                    }
                    return input;
                },
                (words, t) -> {
                    if (t != null) {
                        LOGGER.log(Level.WARNING, t.getMessage());
                        return;
                    }
                    LOGGER.log(Level.INFO, words.stream().collect(Collectors.joining(",")));
                }
         );

        // check
        // - checked standard output;
    }

}