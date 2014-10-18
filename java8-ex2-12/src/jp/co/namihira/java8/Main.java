/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 51ページの2.13「並列ストリーム」で説明したように、AtomicIntegerの配列を更新することで、
 * 並列なStream<String>内の短い単語をすべて数えなさい。
 * 個々のカウントを安全に増やすためにアトミックであるgetAndIncrementメソッドを使用しなさい。
 */

package jp.co.namihira.java8;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // prepare
        String[] words = new String[]{"1", "22", "333", "22"};
        final int UPPER_LENGTH = 3;

        // action
        int[] result = StreamUtils.countWordsLessThan(words, UPPER_LENGTH);

        // check
        System.out.println("input : " + Arrays.toString(words));
        System.out.println("UPPER_LENGTH : " + UPPER_LENGTH);
        System.out.println("result : " + Arrays.toString(result));
    }

}