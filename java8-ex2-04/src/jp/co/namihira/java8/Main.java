/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 配列 int[] values = { 1, 4, 9, 16 }を持っていると想定してください。
 * Stream.of(values)は、何になるでしょうか？
 * 代わりに、intのストリームをどうやって取得できるでしょうか？
 */
/**
 * A.
 * Stream.of(values)は、何になるでしょうか？
 *  -> Stream<int[]>になる。※<int[]> Stream<int[]> java.util.stream.Stream.of(int[] t)
 * 代わりに、intのストリームをどうやって取得できるでしょうか？
 *  -> IntStream.of(int... values) または、Arrays.stream(int[] array)で取得できる。
 *
 *  出力：
 * Stream<int[]>...
 * [I@4617c264
 * IntStream...
 * 1
 * 4
 * 9
 * 16
 * 1
 * 4
 * 9
 * 16
 */

package jp.co.namihira.java8;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // prepare
        int[] values = { 1, 4, 9, 16 };

        // action
        //- int[]のストリームを取得する
        Stream<int[]> arraystream = Stream.of(values);
        //- intのストリームを取得する
        IntStream intstreamByIntStream = IntStream.of(values);
        IntStream intStreamByArrays = Arrays.stream(values);

        // check
        System.out.println("Stream<int[]>...");
        arraystream.forEach(System.out::println);

        System.out.println("IntStream...");
        intstreamByIntStream.forEach(System.out::println);
        intStreamByArrays.forEach(System.out::println);
    }

}