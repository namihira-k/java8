/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * Streamに関するユーティリティクラス
 */
public class StreamUtils {

    /**
     * 指定されたリスト内の各リストについて、指定した計算を実施し、その結果のリストを返却します。
     * 例：
     *  {{1,2,3}, {4,5,6}, {7,8,9}} -> {6, 15, 24}
     * listsがnullまたは、そのサイズがnullの場合、空のリストを返却します。
     *
     * 例外：
     * IllegalArgumentException　- accumulatorがnullの場合。または、各要素のリストがリストの大きさと同じ大きさで生成されていない場合。
     *
     * @param lists 計算するリストを含んだリスト
     * @param accumulator　各リストに実行するaccumulator
     * @return
     */
    public static <T> List<T> collect(List<List<T>> lists, final BinaryOperator<T> accumulator) {
        if (lists == null || lists.size() == 0) {
            return new ArrayList<>();
        }

        if (accumulator == null) {
            throw new IllegalArgumentException("accumulator can not be null.");
        }

        final int LIST_SIZE = lists.size();
        final long COUNT_DIFF_LIST_SIZE = lists.stream().filter(list -> list.size() != LIST_SIZE).count();
        if (COUNT_DIFF_LIST_SIZE != 0) {
            throw new IllegalArgumentException("size of lists must be equal to each element lists.");
        }

        ArrayList<T> tmpList = new ArrayList<>(lists.get(0));
        List<T> result = lists.parallelStream().collect(
                () -> {
                    return tmpList;
                },
                (tmp, list) -> {
                    tmp.set(lists.indexOf(list), list.stream().reduce(accumulator).get());
                },
                (tmp1, tmp2) -> {
                    return;
                });

        return result;
    }
}