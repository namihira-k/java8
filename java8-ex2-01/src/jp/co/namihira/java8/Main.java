/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 24ページの2.1節「イテレーションからストリーム操作へ」のforループの並列バージョンを書きなさい。
 * リストのセグメントごとに処理を行う別々のスレッドを多数生成し、処理が終わるごとに結果を合計するようにしなさい。
 * （みなさんは、単一カウンターを更新するためにスレッドを使用したくはないでしょう。なぜですか）。
 */
/**
 * A.
 * 多数スレッドから単一リソース（カウンター）への排他制御のためのコスト（synchronized実装）が高いため、
 * 並列処理を使用したくない。
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // prepare
        List<String> words = new ArrayList<String>();
        words.add("1");
        words.add("12345");
        words.add("1234567890123");
        words.add("123456789012345");

        // action
        int result = countWordsOver12CharsactersWithParallel(words);

        // check
        System.out.println("result : " + result);
        final int expect = countWordsOver12CharsactersWithForLoop(words);
        if (result != expect) {
            throw new RuntimeException("result is not correct. result : " + result);
        }
    }

    /**
     * 並列処理を使って、12文字以上の単語の数を返します。
     * @param words
     * @return
     */
    private static int countWordsOver12CharsactersWithParallel(List<String> words) {
        List<Thread> threads = new ArrayList<Thread>(words.size());
        Counter counter = new Counter();
        for (String w : words) {
            threads.add(new Thread(() -> {
                if (w.length() > 12) counter.countUp();
            }));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return counter.getCount();
    }

    static class Counter {
        int count = 0;

        private synchronized void countUp(){
            count++;
        }

        private int getCount(){
            return count;
        }
    }

    /**
     * forループを使って、12文字以上の単語の数を返します。
     * @param words
     * @return
     */
    private static int countWordsOver12CharsactersWithForLoop(List<String> words) {
        int count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
        }
        return count;
    }

}