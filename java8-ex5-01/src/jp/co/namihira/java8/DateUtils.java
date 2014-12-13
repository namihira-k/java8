/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.LocalDate;
import java.time.Period;

public class DateUtils {

    /**
     * プログラマーの日を取得します。
     *
     * @param year 年
     * @return プログラマーの日
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     *
     */
    public static LocalDate getProgrammersDay(final int year) {
        if (year < 0) {
            throw new IllegalArgumentException("year must be positive.");
        }

        return LocalDate.of(year, 1, 1).plus(Period.ofDays(255));
    }

    /**
     * プログラマーの日を取得します。
     * ※内部では、plusDaysメソッドを使用しています。
     *
     * @param year 年
     * @return プログラマーの日
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     *
     */
    public static LocalDate getProgrammersDayWithPlusDays(final int year) {
        if (year < 0) {
            throw new IllegalArgumentException("year must be positive.");
        }

        return LocalDate.of(year, 1, 1).plusDays(255);
    }

}