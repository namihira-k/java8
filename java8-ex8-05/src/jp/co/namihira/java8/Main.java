/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 第2章の初めで、リスト内の長い単語を
 * words.stream().filter(w -> w.length() > 12).count()で数えました。
 * ラムダ式を用いて、ストリームを使用しないで、同じことを行いなさい。
 * 長いリストに対してはどちらの操作が速いですか。
 */
/*
 * A.
 * - 長いリストに対してはどちらの操作が速いですか。
 * Total : 540526
 * No Stream...
 *  - Count : 7847
 *  - Time[sec] : 0.086120054
 * Stream...
 *  - Count : 7847
 *  - Time[sec] : 0.412617869
 *
 * 環境：
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // prepare
        final int minLengthWord = 12;
        final String regex = "[\\P{L}]+";
        final String text = getTestData();
        final List<String> words = new ArrayList<>(Arrays.asList(text.split(regex)));
        final Stream<String> wordsStream = Pattern.compile(regex).splitAsStream(text);
        System.out.println("Total : " + words.size());

        /*** Stream利用しない場合 ***/
        // action、check
        System.out.println("No Stream...");
        measureTime(() -> {
            words.removeIf(w -> w.length() <= minLengthWord);
            System.out.println(" - Count : " + words.size());
        });

        /*** Stream利用する場合 ***/
        // action、check
        System.out.println("Stream...");
        measureTime(() -> {
            final long result = wordsStream.filter(w -> w.length() > minLengthWord).count();
            System.out.println(" - Count : " + result);
        });
    }

    private static void measureTime(final Runnable action){
        final long start = System.nanoTime();
        action.run();
        final long end = System.nanoTime();
        System.out.println(" - Time[sec] : " + (double)(end - start)/1_000_000_000.0);
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
        final String classPath = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath();
        final String fileName = "war_and_peace.txt";
        return new File(classPath + "/" + fileName);
    }

}
