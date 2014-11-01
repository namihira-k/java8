/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggerWrapperTest {

    private Logger logger = Logger.getLogger(LoggerWrapperTest.class.getName());

    @Before
    public void setUp() {
        logger.setLevel(Level.INFO);
    }

    @After
    public void tearDown() {
        logger.setLevel(Level.INFO);
    }

    @Test
    public void test_logIf() {
        // prepare
        final int SIZE = 11;
        Integer[] a = Stream.iterate(0, n -> n + 1).limit(SIZE).toArray(Integer[]::new);
        LoggerWrapper loggerWrapper = new LoggerWrapper(logger);

        // action
        for (int i = 0; i < a.length; i++) {
            final int INDEX = i;
            loggerWrapper.logIf(Level.INFO, () -> INDEX == 10,() -> "a[10] = " + a[10]);
        }

        // check
        assertEquals("a[10] = " + String.valueOf(SIZE - 1), loggerWrapper.getLastMessage());
    }

    @Test
    public void test_logIf_no_output_level() {
        // prepare
        final int SIZE = 11;
        Integer[] a = Stream.iterate(0, n -> n + 1).limit(SIZE).toArray(Integer[]::new);
        LoggerWrapper loggerWrapper = new LoggerWrapper(logger);

        // action
        for (int i = 0; i < a.length; i++) {
            final int INDEX = i;
            loggerWrapper.logIf(Level.FINEST, () -> INDEX == 10, () -> "a[10] = " + a[10]);
        }

        // check
        assertEquals("", loggerWrapper.getLastMessage());
    }

    @Test
    public void test_logIf_no_output_level_call_filter() {
        // prepare
        final int SIZE = 11;
        Integer[] a = Stream.iterate(0, n -> n + 1).limit(SIZE).toArray(Integer[]::new);
        LoggerWrapper loggerWrapper = new LoggerWrapper(logger);

        // action
        for (int i = 0; i < a.length; i++) {
            final int INDEX = i;
            loggerWrapper.logIf(Level.FINEST,
                    () -> {
                        System.out.println("called filter");
                        return INDEX == 10;
                    },
                    () -> {
                        System.out.println("called msg");
                        return "a[10] = " + a[10];
                    }
                    );
        }

        // check
        // - check no standard output
    }


    @Test
    public void test_logIf_no_output_condition() {
        // prepare
        final int SIZE = 11;
        Integer[] a = Stream.iterate(0, n -> n + 1).limit(SIZE).toArray(Integer[]::new);
        LoggerWrapper loggerWrapper = new LoggerWrapper(logger);

        // action
        for (int i = 0; i < a.length; i++) {
            final int INDEX = i;
            loggerWrapper.logIf(Level.FINEST, () -> INDEX == 1_000, () -> "a[10] = " + a[10]);
        }

        // check
        assertEquals("", loggerWrapper.getLastMessage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_logIf_null_level() {
        // prepare
        final int SIZE = 11;
        Integer[] a = Stream.iterate(0, n -> n + 1).limit(SIZE).toArray(Integer[]::new);
        LoggerWrapper loggerWrapper = new LoggerWrapper(logger);

        // action
        for (int i = 0; i < a.length; i++) {
            final int INDEX = i;
            loggerWrapper.logIf(null, () -> INDEX == 10, () -> "a[10] = " + a[10]);
        }

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_logIf_null_filter() {
        // prepare
        final int SIZE = 11;
        Integer[] a = Stream.iterate(0, n -> n + 1).limit(SIZE).toArray(Integer[]::new);
        LoggerWrapper loggerWrapper = new LoggerWrapper(logger);

        // action
        loggerWrapper.logIf(Level.FINEST, null, () -> "a[10] = " + a[10]);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_logIf_null_msgSupplier() {
        // prepare
        final int SIZE = 11;
        Integer[] a = Stream.iterate(0, n -> n + 1).limit(SIZE).toArray(Integer[]::new);
        LoggerWrapper loggerWrapper = new LoggerWrapper(logger);

        // action
        for (int i = 0; i < a.length; i++) {
            final int INDEX = i;
            loggerWrapper.logIf(Level.FINEST, () -> INDEX == 10, null);
        }

        // check
        // - throw Exception
    }

}
