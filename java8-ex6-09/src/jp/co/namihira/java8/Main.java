/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * フィボナッチ（Fibonacci）数の計算を並列化するためにparallelPrefixメソッドを使用することができます。
 * n番目のフィボナッチ数は、
 *  F = ( 1 1 )
 *      ( 1 0 )
 * とした場合の、F^nを計算した結果の行列の右上の値です。2×2の行列で配列を埋めなさい。
 * 乗算のメソッドを持つMatrixクラスを定義し、行列の配列を作成するためにparallelSetAllを使用し、
 * 行列の乗算をするためにparallelPrefixを使用しなさい。
 */
/**
 * - See : http://ja.wikipedia.org/wiki/フィボナッチ数
 */

package jp.co.namihira.java8;

import java.util.Arrays;

public class Main {

    private static int[] fibonacciNumber = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144,
            233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946};

    public static void main(String[] args) {
        // prepare
        final int index1 = 10;
        final int index2 = 20;

        // action
        final long result1 = Fibonacci.getFibonacciNumber(index1);
        final long result2 = Fibonacci.getFibonacciNumber(index2);

        // check
        System.out.println("Fibonacci Number : ");
        System.out.println(Arrays.toString(fibonacciNumber));
        System.out.println("index : " + index1);
        System.out.println("- Expect : " + fibonacciNumber[index1]);
        System.out.println("- Result : " + result1);
        System.out.println("index : " + index2);
        System.out.println("- Expect : " + fibonacciNumber[index2]);
        System.out.println("- Result : " + result2);
    }

}
