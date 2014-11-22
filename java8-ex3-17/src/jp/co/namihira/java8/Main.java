/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * first と second を並列に実行し、どちらかのメソッドが例外をスローしたらhandlerを呼び出す
 * doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler)
 * を実行しなさい。
 */
/**
 * A.
 * - log
 * Expect :
 * [first, second]
 * Result :
 * [first, second]
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        // prepare
        final String msg1 = "first";
        final String msg2 = "second";
        final List<String> expected = Arrays.asList(msg1, msg2);
        final List<String> results = new ArrayList<>(2);
        final CountDownLatch latch = new CountDownLatch(2);

        // action
        ThreadUtils.doInParallelAsync(
                () -> {
                    throw new IllegalStateException(msg1);
                },
                () -> {
                    throw new IllegalStateException(msg2);
                },
                (t) -> {
                    synchronized (results) {
                        results.add(t.getMessage());
                        latch.countDown();
                    }
                });

        // check
        try {
            latch.await();
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
        System.out.println("Expect : ");
        System.out.println(expected);
        System.out.println("Result : ");
        System.out.println(results);
    }

}