/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * ユークリッドのアルゴリズム（200年以上前のものです）は、
 * 2つの数値の最大公約数（greatest common divisor）を計算します。
 * bが0であれば、gcd(a,b)=aであり、そうでなければ、gcd(b, rem(a,b))です。
 * remは、余りを意味しています。
 * aかbかが負であったとしても、明らかにgcbは、負になるべきではありません
 * （なぜならば、その値の符号を正にしたものの方がより大きな約数となるからです）。
 * gcdのアルゴリズムを%、floorMod、数学的な（負ではない）余りを生成するrem関数で実装しなさい。
 * これらの3つの方法のどれが負の値に対して最も簡単ですか。
 */
/*
 * A.
 * - これらの3つの方法のどれが負の値に対して最も簡単ですか。
 * Math.floorMod()。
 * 第2引数が符号の決定しているため、計算式（後ろに連結する記法）が統一的に書ける。
 *
 * ※「数学的な（負ではない）余りを生成するrem関数」は問題の意図とあっていない気がしたため除外しました。
 */

package jp.co.namihira.java8;


public class Main {

    public static void main(String[] args) {
        // setup
        final int VALUE_1 = 100;
        final int VALUE_2 = 12;

        // action、check
        int result = MathUtils.gcdWithPercentOperation(VALUE_1, VALUE_2);
        System.out.println("MathUtils.gcdWithPercentOperation(VALUE_1, VALUE_2) : " + result);

        result = MathUtils.gcdWithPercentOperation(-VALUE_1, VALUE_2);
        System.out.println("MathUtils.gcdWithPercentOperation(-VALUE_1, VALUE_2) : " + result);

        result = MathUtils.gcdWithPercentOperation(VALUE_1, -VALUE_2);
        System.out.println("MathUtils.gcdWithPercentOperation(VALUE_1, -VALUE_2) : " + result);

        result = MathUtils.gcdWithFloorMod(VALUE_1, VALUE_2);
        System.out.println("MathUtils.gcdWithFloorMod(VALUE_1, VALUE_2) : " + result);

        result = MathUtils.gcdWithFloorMod(-VALUE_1, VALUE_2);
        System.out.println("MathUtils.gcdWithFloorMod(-VALUE_1, VALUE_2) : " + result);

        result = MathUtils.gcdWithFloorMod(VALUE_1, -VALUE_2);
        System.out.println("MathUtils.gcdWithFloorMod(VALUE_1, -VALUE_2) : " + result);

        result = MathUtils.gcdWithRem(VALUE_1, VALUE_2);
        System.out.println("MathUtils.gcdWithRem(VALUE_1, VALUE_2) : " + result);

        result = MathUtils.gcdWithRem(-VALUE_1, VALUE_2);
        System.out.println("MathUtils.gcdWithRem(-VALUE_1, VALUE_2) : " + result);

        result = MathUtils.gcdWithRem(VALUE_1, -VALUE_2);
        System.out.println("MathUtils.gcdWithRem(VALUE_1, -VALUE_2) : " + result);
    }

}
