/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * int値と符号なし操作を使用して、0 から 2^32 -1 までの値の数値の加算、減算、除算、比較を
 * 行うプログラムを書きなさい。divideUnsignedとremainderUnsignedが必要な理由を示しなさい。
 */
/*
 * A.
 * - divideUnsignedとremainderUnsignedが必要な理由を示しなさい。
 * 「2^32-1」はInteger.MAX_VALUE（2^31）を超えており（2倍）、
 * 符号なしint値として値の領域を広げて計算する必要があるため。
 *
 * - log
 * add : 8589934590
 * subtract : 1
 * divideUnsigned : 2147483647
 * compareUnsigned(a, b) : 1
 * compareUnsigned(a, a) : 0
 * compareUnsigned(b, a) : -1
 *
 */

package jp.co.namihira.java8;

public class Main {

    // Math.pow(2, 32) - 1
    private static final int MAX_VALUE_UNSINGED_INT = Integer.parseUnsignedInt("4294967295");

    public static void main(String[] args) {
        // setup
        final int a = MAX_VALUE_UNSINGED_INT;
        final int b = MAX_VALUE_UNSINGED_INT - 1;

        // action, check
        long result_add = MathUtils.addUnsigned(a, a);
        System.out.println("add : " + result_add);

        int result = MathUtils.subtractUnsigned(a, b);
        System.out.println("subtract : " + Integer.toUnsignedLong(result));

        result = MathUtils.divideUnsigned(a, 2);
        System.out.println("divideUnsigned : " + Integer.toUnsignedLong(result));

        result = MathUtils.compareUnsigned(a, b);
        System.out.println("compareUnsigned(a, b) : " + result);

        result = MathUtils.compareUnsigned(a, a);
        System.out.println("compareUnsigned(a, a) : " + result);

        result = MathUtils.compareUnsigned(b, a);
        System.out.println("compareUnsigned(b, a) : " + result);
    }

}
