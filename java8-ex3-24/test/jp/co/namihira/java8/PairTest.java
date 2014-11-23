/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {

    @Test
    public void test_flatMap(){
        // prepare
        final String left = "hoge";
        final String right = "foo";
        Pair<String> pair = new Pair<>(left, right);

        // action
        Pair<Integer> result = pair.flatMap(
                (l, r) -> {
                    final int lengthL = (l == null ? 0 : l.length());
                    final int lengthR = (r == null ? 0 : r.length());
                    Pair<Integer> tmp = new Pair<>(lengthL, lengthR);
                    reverse(tmp);
                    return tmp;
                });

        // check
        assertEquals(left.length(), result.getRight().intValue());
        assertEquals(right.length(), result.getLeft().intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_flatMap_null(){
        // prepare
        final String left = "hoge";
        final String right = "foo";
        Pair<String> pair = new Pair<>(left, right);

        // action
        pair.flatMap(null);

        // check
        // - nothing
    }

    @Test
    public void test_flatMap_null_left(){
        // prepare
        final String right = "foo";
        Pair<String> pair = new Pair<>(null, right);

        // action
        Pair<Integer> result = pair.flatMap(
                (l, r) -> {
                    final int lengthL = (l == null ? 0 : l.length());
                    final int lengthR = (r == null ? 0 : r.length());
                    Pair<Integer> tmp = new Pair<>(lengthL, lengthR);
                    reverse(tmp);
                    return tmp;
                });

        // check
        assertEquals(0, result.getRight().intValue());
        assertEquals(right.length(), result.getLeft().intValue());
    }

    @Test
    public void test_flatMap_null_right(){
        // prepare
        final String left = "foo";
        Pair<String> pair = new Pair<>(left, null);

        // action
        Pair<Integer> result = pair.flatMap(
                (l, r) -> {
                    final int lengthL = (l == null ? 0 : l.length());
                    final int lengthR = (r == null ? 0 : r.length());
                    Pair<Integer> tmp = new Pair<>(lengthL, lengthR);
                    reverse(tmp);
                    return tmp;
                });

        // check
        assertEquals(left.length(), result.getRight().intValue());
        assertEquals(0, result.getLeft().intValue());
    }

    private <T> void reverse(Pair<T> pair) {
        final T left = pair.getLeft();
        pair.setLeft(pair.getRight());
        pair.setRight(left);
    }

}