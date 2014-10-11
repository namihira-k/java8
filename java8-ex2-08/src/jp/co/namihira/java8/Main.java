/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) メソッドを作成しなさい。
 * そのメソッドは、ストリームである frist とsecond から要素を交互に取り出し、どちらかのストリームから要素がなくなったら停止します。
 */

package jp.co.namihira.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // prepare
        List<Integer> odd = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> even = Arrays.asList(2, 4, 6);
        print("First : ", odd);
        print("Second : ", even);

        // action
        Stream<Integer> resultStream = StreamUtils.zip(odd.stream(), even.stream());

        // check
        List<Integer> result = resultStream.collect(Collectors.toList());
        print("Result : ", result);
        System.out.println("- Size : " + result.size());
    }

    private static <T> void print(final String message, final List<T> list){
        System.out.print(message);
        System.out.println(list.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

}