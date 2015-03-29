/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathUtilsTest {

    // Math.pow(2, 32) - 1
    private static final int MAX_VALUE_UNSINGED_INT = Integer.parseUnsignedInt("4294967295");

    @Test
    public void test_add(){
        // prepare
        final int a = MAX_VALUE_UNSINGED_INT;
        final int b = MAX_VALUE_UNSINGED_INT;

        // action
        final long result = MathUtils.addUnsigned(a, b);

        // check
        assertEquals(Integer.toUnsignedLong(MAX_VALUE_UNSINGED_INT) * 2, result);
    }

    @Test
    public void test_subtract(){
        // prepare
        final int a = MAX_VALUE_UNSINGED_INT;
        final int b = MAX_VALUE_UNSINGED_INT - 1;

        // action
        final int result = MathUtils.subtractUnsigned(a, b);

        // check
        assertEquals(1, Integer.toUnsignedLong(result));
    }

    @Test
    public void test_divide(){
        // prepare
        final int a = MAX_VALUE_UNSINGED_INT;
        final int b = 2;

        // action
        final int result = MathUtils.divideUnsigned(a, b);

        // check
        assertEquals(Integer.MAX_VALUE, Integer.toUnsignedLong(result));
    }

    @Test(expected = ArithmeticException.class)
    public void test_divide_zero(){
        // prepare
        final int a = MAX_VALUE_UNSINGED_INT;

        // action
        MathUtils.divideUnsigned(a, 0);

        // check
        // - throw Exception
    }

    @Test
    public void test_compare(){
        // prepare
        final int a = MAX_VALUE_UNSINGED_INT;
        final int b = MAX_VALUE_UNSINGED_INT - 1;

        // action
        final int result1 = MathUtils.compareUnsigned(a, b);
        final int result2 = MathUtils.compareUnsigned(b, a);
        final int result3 = MathUtils.compareUnsigned(a, a);

        // check
        assertTrue(0 < result1);
        assertTrue(result2 < 0);
        assertTrue(result3 == 0);
    }

}
