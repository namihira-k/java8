/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * ある文字数以上の長い単語のうち最初の5個だけ求める処理において、5番目の長い単語がいったん見つかったら、
 * filterメソッドが呼び出されないことを検証しなさい。 単純に、各メソッドの呼び出しでログを出せば良いです。
 */
/**
 * A.
 * 以下のような出力がされ、6つ目の単語（”123456789012345678901234567890”）が実行されていないことが分かる。
 * called elements...
 * 1
 * 12345
 * 1234567890
 * 123456789012345
 * 12345678901234567890
 * 1234567890123456789012345
 * In the list...
 * 12345
 * 1234567890
 * 123456789012345
 * 12345678901234567890
 * 1234567890123456789012345
 *
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // prepare
        final int MIN_LENGTH_WORD = 3;
        final int NUM_WORDS = 5;

        List<String> words = new ArrayList<>();
        words.add("1");
        words.add("12345");
        words.add("1234567890");
        words.add("123456789012345");
        words.add("12345678901234567890");
        words.add("1234567890123456789012345");
        words.add("123456789012345678901234567890");

        // action
        List<String> result = select(words, MIN_LENGTH_WORD, NUM_WORDS);

        // check
        // - checked output logs and list
        System.out.println("In the list...");
        result.stream().forEachOrdered(System.out::println);
    }

    /**
     * 指定した文字数以上の単語を、指定した数だけ格納したリストを返却します。
     * リストの最初のほう単語を優先的に返却します。
     *
     * @param words 単語のリスト
     * @param minLengthWord　最小の文字長
     * @param num　格納する数
     * @return　最小の文字より短い単語のリスト
     */
    private static List<String> select(List<String> words, final int minLengthWord, final int num) {
        if (words == null) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();

        System.out.println("called elements...");

        words.stream().filter(word -> {
            System.out.println(word);
            return (word.length() >= minLengthWord) ? result.add(word) : false;
        })
        .filter(word -> result.size() == num)
        .findFirst();

        return result;
    }

}