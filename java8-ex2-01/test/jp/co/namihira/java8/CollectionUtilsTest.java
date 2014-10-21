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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CollectionUtilsTest {

    @Test
    public void test_countWordsWithParallel() {
        // prepare
        List<String> words = new ArrayList<String>();
        words.add("1");
        words.add("12345");
        words.add("1234567890123");
        words.add("123456789012345");

        // action
        int result = CollectionUtils.countWordsWithParallel(words, 12);

        // check
        assertEquals(2, result);
    }

    @Test
    public void test_countWordsParallel_null_words() {
        // prepare
        // - nothing

        // action
        int result = CollectionUtils.countWordsWithParallel(null, 12);

        // check
        assertEquals(0, result);
    }

    @Test
    public void test_countWordsParallel_length_negative() {
        // prepare
        List<String> words = new ArrayList<String>();
        words.add("1");
        words.add("12345");
        words.add("1234567890123");
        words.add("123456789012345");

        // action
        int result = CollectionUtils.countWordsWithParallel(words, -1);

        // check
        assertEquals(words.size(), result);
    }

    @Test
    public void test_countWordsWithForLoop() {
        // prepare
        List<String> words = new ArrayList<String>();
        words.add("1");
        words.add("12345");
        words.add("1234567890123");
        words.add("123456789012345");

        // action
        int result = CollectionUtils.countWordsWithForLoop(words, 12);

        // check
        assertEquals(2, result);
    }

    @Test
    public void test_countWordsWithForLoop_null_words() {
        // prepare
        // - nothing

        // action
        int result = CollectionUtils.countWordsWithForLoop(null, 12);

        // check
        assertEquals(0, result);
    }

    @Test
    public void test_countWordsWithForLoop_length_negative() {
        // prepare
        List<String> words = new ArrayList<String>();
        words.add("1");
        words.add("12345");
        words.add("1234567890123");
        words.add("123456789012345");

        // action
        int result = CollectionUtils.countWordsWithForLoop(words, -1);

        // check
        assertEquals(words.size(), result);
    }


}