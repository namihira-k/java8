/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciTest {

    private int[][] fibonacciMatrix = {{1, 1}, {1, 0}};
    private int[] fibonacciNumber = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144,
            233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946};

    @Test
    public void test_Fibonacci_Matrix(){
        // prepare
        // - nothing

        // action
        final Fibonacci.Matrix result = new Fibonacci.Matrix();

        // check
        assertEquals(fibonacciMatrix[0][0], result.getElement(0, 0));
        assertEquals(fibonacciMatrix[0][1], result.getElement(0, 1));
        assertEquals(fibonacciMatrix[1][0], result.getElement(1, 0));
        assertEquals(fibonacciMatrix[1][1], result.getElement(1, 1));
    }

    @Test
    public void test_Fibonacci_Matrix_times(){
        // prepare
        final Fibonacci.Matrix matrix1 = new Fibonacci.Matrix();
        final Fibonacci.Matrix matrix2 = new Fibonacci.Matrix();

        // action
        final Fibonacci.Matrix result = matrix1.times(matrix2);

        // check
        assertEquals(2, result.getElement(0, 0));
        assertEquals(1, result.getElement(0, 1));
        assertEquals(1, result.getElement(1, 0));
        assertEquals(1, result.getElement(1, 1));
    }

    @Test(expected = NullPointerException.class)
    public void test_Fibonacci_Matrix_times_null(){
        // prepare
        final Fibonacci.Matrix matrix1 = new Fibonacci.Matrix();

        // action
        matrix1.times(null);

        // check
        // - throw Exception
    }

    @Test
    public void test_getFibonacciNumber_zero(){
        // prepare
        final int index = 0;

        // action
        final long result = Fibonacci.getFibonacciNumber(index);

        // check
        assertEquals(fibonacciNumber[index], result);
    }

    @Test
    public void test_getFibonacciNumber(){
        // prepare
        final int index = 20;

        // action
        final long result = Fibonacci.getFibonacciNumber(index);

        // check
        assertEquals(fibonacciNumber[index], result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getFibonacciNumber_minus(){
        // prepare
        final int index = -1;

        // action
        Fibonacci.getFibonacciNumber(index);

        // check
        // - throw Exception
    }


}