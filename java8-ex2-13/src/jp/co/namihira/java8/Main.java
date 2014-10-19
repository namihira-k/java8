/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 練習問題12に対して次の点を変更し、その問題を解きなさい。
 * 変更点として、短い文字列はフィルターで取り出し、
 * Collectors.groupingBy と Collectors.counting
 * と一緒にcollectメソッドを使用しなさい。
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