/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.math.BigInteger;

/**
 * 数計算関連のユーティリティクラス
 */
public class MathUtils {

    private static final long a = 11;
    private static final long v = 246154705703781L;
    private static final long N = (long) Math.pow(2, 48);

    public static long prev(final long s){
        final BigInteger ans = BigInteger.valueOf(s-a).multiply(BigInteger.valueOf(v));
        return Math.floorMod(ans.longValue(), N);
    }

}