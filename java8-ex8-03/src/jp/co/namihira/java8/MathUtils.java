/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

/**
 * 数計算関連のユーティリティクラス
 */
public class MathUtils {

    /**
     * 最大公約数を返します。計算には"%"を使用しています。
     * @param a 値1
     * @param b 値2
     * @return 最大公約数
     */
    public static int gcdWithPercentOperation(final int a, final int b) {
        if (b == 0) {
            return a;
        }
        return gcdWithPercentOperation(b, ((a % b) + Math.abs(b)) % b);
    }

    /**
     * 最大公約数を返します。計算には"Math.floorMod()"を使用しています。
     * @param a 値1
     * @param b 値2
     * @return 最大公約数
     */
    public static int gcdWithFloorMod(final int a, final int b) {
        if (b == 0) {
            return a;
        }
        return gcdWithFloorMod(b, (Math.floorMod(a, b) + Math.abs(b)) % Math.abs(b));
    }

    /**
     * 最大公約数を返します。
     * @param a 値1
     * @param b 値2
     * @return 最大公約数
     */
    public static int gcdWithRem(final int a, final int b) {
        if (b == 0) {
            return a;
        }
        return gcdWithRem(b, rem(a, b));
    }

    private static int rem(final int a, final int b){
        return Math.abs(a % b);
    }

}
