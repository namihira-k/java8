/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * Math,negateExact(n)が例外をスローすることになる整数nの値は何ですか？
 * （ヒント：1つの値しか該当しません）。
 */
/*
 * A.
 * negateExact(int a) : Integer.MIN_VALUE(-2^31)
 * negateExact(long a) : Long.MIN_VALUE(-2^63)
 * ※対応する否定値が値の範囲を超えるため。
 *
 * - log
 * java.lang.ArithmeticException: integer overflow
 *  at java.lang.Math.negateExact(Math.java:977)
 *  at jp.co.namihira.java8.Main.main(Main.java:30)
 *
 * java.lang.ArithmeticException: long overflow
 *  at java.lang.Math.negateExact(Math.java:994)
 *  at jp.co.namihira.java8.Main.main(Main.java:43)
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args) {
        try {
            // setup
            final int a = Integer.MIN_VALUE;

            // action
            Math.negateExact(a);

            // check
            // - throw ArithmeticException
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // setup
            final long b = Long.MIN_VALUE;

            // action
            Math.negateExact(b);

            // check
            // - throw ArithmeticException
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
