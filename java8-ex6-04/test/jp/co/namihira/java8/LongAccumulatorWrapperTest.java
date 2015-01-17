/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LongAccumulatorWrapperTest {

    public static LongAccumulatorWrapper accumulator = new LongAccumulatorWrapper(0);

    @Before
    public void setUp(){
        accumulator.reset();
    }

    @After
    public void tearDown(){
        accumulator.reset();
    }

    @Test
    public void test_max(){
        // prepare
        final long big = 1;
        final long small = -1;

        accumulator.accumulate(big);
        accumulator.accumulate(small);

        // action
        final long result = accumulator.max();

        // check
        assertEquals(big, result);
    }

    @Test
    public void test_min(){
        // prepare
        final long big = 1;
        final long small = -1;

        accumulator.accumulate(big);
        accumulator.accumulate(small);

        // action
        final long result = accumulator.min();

        // check
        assertEquals(small, result);
    }

}