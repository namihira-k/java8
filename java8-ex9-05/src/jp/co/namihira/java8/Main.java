/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * ファイルからすべての文字を読み込み、逆順に書き出すプログラムを作成しなさい。
 * Files.readAllBytesとFiles.writeを使用しなさい。
 */
/*
 * A.
 * - log
 * -->
 * I am Kosuke Namihira.
 * <--
 * .arihimaN ekusoK ma I
 */

package jp.co.namihira.java8;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args){
        // setup
        final Path in = getPath("in.txt");
        final Path out = getPath("out.txt");

        // action
        try {
            final byte[] bytes = Files.readAllBytes(in);
            System.out.println("-->");
            System.out.println(new String(bytes));

            final byte[] reversed = CollectionUtils.reverse(bytes);
            Files.write(out, reversed);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        // check
        try {
            final byte[] result = Files.readAllBytes(out);
            System.out.println("<--");
            System.out.println(new String(result));
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
