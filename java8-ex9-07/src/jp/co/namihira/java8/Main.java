/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * ウェブページの内容を読み込んで、ファイルに保存するプログラムを作成しなさい。
 * URL.openStream と Files.copyを使用しなさい。
 */

package jp.co.namihira.java8;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Main {

    public static void main(String[] args) throws MalformedURLException{
        // setup
        final URL url = new URL("http://www.horstmann.com/");
        final Path out = getPath("out.html");

        // action
        try (final InputStream istream = url.openStream()) {
            Files.copy(istream, out, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        // check
        try {
            final List<String> lines = Files.readAllLines(out);
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
