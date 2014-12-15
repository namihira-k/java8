/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 今まで、あなたが生きてきた日数をを表示するプログラムを書きなさい。
 */
/**
 * A.(ログ)
 * from Brithday : 1985-04-28
 * to Now : 2014-12-15
 * Count Days : 10823
 */

package jp.co.namihira.java8;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        // prepare
        final LocalDate from = LocalDate.of(1985, Month.APRIL, 28);

        // action
        final long count = DateUtils.countDays(from);

        // check
        System.out.println("from Brithday : " + from);
        System.out.println("to Now : " + LocalDate.now());
        System.out.println("Count Days : " + count);
    }
}