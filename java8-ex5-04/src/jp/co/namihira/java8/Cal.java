/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * ある月のカレンダーを表示するUnixのcalプログラムと同じプログラムを書きなさい。
 * 例えば、java Cal 3 2013を実行すると、次のように表示します。
 *              1  2  3
 *  4  5  6  7  8  9 10
 * 11 12 13 14 15 16 17
 * 18 19 20 21 22 23 24
 * 25 26 27 28 29 30 31
 * 3月1日が金曜日であることを示しています（土曜日と日曜が右端に表示されるようにしなさい）。
 */

package jp.co.namihira.java8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Cal {

    public static void main(String[] args) {
        final Month month = Month.of(Integer.valueOf(args[0]));
        final int year = Integer.valueOf(args[1]);

        showCal(year, month);
    }

    private static void showCal(final int year, final Month month){
        if (year <= 0) {
            throw new IllegalArgumentException("year must be positive.");
        }

        if (month == null) {
            throw new IllegalArgumentException("month must not be null");
        }

        final LocalDate firstDay = LocalDate.of(year, month, 1);
        final LocalDate lastDay = firstDay.with(TemporalAdjusters.lastDayOfMonth());

        final List<LocalDate> days = new ArrayList<>(lastDay.getDayOfMonth());
        final DayOfWeek dayOfWeekOnFirstDay = firstDay.getDayOfWeek();
        final LocalDate padding = null;
        for (int i = 0; i < dayOfWeekOnFirstDay.getValue() - 1; i++) {
            days.add(padding);
        }

        for (int i = firstDay.getDayOfMonth(); i <= lastDay.getDayOfMonth(); i++) {
            days.add(LocalDate.of(year, month, i));
        }

        print(days);
    }

    private static void print(final List<LocalDate> days) {
        final String space = " ";
        final String padding = "  ";

        days.stream().forEachOrdered(day -> {
            if (day == null) {
                System.out.print(padding);
                System.out.print(space);
                return;
            }

            if (day.getDayOfMonth() < 10) {
                System.out.print(" " + day.getDayOfMonth());
            } else {
                System.out.print(day.getDayOfMonth());
            }
            System.out.print(space);

            if (day.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println("");
            }
        });
    }

}