/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * streamではなく、parallelStreamで長い単語を数えた場合の速度を違いを計りなさい。
 * 呼び出しの前後で System.nanoTime 呼び出して、差を検証しなさい。速いコンピュータを持っているのであれば、
 * （『戦争と平和』（War and Peace）などの）もっと大きなドキュメントで試しなさい。
 */
/**
 * A.
 * 以下のような実行結果だった。
 * Stream...
 *  - Count : 7847
 *  - Time[sec] : 0.760067351
 *
 * ParallelStream...
 *  - Count : 7847
 *  - Time[sec] : 0.551866886
 *
 * Diff[sec] : 0.208200465
 *
 *　環境：
 *  - OS : windows 8.1
 *  - CPU : Intel(R) Core(TM) i5-3317U CPU @ 1.70GHz 1.70 GHz
 *  - 実装メモリ（RAM） : 4.00 GB
 *  - 64ビット オペレーション システム、x64 ベース プロセッサ
 *
 * 参考：
 *  - 戦争と平和：http://www.gutenberg.org/ebooks/2600
 */

package jp.co.namihira.java8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // prepare
        final int MIN_LENGTH_WORD = 12;
        final String text = getTestData();
        final String REGEX = "[\\P{L}]+";

        /*** Stream処理 ***/
        Stream<String> words = Pattern.compile(REGEX).splitAsStream(text);
        // action
        long start = System.nanoTime();
        long result = words.filter(word -> word.length() > MIN_LENGTH_WORD).count();
        long end = System.nanoTime();

        // check
        System.out.println("Stream...");
        System.out.println(" - Count : " + result);
        final long TIME_SEQUENTIAL = end - start;
        System.out.println(" - Time[sec] : " + (double)TIME_SEQUENTIAL/1_000_000_000.0);


        /*** ParallelStream処理 ***/
        words = Pattern.compile(REGEX).splitAsStream(text).parallel();
        // action
        start = System.nanoTime();
        result = words.filter(word -> word.length() > MIN_LENGTH_WORD).count();
        end = System.nanoTime();

        // check
        System.out.println("ParallelStream...");
        System.out.println(" - Count : " + result);
        final long TIME_PARALLEL = end - start;
        System.out.println(" - Time[sec] : " + (double)TIME_PARALLEL/1_000_000_000.0);


        // check
        System.out.println("Diff[sec] : " + (double)(TIME_SEQUENTIAL - TIME_PARALLEL)/1_000_000_000.0);
    }

    private static String getTestData(){
        File testFile = getTestFile();

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            String str = br.readLine();
            while(str != null){
              sb.append(str);
              str = br.readLine();
            }
        } catch (IOException e) {
               throw new RuntimeException(e);
        }

        return sb.toString();
    }

    private static File getTestFile() {
        final String CLASS_PATH = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath();
        final String FILE_NAME = "war_and_peace.txt";
        return new File(CLASS_PATH + "/" + FILE_NAME);
    }

}