/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Stream<T>のメソッドである
 * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
 * を見てみなさい。
 * BiFunctionへの最初の型引数でUを? super Uと宣言すべきですか。その理由は、何ですか。
 *
 */
/**
 * A.
 * 宣言するべきではない。
 *
 * [理由]
 * 上記の関数において、accumulatorでの変数の読み込み処理と計算処理においては、
 * identiryから引き継いだ型（U）の上位クラス（<? super U>）を対象として処理することが可能である。
 * しかし、そのaccumulatoの戻り値はU型であるため、その下位クラス（<? extends U>）も返却することも可能である。
 * それらの共変と反変によって型の範囲が打ち消されるため、"U"として定義するのが望ましい。
 *
 * ※<? super U>としてBiFunctionの引数を定義はできるが、上位クラスでキャストすることが冗長な処理となる。
 */

package jp.co.namihira.java8;

public class Main<T> {

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        // - nothing

        // check
        // - nothing
    }

}