/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.List;

/**
 * Streamに関するユーティリティクラス
 */
public class StreamUtils {

    /**
     * 与えられた値のリストに対して、平均値を返却します。
     * リストがnullまたはサイズが0の場合、0.0を返却します。
     *
     * @param values
     * @return　平均値
     */
    public static double average(List<Double> values) {
        if (values == null || values.size() == 0) {
            return 0.0;
        }

        final long SIZE = values.size();
        Double avg = values.stream().reduce(0.0,
                (total, value) -> total + (value / SIZE),
                (total1, total2) -> total1 + total2);

        return avg;
    }

}