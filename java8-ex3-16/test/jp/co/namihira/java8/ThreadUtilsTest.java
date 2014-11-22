/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThreadUtilsTest {

    private LoggerWrapper loggerLoggerWrapper = null;
    private Logger logger = Logger.getLogger(ThreadUtilsTest.class.getName());

    @Before
    public void setUp() {
        logger.setLevel(Level.INFO);
        loggerLoggerWrapper = new LoggerWrapper(logger);
    }

    @After
    public void tearDown() {
        logger.setLevel(Level.INFO);
        loggerLoggerWrapper = new LoggerWrapper(logger);
    }

    @Test
    public void test_doInOrderAsync_exception(){
        // prepare
        final List<String> input = Arrays.asList("hoge", "foo", "password");
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
                            loggerLoggerWrapper.log(Level.WARNING, t.getMessage());
                            return;
                        }
                        loggerLoggerWrapper.log(Level.INFO, words.stream().collect(Collectors.joining(",")));
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
        assertTrue(loggerLoggerWrapper.getLastMessage().contains(errorMessage));
    }

    @Test
    public void test_doInOrderAsync_normal(){
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
                            loggerLoggerWrapper.log(Level.WARNING, t.getMessage());
                            return;
                        }
                        loggerLoggerWrapper.log(Level.INFO, words.stream().collect(Collectors.joining(",")));
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
        assertTrue(loggerLoggerWrapper.getLastMessage().contains(input.stream().collect(Collectors.joining(","))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_doInOrderAsync_null_first(){
        // prepare
        // - nothing

        // action
        ThreadUtils.doInOrderAsync(
                null,
                (words, t) -> {
                    return;
                }
         );

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_doInOrderAsync_null_second(){
        // prepare
        // - nothing

        // action
        ThreadUtils.doInOrderAsync(
                () -> {return null;},
                null
         );

        // check
        // - throw Exception
    }


}