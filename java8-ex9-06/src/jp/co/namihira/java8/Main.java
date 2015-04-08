/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * ファイルのすべての行を読み込み、逆順に書き出すプログラムを作成しなさい。
 * Files.readAllLinesとFiles.writeを使用しなさい。
 */
/*
 * A.
 * - log
 * - Order
 * 1. I am Kosuke Namihira.
 * 2. I am form Tochigi.
 * 3. I live in Tokyo.
 * - Reverse
 * 3. I live in Tokyo.
 * 2. I am form Tochigi.
 * 1. I am Kosuke Namihira.
 */

package jp.co.namihira.java8;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args){
        // setup
        final Path in = getPath("in.txt");
        final Path out = getPath("out.txt");

        // action
        try {
            final List<String> lines = Files.readAllLines(in);
            System.out.println("- Order");
            lines.stream().forEachOrdered(System.out::println);

            Collections.reverse(lines);

            Files.write(out, lines);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        // check
        try {
            final List<String> lines = Files.readAllLines(out);
            System.out.println("- Reverse");
            lines.stream().forEachOrdered(System.out::println);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Path getPath(final String filename) {
        final String classPath = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath();
        return new File(classPath + "/" + filename).toPath();
    }


}
