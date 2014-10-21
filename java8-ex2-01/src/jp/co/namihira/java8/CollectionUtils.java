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

public class CollectionUtils {

    /**
     * 並列処理を使って、指定された文字数以上の単語の数を返します。
     * wordsにnullが指定された場合、0を返却します。
     *
     * @param words 検索対象の単語群
     * @param maxLength　最大長
     * @return　指定された文字数以上の単語の数
     */
    public static int countWordsWithParallel(List<String> words, final int maxLength) {
        if (words == null) {
            return 0;
        }

        if (maxLength < 0) {
            return words.size();
        }

        List<Thread> threads = new ArrayList<Thread>(words.size());
        Counter counter = new Counter();
        for (String w : words) {
            threads.add(new Thread(() -> {
                if (w.length() > maxLength) counter.countUp();
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
     * forループを使って、指定された文字数以上の単語の数を返します。
     * wordsにnullが指定された場合、0を返却します。
     *
     * @param words 検索対象の単語群
     * @param maxLength　最大長
     * @return　指定された文字数以上の単語の数
     */

    public static int countWordsWithForLoop(List<String> words, final int maxLength) {
        if (words == null) {
            return 0;
        }

        if (maxLength < 0) {
            return words.size();
        }

        int count = 0;
        for (String w : words) {
            if (w.length() > maxLength) count++;
        }
        return count;
    }

}