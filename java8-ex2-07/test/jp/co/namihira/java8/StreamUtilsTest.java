/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamUtilsTest {

    @Test
    public void test_isFinite_true() {
        // prepare
        Stream<Double> stream = Stream.generate(Math::random).limit(StreamUtils.UPPER_LIMIT - 1);

        // action
        final boolean RESULT = StreamUtils.isFinite(stream);

        // check
        assertEquals(true, RESULT);
    }

    @Test
    public void test_isFinite_false() {
        // prepare
        Stream<Double> stream = Stream.generate(Math::random);

        // action
        final boolean RESULT = StreamUtils.isFinite(stream);

        // check
        assertEquals(false, RESULT);
    }

    @Test
    public void test_isFinite_null() {
        // prepare
        // - nothing

        // action
        final boolean RESULT = StreamUtils.isFinite(null);

        // check
        assertEquals(false, RESULT);
    }

    @Test(expected= IllegalStateException.class)
    public void test_isFinite_reuse() {
        // prepare
        Stream<Double> stream = Stream.generate(Math::random).limit(StreamUtils.UPPER_LIMIT - 1);

        // action
        StreamUtils.isFinite(stream);
        stream.count();

        // check
        // - checked Exception.
    }

}
