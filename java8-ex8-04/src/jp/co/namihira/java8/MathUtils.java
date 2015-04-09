/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.math.BigInteger;


/**
 * 数計算関連のユーティリティクラス
 */
public class MathUtils {

    private static final long a = 11L;
    private static final long v = 246154705703781L;
    private static final long N = (long) Math.pow(2, 48);

    private static final long T = 107048004364958L;

    /*
     * prev(s)=(s-a)*v%N
     */
    public static long prev(final long s){
        final long diff = Math.subtractExact(s, a);
        final BigInteger multi = BigInteger.valueOf(diff).multiply(BigInteger.valueOf(v));

        return multi.mod(BigInteger.valueOf(N))
                    .add(BigInteger.valueOf(N))
                    .mod(BigInteger.valueOf(N))
                    .longValueExact();
    }

}