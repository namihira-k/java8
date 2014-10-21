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

        // action、check
        int result = CollectionUtils.countWordsWithParallel(words, 12);
        System.out.println("result countWordsWithParallel : " + result);

        // action、check
        result = CollectionUtils.countWordsWithParallel(words, 12);
        System.out.println("result countWordsWithForLoop : " + result);
    }

}