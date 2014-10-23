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
     * ストリームである frist とsecond から要素を交互に取り出し、結合したストリームを返却します。
     * どちらかのストリームから要素がなくなったら結合は停止し、返却します。
     * どちらかのストリームがnullの場合、空のストリームを返却します。
     *
     * @param first
     * @param second
     * @return 結合されたストリーム
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        if (first == null || second == null) {
            return Stream.empty();
        }

        T[] firstArray = (T[]) first.toArray();
        T[] secondArray = (T[]) second.toArray();

        List<T> result = new ArrayList<>();

        final int STREAM_LENGTH = (firstArray.length >= secondArray.length) ? secondArray.length : firstArray.length;
        for (int i = 0; i < STREAM_LENGTH; i++) {
            result.add(firstArray[i]);
            result.add(secondArray[i]);
        }
        return result.stream();
    }

}