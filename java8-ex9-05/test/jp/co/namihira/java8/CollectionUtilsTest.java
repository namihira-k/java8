/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class CollectionUtilsTest {

    @Test
    public void test_reverse(){
        // setup
        final byte[] input = new byte[]{'h', 'o', 'g', 'e'};
        final byte[] reversed = new byte[]{'e', 'g', 'o', 'h'};

        // action
        final byte[] result = CollectionUtils.reverse(input);

        // check
        assertTrue(Arrays.equals(reversed, result));
    }

    @Test(expected = NullPointerException.class)
    public void test_reverse_null(){
        // setup
        // - nothing

        // action
        CollectionUtils.reverse(null);

        // check
        // - throw Exception
    }


}
