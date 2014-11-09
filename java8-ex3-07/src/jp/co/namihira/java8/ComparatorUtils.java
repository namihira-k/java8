/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Comparator;

/**
 * Comparatorに関するユーティリティクラス
 */
public class ComparatorUtils {

    /**
     * 指定されたComparatorをthenComparing()で連結したComparatorを返却します。
     *
     * @param comparators Comparator
     * @return 連結したComparator
     *
     * @throws IllegalArgumentException 引数がnullの場合
     *
     */
    @SuppressWarnings("unchecked")
    public static Comparator<String> comparing(Comparator<String>... comparators){
        if (comparators == null) {
            throw new IllegalArgumentException("comparators must not be null");
        }

        Comparator<String> combinedComp = (first, second) -> comparators[0].compare(first, second);
        if (comparators.length == 1) {
            return combinedComp;
        }

        for (int i = 1; i < comparators.length; i++) {
            combinedComp = combinedComp.thenComparing(comparators[i]);
        }

        final Comparator<String> result = combinedComp;
        return (first, second) -> result.compare(first, second);
    }

}