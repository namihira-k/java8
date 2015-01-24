/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final String hrefPattern = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";

    /**
     * 指定されたページから、リンクを抽出し返します
     *
     * @param page　ページ
     * @return　リンク一覧
     *
     * @throws NullPointerException 引数がnullの場合
     */
    public static List<String> getLinks(final String page){
        Objects.requireNonNull(page);
        return matches(page, hrefPattern);
    }

    private static List<String> matches(String input, String patternString) {
        final Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(input);
        final List<String> result = new ArrayList<>();

        while (matcher.find()) {
           result.add(matcher.group(1));
        }
        return result;
    }

}
