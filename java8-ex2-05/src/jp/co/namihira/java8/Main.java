/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Stream.iterate を使用して、Math.random を呼び出すのではなく、線形合成生成器を直接実装して
 * 乱数の無限ストリームを作成しなさい。そのような生成器では、
 * x_0 = seed で始めて、a、c、m、の適切な値に対して、x_(n + 1) = (a * x_n + c) % m を生成します。
 * パラメータa、c、m、seedを受け取り、Stream<Long>を生成するメソッドを実装しなさい。
 * a = 25214903917、c=11、m = 2^48 を試してみなさい
 */

package jp.co.namihira.java8;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // prepare
        final long SEED = 123456L;
        final int SIZE = 1_000_000;

        // action
        Stream<Long> random = new Random(SEED).generate().limit(SIZE);
        Set<Long> result = random.collect(Collectors.toCollection(HashSet::new));

        // check
        if (result.size() != SIZE) {
            throw new RuntimeException("don't work well");
        } else {
            System.out.println("work well");
            System.out.println("result.size : " + result.size());
        }
    }


}