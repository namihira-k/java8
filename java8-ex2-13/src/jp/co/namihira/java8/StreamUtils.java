/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Streamに関するユーティリティクラス
 */
public class StreamUtils {

    /**
     * 指定された文字数より小さい単語の数をカウントします。
     *
     * @exception IllegalArgumentException wordsがnullの場合。または、lengthが正の数でない場合。
     *
     * @param words
     * @param length
     * @return カウント結果。インデックスが文字長を示します。
     */
    public static int[] countWordsLessThan(String[] words, final int length){
        if (words == null) {
            throw new IllegalArgumentException("words must not be null");
        }

        if (length <= 0) {
            throw new IllegalArgumentException("length must be positive number");
        }

        Map<Integer, Long> resultMap = Stream.of(words)
                .filter(w -> w.length() < length)
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));

        int[] result = new int[length];
        for (Integer i : resultMap.keySet()) {
            result[i] = resultMap.get(i).intValue();
        }
        return result;
    }

}