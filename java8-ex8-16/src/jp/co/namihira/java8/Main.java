/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 市（city）、州（state）、郵便番号（zip code）を含む行を解析するために
 * 名前キャプチャグループを用いた正規表現を使用しなさい。
 * 5桁と9桁の郵便番号の両方を受け付けるようにしなさい。
 */
/*
 * A.
 * - log
 * City : Chicago
 * State : IL
 * Zip Code : 60601
 *
 * City : SanFrancisco
 * State : CA
 * Zip Code : 94111
 *
 * City : Tampa
 * State : FL
 * Zip Code : 33701-4313
 */

package jp.co.namihira.java8;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    private final static String REGEX_ADDRESS = "(?<city>[\\p{L}]+),(?<state>[A-Z]{2}),(?<zipCode>[0-9]{5}(|(-[0-9]{4})))";

    public static void main(String[] args){
        // setup
        final Path testFile = getTestFile();
        final Pattern pattern = Pattern.compile(REGEX_ADDRESS);

        // action, check
        try (final Stream<String> lines = Files.lines(testFile)) {
            lines.forEachOrdered(line -> {
                final Matcher matcher = pattern.matcher(line);
                if (!matcher.matches()) {
                    return;
                }
                System.out.println("City : " + matcher.group("city"));
                System.out.println("State : " + matcher.group("state"));
                System.out.println("Zip Code : " + matcher.group("zipCode"));
                System.out.println("");
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Path getTestFile() {
        final String classPath = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath();
        final String fileName = "address_book.csv";
        return new File(classPath + "/" + fileName).toPath();
    }

}
