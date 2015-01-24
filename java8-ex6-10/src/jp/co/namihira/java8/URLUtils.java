/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class URLUtils {

    /**
     * 指定されたURLのページコンテンツを取得します
     *
     * @param urlStr　URL
     * @return ページコンテンツ
     *
     * @throws NullPointerException 引数がnullの場合
     */
    public static String readPage(final String urlStr){
        Objects.requireNonNull(urlStr);

        final StringBuilder builder = new StringBuilder();
        Scanner in = null;
        try {
            in = new Scanner(new URL(urlStr).openStream());
            while (in.hasNextLine()) {
                builder.append(in.nextLine());
                builder.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            in.close();
        }
        return builder.toString();
    }

}
