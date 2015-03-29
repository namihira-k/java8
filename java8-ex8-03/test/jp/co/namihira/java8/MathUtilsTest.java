/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathUtilsTest {

    private static final int VALUE_1 = 100;
    private static final int VALUE_2 = 12;
    private static final int VALUE_ANSWER = 4;

    @Test
    public void test_gcdWithPercentOperation(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithPercentOperation(VALUE_1, VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithPercentOperation_zero_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithPercentOperation(-VALUE_ANSWER, 0);

        // check
        assertEquals(-VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithPercentOperation_a_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithPercentOperation(-VALUE_1, VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithPercentOperation_a_negative_asc(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithPercentOperation(-VALUE_2, VALUE_1);

        // check
        assertEquals(VALUE_ANSWER, result);
    }


    @Test
    public void test_gcdWithPercentOperation_b_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithPercentOperation(VALUE_1, -VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithPercentOperation_b_negative_acs(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithPercentOperation(VALUE_2, -VALUE_1);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithPercentOperation_a_b_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithPercentOperation(-VALUE_1, -VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithFloorMod(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithFloorMod(VALUE_1, VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithFloorMod_zero(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithFloorMod(VALUE_ANSWER, 0);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithFloorMod_zero_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithFloorMod(-VALUE_ANSWER, 0);

        // check
        assertEquals(-VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithFloorMod_a_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithFloorMod(-VALUE_1, VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithFloorMod_a_negative_asc(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithFloorMod(-VALUE_2, VALUE_1);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithFloorMod_b_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithFloorMod(VALUE_1, -VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithFloorMod_b_negative_ace(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithFloorMod(VALUE_2, -VALUE_1);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithFloorMod_a_b_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithFloorMod(-VALUE_1, -VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithRem(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithRem(VALUE_1, VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithRem_zero(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithRem(VALUE_ANSWER, 0);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithRem_zero_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithRem(-VALUE_ANSWER, 0);

        // check
        assertEquals(-VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithRem_a_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithRem(-VALUE_1, VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithRem_a_negative_acs(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithRem(-VALUE_2, VALUE_1);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithRem_b_negative(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithRem(VALUE_1, -VALUE_2);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

    @Test
    public void test_gcdWithRem_b_negative_asc(){
        // setup
        // - nothing

        // action
        final int result = MathUtils.gcdWithRem(VALUE_2, -VALUE_1);

        // check
        assertEquals(VALUE_ANSWER, result);
    }

}
