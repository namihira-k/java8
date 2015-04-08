/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabeledPointTest {

    @Test
    public void test_equals(){
        // setup
        final LabeledPoint p1 = new LabeledPoint("hoge", 1, 1);
        final LabeledPoint p2 = new LabeledPoint("hoge", 1, 1);

        // action
        final boolean result = p1.equals(p2);

        // check
        assertTrue(result);
    }

    @Test
    public void test_equals_null(){
        // setup
        final LabeledPoint p1 = new LabeledPoint(null, 1, 1);
        final LabeledPoint p2 = new LabeledPoint(null, 1, 1);

        // action
        final boolean result = p1.equals(p2);

        // check
        assertTrue(result);
    }

    @Test
    public void test_equals_not(){
        // setup
        final LabeledPoint p1  = new LabeledPoint("hoge", 1, 1);
        final LabeledPoint p21 = new LabeledPoint("foo",  1, 1);
        final LabeledPoint p22 = new LabeledPoint("hoge", 0, 1);
        final LabeledPoint p23 = new LabeledPoint("hoge", 1, 0);

        // action, check
        boolean result = p1.equals(p21);
        assertFalse(result);

        result = p1.equals(p22);
        assertFalse(result);

        result = p1.equals(p23);
        assertFalse(result);
    }

    @Test
    public void test_hashcode(){
        // setup
        final LabeledPoint p1  = new LabeledPoint("hoge", 1, 1);
        final LabeledPoint p2 = new LabeledPoint("hoge",  1, 1);

        // action
        final int result1 = p1.hashCode();
        final int result2 = p2.hashCode();

        // check
        assertEquals(result1, result2);
    }

    @Test
    public void test_hashcode_null(){
        // setup
        final LabeledPoint p1  = new LabeledPoint(null, 1, 1);
        final LabeledPoint p2 = new LabeledPoint(null,  1, 1);

        // action
        final int result1 = p1.hashCode();
        final int result2 = p2.hashCode();

        // check
        assertEquals(result1, result2);
    }

    @Test
    public void test_hashcode_not_equals(){
        // setup
        final LabeledPoint p1  = new LabeledPoint("hoge", 1, 1);
        final LabeledPoint p21 = new LabeledPoint("foo",  1, 1);
        final LabeledPoint p22 = new LabeledPoint("hoge", 0, 1);
        final LabeledPoint p23 = new LabeledPoint("hoge", 1, 0);

        // action, check
        final int result1 = p1.hashCode();
        int result = p21.hashCode();
        assertNotEquals(result1, result);

        result = p22.hashCode();
        assertNotEquals(result1, result);

        result = p23.hashCode();
        assertNotEquals(result1, result);
    }

}
