/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 文字に関するStreamを操作するクラス
 */
public class CharacterStream {

    /**
     * 指定された文字列の文字分割したStreamを返します。
     * nullが指定された場合、空のStreamが返されます。
     * 補足：実装は、ストリームを利用しています。
     * @param s
     * @return　文字分割されたStream
     */
    public static Stream<Character> characterStream(String s) {
        if (s == null) {
            return Stream.empty();
        }

        Stream<Character> result = Stream.iterate(0, n -> n + 1).limit(s.length()).map(s::charAt);
        return result;
    }

    /**
     * 指定された文字列の文字分割したStreamを返します。
     * 補足：実装は、リストを利用しています。
     * @param s
     * @return　文字分割されたStream
     */
    public static Stream<Character> characterStreamWithArray(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray())
            result.add(c);
        return result.stream();
    }

}