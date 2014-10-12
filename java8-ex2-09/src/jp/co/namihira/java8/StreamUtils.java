/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Streamに関するユーティリティクラス
 */
public class StreamUtils {

    /**
     * ストリーム内のList群をフラット化したListを返却します。
     * その際、入力の順番は維持されます。
     * nullが指定された場合、空のリストを返却します。
     * ※実装には、以下の関数を使用しています。
     *      Optional<T> reduce(BinaryOperator<T> accumulator);
     * @param stream
     * @return　フラット化したList
     */
    public static <T> List<T> flatListWithReduce1args(Stream<List<T>> stream) {
        if (stream == null) {
            return new ArrayList<>(0);
        }

        List<List<T>> tmp = new ArrayList<>();
        tmp.add(new ArrayList<>());
        stream.forEachOrdered(list -> tmp.add(list));

        List<T> result = new ArrayList<>();
        tmp.stream().sequential().reduce(
                (total, list) -> {
                    result.addAll(list);
                    return result;
                });
        return result;
    }

    /**
     * ストリーム内のList群をフラット化したListを返却します。
     * その際、入力の順番は維持されます。
     * nullが指定された場合、空のリストを返却します。
     * ※実装には、以下の関数を使用しています。
     *      T reduce(T identity, BinaryOperator<T> accumulator);
     * @param stream
     * @return　フラット化したList
     */
    public static <T> List<T> flatListWithReduce2args(Stream<List<T>> stream) {
        if (stream == null) {
            return new ArrayList<>(0);
        }

        List<T> result = stream.sequential().reduce(new ArrayList<>(0),
                (total, list) -> {
                    total.addAll(list);
                    return total;
                });
        return result;
    }

    /**
     * ストリーム内のList群をフラット化したListを返却します。
     * その際、入力の順番は維持されます。
     * nullが指定された場合、空のリストを返却します。
     * ※実装には、以下の関数を使用しています。
     *     <U> U reduce(U identity,
     *           BiFunction<U, ? super T, U> accumulator,
     *           BinaryOperator<U> combiner);
     * @param stream
     * @return　フラット化したList
     */
    public static <T> List<T> flatListWithReduce3args(Stream<List<T>> stream) {
        if (stream == null) {
            return new ArrayList<>(0);
        }

        List<T> result = stream.sequential().reduce(new ArrayList<>(0),
                (total, list) -> {
                    total.addAll(list);
                    return total;
                },
                (total1, total2) -> {
                    total1.addAll(total2);
                    return total1;
                });
        return result;
    }

}