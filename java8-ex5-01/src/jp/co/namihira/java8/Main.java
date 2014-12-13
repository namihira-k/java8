/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * plusDaysを使用しないで、プログラマーの日を計算しなさい。
 */
/**
 * A.
 * （ログ）
 * Expected : 2014-09-13
 * Result : 2014-09-13
 */

package jp.co.namihira.java8;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // prepare
        final int year = 2014;
        final LocalDate expected = DateUtils.getProgrammersDayWithPlusDays(year);

        // action
        final LocalDate result = DateUtils.getProgrammersDay(year);

        // check
        System.out.println("Expected : " + expected);
        System.out.println("Result : " + result);
    }

}