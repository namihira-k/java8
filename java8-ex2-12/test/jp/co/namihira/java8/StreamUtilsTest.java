/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class StreamUtilsTest {

    @Test
    public void test_countWordsLessThan() {
        // prepare
        String[] words = new String[]{"1", "22", "333"};

        // action
        int[] result = StreamUtils.countWordsLessThan(words, 2);

        // check
        int[] expected = new int[]{0, 1};
        assertArrayEquals(expected, result);
    }

    @Test
    public void test_countWordsLessThan_duplicate() {
        // prepare
        String[] words = new String[]{"1", "22", "333", "22"};

        // action
        int[] result = StreamUtils.countWordsLessThan(words, 3);

        // check
        int[] expected = new int[]{0, 1, 2};
        assertArrayEquals(expected, result);
    }

    @Test
    public void test_countWordsLessThan_upper_length() {
        // prepare
        String[] words = new String[]{"1"};

        // action
        int[] result = StreamUtils.countWordsLessThan(words, 5);

        // check
        int[] expected = new int[]{0, 1, 0, 0, 0};
        assertArrayEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_countWordsLessThan_null() {
        // prepare
        // - nothing

        // action
        StreamUtils.countWordsLessThan(null, 2);

        // check
        // - nothing
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_countWordsLessThan_length_less_than_one() {
        // prepare
        String[] words = new String[]{"1", "22", "333"};

        // action
        StreamUtils.countWordsLessThan(words, 0);

        // check
        // - nothing
    }



}
