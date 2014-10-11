/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamUtilsTest {

    @Test
    public void test_zip() {
        // prepare
        List<Integer> odd = Arrays.asList(1, 3, 5);
        List<Integer> even = Arrays.asList(2, 4, 6);

        // action
        Stream<Integer> result = StreamUtils.zip(odd.stream(), even.stream());

        // check
        Integer[] array = result.toArray(Integer[]::new);
        for (int i = 0; i < array.length - 1; i++) {
            assertEquals(i + 1, array[i].intValue());
        }
    }

    @Test
    public void test_zip_firstOverSecond() {
        // prepare
        List<Integer> odd = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> even = Arrays.asList(2, 4, 6);

        // action
        Stream<Integer> result = StreamUtils.zip(odd.stream(), even.stream());

        // check
        Integer[] array = result.toArray(Integer[]::new);
        assertEquals(even.size() * 2, array.length);

        for (int i = 0; i < even.size() - 1; i++) {
            assertEquals(i + 1, array[i].intValue());
        }
    }

    @Test
    public void test_zip_secondOverFirst() {
        // prepare
        List<Integer> odd = Arrays.asList(1, 3, 5);
        List<Integer> even = Arrays.asList(2, 4, 6, 8, 10);

        // action
        Stream<Integer> result = StreamUtils.zip(odd.stream(), even.stream());

        // check
        Integer[] array = result.toArray(Integer[]::new);
        assertEquals(odd.size() * 2, array.length);

        for (int i = 0; i < odd.size() - 1; i++) {
            assertEquals(i + 1, array[i].intValue());
        }
    }


    @Test
    public void test_zip_firstIsNull() {
        // prepare
        List<Integer> even = Arrays.asList(2, 4, 6);

        // action
        Stream<Integer> result = StreamUtils.zip(null, even.stream());

        // check
        assertEquals(0, result.count());
    }

    @Test
    public void test_zip_secondIsNull() {
        // prepare
        List<Integer> odd = Arrays.asList(1, 3, 5);

        // action
        Stream<Integer> result = StreamUtils.zip(odd.stream(), null);

        // check
        assertEquals(0, result.count());
    }

}
