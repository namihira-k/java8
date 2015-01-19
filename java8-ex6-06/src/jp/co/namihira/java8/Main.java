/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * マップを更新するメソッドとして、mergeの代わりにcomputeIfAbsentを使用して、
 * 練習問題5と同じアプリケーションを作成しなさい。この方法の利点は何ですか？
 */
/**
 * A.
 * - この方法の利点は何ですか？
 * computeIfAbsentは、Key値によって初期値を変更できるため、柔軟に設定できる。
 * 例：特定の単語の場合は、指定のFileをデフォルトとして挿入するなど。
 *
 * ※merge()は、初期値は「<V> value」に固定される。
 */

package jp.co.namihira.java8;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    final static int NUM_THREADS = 1_000;

    private static final WordFileMapping MAP = new WordFileMapping();

    public static void main(String[] args) {
        // prepare
        final ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        final CountDownLatch latch = new CountDownLatch(NUM_THREADS);

        final List<String> files = Arrays.asList("sample1.txt", "sample2.txt");

        // action
        for (int i = 0; i < NUM_THREADS; i++) {
            pool.submit(() -> {
                files.stream().forEach(f -> MAP.add(getFile(f)));
                latch.countDown();
            });
        };

        // check
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }

        MAP.entrySet().stream().forEach(System.out::println);
    }

    private static File getFile(final String filename) {
        final String classPath = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath();
        return new File(classPath + "/" + filename);
    }


}
