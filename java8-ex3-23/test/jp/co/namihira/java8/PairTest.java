/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {

    @Test
    public void test_map(){
        // prepare
        final String left = "hoge";
        final String right = "foo";
        Pair<String> pair = new Pair<>(left, right);

        // action
        Pair<Integer> result = pair.map((t) -> t.length());

        // check
        assertEquals(left.length(), result.getLeft().intValue());
        assertEquals(right.length(), result.getRight().intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_map_null(){
        // prepare
        final String left = "hoge";
        final String right = "foo";
        Pair<String> pair = new Pair<>(left, right);

        // action
        pair.map(null);

        // check
        // - nothing
    }

}