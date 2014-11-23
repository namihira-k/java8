/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * staticメソッドである<T, U> List<U> map(List<T>, Function<T, U>)を
 * 提供しなさい。
 */

package jp.co.namihira.java8;

import java.util.Arrays;
import java.util.List;

public class Main<T> {

    public static void main(String[] args) {
        // prepare
        final String e1 = "1";
        final String e2 = "22";
        final String e3 = "333";
        List<String> input = Arrays.asList(e1, e2, e3);

        // action
        List<Integer> result = CollectionUtils.map(input, (t) -> t.length());

        // check
        System.out.println("Input(String) : ");
        System.out.println(input);
        System.out.println("Result(String.length) : ");
        System.out.println(result);
    }

}