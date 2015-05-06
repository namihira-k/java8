/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * ProcessBuilderクラスを使用して、ユーザーのホームディレクトリのすべての
 * サブディレクトリ内のすべてのファイルからクレジットカード番号を探すために grep -r
 * を呼び出すプログラムを作成しなさい。ファイル内で見つけた番号をを収集しなさい。
 *
 */
/*
 * - クレジットカード番号は14～16桁と想定。
 *   参考：http://ja.wikipedia.org/wiki/クレジットカードの番号
 */
package jp.co.namihira.java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){
        final String homeDir = System.getProperty("user.home");
        final String regex = "[0-9]\\{14,16\\}";
        final Pattern pattern = Pattern.compile("(.*):.*(" + regex.replaceAll("\\\\", "") + ")");

        final ProcessBuilder builder = new ProcessBuilder("sudo", "grep", "-r", regex, homeDir);

        Process process;
        try {
            process = builder.start();
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        try (final BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            br.lines().forEach(line -> {
                    final Matcher matcher = pattern.matcher(line);
                    if (!matcher.matches()) {
                        return;
                    }
                    System.out.println(matcher.group(1) + ":" + matcher.group(2));
                }
            );
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }

        process.destroy();
    }

}
