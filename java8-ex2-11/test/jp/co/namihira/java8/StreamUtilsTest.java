/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StreamUtilsTest {

    @Test
    public void test_collect() {
        // prepare
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> data1 = Arrays.asList(1, 2, 3);
        input.add(data1);
        List<Integer> data2 = Arrays.asList(4, 5, 6);
        input.add(data2);
        List<Integer> data3 = Arrays.asList(7, 8, 9);
        input.add(data3);

        // action
        List<Integer> result = StreamUtils.collect(input, Integer::sum);

        // check
        assertEquals(data1.stream().reduce(Integer::sum).get(), result.get(0));
        assertEquals(data2.stream().reduce(Integer::sum).get(), result.get(1));
        assertEquals(data3.stream().reduce(Integer::sum).get(), result.get(2));
    }

    @Test
    public void test_collect_null_list() {
        // prepare
        // - nothing

        // action
        List<Integer> result = StreamUtils.collect(null, Integer::sum);

        // check
        assertEquals(0, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_collect_null_accumulator() {
        // prepare
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> data1 = Arrays.asList(1);
        input.add(data1);

        // action
        StreamUtils.collect(input, null);

        // check
        // - nothing
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_collect_illegal_list_size() {
        // prepare
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> data1 = Arrays.asList(1, 2, 3);
        input.add(data1);
        List<Integer> data2 = Arrays.asList(4);
        input.add(data2);

        // action
        StreamUtils.collect(input, Integer::sum);

        // check
        // - nothing
    }

}
