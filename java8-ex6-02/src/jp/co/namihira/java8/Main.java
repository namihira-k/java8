/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 増加するID列を生成するためにLongAdderは役立ちますか。
 * その答えの理由を述べなさい。
 */
/**
 * A.
 * 役に立たない。
 * LongAdder#get()で取得できる値は、合計の1つの値であり、
 * 蓄積された複数の値を取得する術がない。
 * 増加する値を生成するためには、LongAdderに対して加算する必要があるが、
 * その過程はローカル変数を保持することを変わらない。（並列で加算していく要件がない限り、ローカル変数で十分である）
 *
 * ※増加するID列＝[1, 2, 3, 4, ....]
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static void main(String[] args) {
        // prepare
        final int size = 100;

        // action
        final LongAdder la = new LongAdder();
        final List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            la.increment();
            numbers.add(la.intValue()); //numbers.add(i)と同等。
        }

        // check
        System.out.println(numbers);
    }

}
