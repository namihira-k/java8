/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class ThreadUtilsTest {

    @Test
    public void test_doInParallelAsync_first(){
        // prepare
        final String msg = "first";
        final List<String> results = new ArrayList<>(1);
        final CountDownLatch latch = new CountDownLatch(2);

        // action
        ThreadUtils.doInParallelAsync(
                () -> {
                    throw new IllegalStateException(msg);
                },
                () -> {
                    latch.countDown();
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
        assertEquals(msg, results.get(0));
    }

    @Test
    public void test_doInParallelAsync_second(){
        // prepare
        final String msg = "second";
        final List<String> results = new ArrayList<>(1);
        final CountDownLatch latch = new CountDownLatch(2);

        // action
        ThreadUtils.doInParallelAsync(
                () -> {
                    latch.countDown();
                },
                () -> {
                    throw new IllegalStateException(msg);
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
        assertEquals(msg, results.get(0));
    }

    @Test
    public void test_doInParallelAsync_first_second(){
        // prepare
        final String msg1 = "first";
        final String msg2 = "second";
        final List<String> expected = Arrays.asList(msg1, msg2);
        final List<String> results = new ArrayList<>(2);
        final CountDownLatch latch = new CountDownLatch(2);

        // action
        ThreadUtils.doInParallelAsync(
                () -> {
                    throw new IllegalStateException(msg2);
                },
                () -> {
                    throw new IllegalStateException(msg1);
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
        assertEquals(expected.size(), results.size());
        assertTrue(results.containsAll(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_doInParallelAsync_null_first(){
        // prepare
        // - nothing

        // action
        ThreadUtils.doInParallelAsync(
                null,
                () -> {
                    return;
                },
                (t) -> {
                    return;
                });

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_doInParallelAsync_null_second(){
        // prepare
        // - nothing

        // action
        ThreadUtils.doInParallelAsync(
                () -> {
                    return;
                },
                null,
                (t) -> {
                    return;
                });

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_doInParallelAsync_null_handler(){
        // prepare
        // - nothing

        // action
        ThreadUtils.doInParallelAsync(
                () -> {
                    return;
                },
                () -> {
                    return;
                },
                null
         );

        // check
        // - throw Exception
    }


}