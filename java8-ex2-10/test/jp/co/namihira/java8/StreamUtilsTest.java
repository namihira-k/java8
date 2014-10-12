/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamUtilsTest {

    @Test
    public void test_average() {
        // prepare
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);

        // action
        Double result = StreamUtils.average(values);

        // check
        DoubleSummaryStatistics except = values.stream()
                .collect(Collectors.summarizingDouble(Double::valueOf));
        assertEquals(except.getAverage(), result.doubleValue(), 0.1);
    }

    @Test
    public void test_average_bigdata() {
        // prepare
        final int MAX_SIZE = 10_000;
        List<Double> values = Stream.generate(Math::random).limit(MAX_SIZE).collect(Collectors.toList());

        // action
        Double result = StreamUtils.average(values);

        // check
        DoubleSummaryStatistics except = values.stream()
                .collect(Collectors.summarizingDouble(Double::valueOf));
        assertEquals(except.getAverage(), result.doubleValue(), 0.1);
    }

    @Test
    public void test_average_null() {
        // prepare
        // - nothing

        // action
        Double result = StreamUtils.average(null);

        // check
        assertEquals(0.0, result.doubleValue(), 0.1);
    }
}
