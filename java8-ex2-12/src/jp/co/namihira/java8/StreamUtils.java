/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.concurrent.atomic.AtomicInteger;
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

        AtomicInteger[] shortWords = new AtomicInteger[length];
        for (int i = 0; i < length; i++) {
            shortWords[i] = new AtomicInteger(0);
        }

        Stream.of(words).parallel().forEach(
                w -> {
                    if (w.length() < length) {
                        shortWords[w.length()].getAndIncrement();
                    }
                });

        int[] result = Stream.of(shortWords)
                .mapToInt(r -> r.intValue())
                .toArray();
        return result;
    }

}