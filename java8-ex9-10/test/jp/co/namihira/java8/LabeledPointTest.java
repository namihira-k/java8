/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabeledPointTest {

    @Test
    public void test_compareTo(){
        // setup
        final LabeledPoint p1 = new LabeledPoint("hoge", 1, 1);
        final LabeledPoint p2 = new LabeledPoint("hoge", 1, 1);

        // action
        final int result = p1.compareTo(p2);

        // check
        assertTrue(result == 0);
    }

    @Test
    public void test_compareTo_reflexivity(){
        // setup
        final LabeledPoint p1 = new LabeledPoint("hoge", 1, 1);
        final LabeledPoint p2 = new LabeledPoint("hoge", 1, 1);
        final LabeledPoint p3 = new LabeledPoint("hoge", 3, 3);

        // action
        final int result1 = p1.compareTo(p2);
        final int result2 = p1.compareTo(p3);
        final int result3 = p2.compareTo(p3);

        // check
        assertTrue(0 == result1);
        assertTrue(result2 == result3);
    }

    @Test
    public void test_compareTo_equals(){
        // setup
        final LabeledPoint p1 = new LabeledPoint("hoge", 1, 1);
        final LabeledPoint p2 = new LabeledPoint("hoge", 1, 1);

        // action
        final int result1 = p1.compareTo(p2);
        final boolean result2 = p1.equals(p2);

        // check
        assertTrue(result1 == 0);
        assertTrue(result2);
    }

    @Test(expected = NullPointerException.class)
    public void test_compareTo_nullpointer(){
        // setup
        final LabeledPoint p1 = new LabeledPoint("hoge", 1, 1);

        // action
        p1.compareTo(null);

        // check
        // - throw Exception
    }

    @Test
    public void test_compareTo_transitivity(){
        // setup
        final LabeledPoint p3 = new LabeledPoint("hoge", 3, 3);
        final LabeledPoint p2 = new LabeledPoint("hoge", 2, 2);
        final LabeledPoint p1 = new LabeledPoint("hoge", 1, 1);

        // action
        final int result1 = p3.compareTo(p2);
        final int result2 = p2.compareTo(p1);
        final int result3 = p3.compareTo(p1);

        // check
        assertTrue(0 < result1);
        assertTrue(0 < result2);
        assertTrue(0 < result3);
    }


    @Test
    public void test_compareTo_symmetry(){
        // setup
        final LabeledPoint p1 = new LabeledPoint("hoge", 1, 1);
        final LabeledPoint p2 = new LabeledPoint("hoge", 2, 2);

        // action
        final int result1 = p1.compareTo(p2);
        final int result2 = p2.compareTo(p1);

        // check
        assertTrue(result1 == -result2);
    }


    @Test
    public void test_compareTo_null(){
        // setup
        final LabeledPoint p1 = new LabeledPoint(null, 1, 1);
        final LabeledPoint p2 = new LabeledPoint(null, 1, 1);

        // action
        final int result = p1.compareTo(p2);

        // check
        assertTrue(result == 0);
    }

    @Test
    public void test_compareTo_bigger(){
        // setup
        final LabeledPoint p1 = new LabeledPoint("hoge", 2, 2);
        final LabeledPoint p21 = new LabeledPoint("foo", 2, 2);
        final LabeledPoint p22 = new LabeledPoint("hoge", 1, 2);
        final LabeledPoint p23 = new LabeledPoint("hoge", 2, 1);

        // action, check
        int result = p1.compareTo(p21);
        assertTrue(0 < result);

        result = p1.compareTo(p22);
        assertTrue(0 < result);

        result = p1.compareTo(p23);
        assertTrue(0 < result);
    }

    @Test
    public void test_compareTo_smaller(){
        // setup
        final LabeledPoint p1 = new LabeledPoint("foo",  1, 1);
        final LabeledPoint p21 = new LabeledPoint("hoge",  1, 1);
        final LabeledPoint p22 = new LabeledPoint("foo", 2, 1);
        final LabeledPoint p23 = new LabeledPoint("foo", 1, 2);

        // action, check
        int result = p1.compareTo(p21);
        assertTrue(result < 0);

        result = p1.compareTo(p22);
        assertTrue(result < 0);

        result = p1.compareTo(p23);
        assertTrue(result < 0);
    }

}
