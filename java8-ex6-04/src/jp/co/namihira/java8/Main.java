/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * LongAccumulatorを使用して、要素の最大値あるいは最小値を計算しなさい。
 */

package jp.co.namihira.java8;

public class Main {

    public static LongAccumulatorWrapper accumulator = new LongAccumulatorWrapper(0);

    public static void main(String[] args) {
        excuteMax();
        excuteMin();
    }

    private static void excuteMax(){
        // prepare
        final long big = 1;
        final long small = -1;

        accumulator.accumulate(big);
        accumulator.accumulate(small);

        // action
        final long result = accumulator.max();

        // check
        System.out.println("Result max():");
        System.out.println(result);
    }

    private static void excuteMin(){
        // prepare
        final long big = 1;
        final long small = -1;

        accumulator.accumulate(big);
        accumulator.accumulate(small);

        // action
        final long result = accumulator.min();

        // check
        System.out.println("Result min():");
        System.out.println(result);
    }

}
