/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 27ページの2.3節「filter、map、flatMapメソッド」の characterStream メソッドは、
 * 最初にArrayListを埋めて、それからそのリストをストリームに変換するという具合に、
 * 多少ぎこちないです。代わりに、ストリームを使用して、1行書きなさい。
 * 適切な方法は、0 から s.length() - 1 までの整数のストリームを作成して、
 * それを s::charAt メソッド参照でマップにすることです。
 */

package jp.co.namihira.java8;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        //prepare
        final String ORIGIN = "boat";

        // action
        Stream<Character> results = CharacterStream.characterStream(ORIGIN);

        // check
        final String RESULT_STRING = results.map(Object::toString).collect(Collectors.joining());
        System.out.println("input : " + ORIGIN);
        System.out.println("result : " + RESULT_STRING);
    }

}