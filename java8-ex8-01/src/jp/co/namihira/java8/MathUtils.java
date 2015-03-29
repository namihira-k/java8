/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

public class MathUtils {

    //加算、減算、除算、比較を

    /**
     * 指定された値の加算を実施します。各引数 は符号なしの値として解釈されます。
     * @param a　加算する値
     * @param b　加算する値
     * @return　加算結果
     */
    public static long addUnsigned(final int a, final int b){
        return Integer.toUnsignedLong(a) + Integer.toUnsignedLong(b);
    }

    /**
     * 指定された値の減算を実施します。各引数と結果 は符号なしの値として解釈されます。
     * @param a　引かれる値
     * @param b　引く値
     * @return　減算結果
     */
    public static int subtractUnsigned(final int a, final int b){
        return a - b;
    }

    /**
     * 指定された値の除算を実施します。各引数と結果 は符号なしの値として解釈されます。
     * @param dividend　割られる値
     * @param divisor　割る値
     * @return　除算結果
     *
     * @throws ArithmeticException divisorに0を指定した場合
     */
    public static int divideUnsigned(final int dividend, final int divisor){
        if (divisor == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return Integer.divideUnsigned(dividend, divisor);
    }

    /**
     * 指定された値の比較を実施します。各引数 は符号なしの値として解釈されます。
     * @param x 比較する最初の値
     * @param y 比較する2つ目の値
     * @return　x == yの場合は値0、符号なしの値としてx < yの場合は0より小さい値、符号なしの値としてx> yの場合は0より大きい値
     */
    public static int compareUnsigned(final int x, final int y){
        return Integer.compareUnsigned(x, y);
    }

}
