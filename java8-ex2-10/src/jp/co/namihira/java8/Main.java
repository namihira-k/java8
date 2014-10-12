/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Stream<Double> の平均を計算するために使用できる reduceの呼び出しを書きなさい。
 * 単純に合計して、count()で割ることができないのはなぜですか。
 */
/**
 * A.
 * reduceに渡す関数は、個々の要素に対して行う関数の渡すため、
 * 最終的な計算結果に対する処理に関する情報（”合計を総数で割る”）を渡せないため。
 */

package jp.co.namihira.java8;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // prepare
        final int MAX_SIZE = 10_000;
        List<Double> values = Stream.generate(Math::random).limit(MAX_SIZE).collect(Collectors.toList());

        // action
        Double result = StreamUtils.average(values);

        // check
        DoubleSummaryStatistics except = values.stream()
                .collect(Collectors.summarizingDouble(Double::valueOf));
        System.out.println("except : " + except.getAverage());
        System.out.println("result : " + result.doubleValue());

        final double DIFF = except.getAverage() - result.doubleValue();
        System.out.println("diff(except - result) : " + DIFF);
        System.out.println("diff is less than 0.1 ? " + (Math.abs(DIFF) < 0.1));
    }

}