/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 1,000個のスレッドを生成し、各スレッドは、ある1つのカウンターを100,000回だけ
 * 1つずつ増加させます。AtomicLongとLongAdderを使用した場合の性能を比較しなさい。
 */
/**
 * A.
 * 測定値（ログ）：
 * 100000000
 * run With AtomicLong : PT6.641S
 * 100000000
 * run With LongAdder : PT1.421S
 *
 * 動作環境：
 *  - OS : windows 8.1
 *  - CPU : Intel(R) Core(TM) i5-3317U CPU @ 1.70GHz 1.70 GHz
 *  - 実装メモリ（RAM） : 4.00 GB
 *  - 64ビット オペレーション システム、x64 ベース プロセッサ
 */

package jp.co.namihira.java8;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    final static int NUM_THREADS = 1_000;
    final static int NUM_COUNTS = 100_000;

    public static void main(String[] args) {
        Duration result = measureRunTime(() -> runWithAtomicLong());
        System.out.println("run With AtomicLong : " + result);

        result = measureRunTime(() -> runWithLongAdder());
        System.out.println("run With LongAdder : " + result);
    }

    private static Duration measureRunTime(final Runnable function){
        final Instant start = Instant.now();
        function.run();
        final Instant end = Instant.now();
        return Duration.between(start, end);
    }

    private static void runWithAtomicLong(){
        // prepare
        final AtomicLong counter = new AtomicLong(0);
        final ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        final CountDownLatch latch = new CountDownLatch(NUM_THREADS);

        // action
        for (int i = 0; i < NUM_THREADS; i++) {
            pool.submit(() -> {
                for (int j = 0; j < NUM_COUNTS; j++) {
                    counter.incrementAndGet();
                }
                latch.countDown();
            });
        };

        // check
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        System.out.println(counter.get());
    }

    private static void runWithLongAdder(){
        // prepare
        final LongAdder adder = new LongAdder();
        final ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        final CountDownLatch latch = new CountDownLatch(NUM_THREADS);

        // action
        for (int i = 0; i < NUM_THREADS; i++) {
            pool.submit(() -> {
                for (int j = 0; j < NUM_COUNTS; j++) {
                    adder.increment();
                }
                latch.countDown();
            });
        };

        // check
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        System.out.println(adder.sum());
    }

}
