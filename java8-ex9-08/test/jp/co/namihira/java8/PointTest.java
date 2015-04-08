/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

    @Test
    public void test_compareTo_equal(){
        // setup
        final Point p1 = new Point(1, 1);
        final Point p2 = new Point(1, 1);

        // action
        final int result = p1.compareTo(p2);

        // check
        assertEquals(0, result);
    }

    @Test
    public void test_compareTo_bigger(){
        // setup
        final Point p12 = new Point(1, 2);
        final Point p21 = new Point(2, 1);
        final Point p2 = new Point(1, 1);

        // action, check
        int result = p12.compareTo(p2);
        assertTrue(0 < result);

        result = p21.compareTo(p2);
        assertTrue(0 < result);
    }

    @Test
    public void test_compareTo_bigger_max(){
        // setup
        final Point p1 = new Point(Integer.MAX_VALUE, 1);
        final Point p2 = new Point(Integer.MIN_VALUE, 1);

        // action
        final int result = p1.compareTo(p2);

        // check
        assertTrue(0 < result);
    }

    @Test
    public void test_compareTo_smaller(){
        // setup
        final Point p12 = new Point(1, 2);
        final Point p21 = new Point(2, 1);
        final Point p2 = new Point(2, 2);

        // action, check
        int result = p12.compareTo(p2);
        assertTrue(result < 0);

        result = p21.compareTo(p2);
        assertTrue(result < 0);
    }

    @Test
    public void test_compareTo_smaller_max(){
        // setup
        final Point p1 = new Point(Integer.MIN_VALUE, 1);
        final Point p2 = new Point(Integer.MAX_VALUE, 1);

        // action
        final int result = p1.compareTo(p2);

        // check
        assertTrue(result < 0);
    }


}
