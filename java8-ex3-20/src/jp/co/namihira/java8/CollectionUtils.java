/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionUtils {

    /**
     * 指定されたListの各要素に対してfを適用した結果のListを返します。
     *
     * @param list 処理対象のリスト
     * @param f 処理
     * @return 処理結果のリスト
     *
     * @throws IllegalArgumentException 引数のいずれかがnullの場合。
     */
    public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        if (list == null || f == null) {
            throw new IllegalArgumentException("args must not be null");
        }

        return list.stream().map((t) -> f.apply(t)).collect(Collectors.toList());
    }

}