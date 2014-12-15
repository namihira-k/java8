/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * Dateに関するユーティリティクラス
 */
public class DateUtils {

    /**
     * 指定した世紀内のすべての13日の金曜日を返します。
     *
     * @param century　世紀
     * @return　13日の金曜日の一覧
     *
     * @throws IllegalArgumentException 引数が0または負の値の場合。
     */
    public static List<LocalDate> get13thOnFriday(final int century) {
        if (century <= 0) {
            throw new IllegalArgumentException("century must be a positive number");
        }

        return getDays((century - 1)*100 + 1, century*100, 13, DayOfWeek.FRIDAY);
    }

    /**
     *　指定した期間内において指定した日と曜日に一致する日を返します。
     *
     * @param fromYear 始点となる年
     * @param toYear　終点となる年
     * @param dayOfMonth 日
     * @param weekDay　曜日
     * @return　一致する日の一覧
     *
     * @throws IllegalArgumentException 年と日について0または負の値の場合。始点と終点となる年が時系列が逆の場合。weekdayがnullの場合。
     */
    public static List<LocalDate> getDays(final int fromYear, final int toYear, final int dayOfMonth, final DayOfWeek weekDay) {
        if (fromYear <= 0 || toYear <= 0 || dayOfMonth <= 0) {
            throw new IllegalArgumentException("args must be a positive number");
        }

        if (fromYear > toYear) {
            throw new IllegalArgumentException("fromYear must be before toYear");
        }

        if (weekDay == null) {
            throw new IllegalArgumentException("weekDay must not be null");
        }

        final LocalDate from = LocalDate.of(fromYear, Month.JANUARY, 1);
        final LocalDate to = LocalDate.of(toYear, Month.DECEMBER, 31);

        final TemporalAdjuster NEXT_TARGET_DAY = TemporalAdjusters.ofDateAdjuster( w -> {
           LocalDate result = w;
           do {
               result = result.with(TemporalAdjusters.next(weekDay));
           } while (result.getDayOfMonth() != dayOfMonth);
           return result;
        });

        final List<LocalDate> results = new ArrayList<>();
        LocalDate tmp = from;
        do {
            tmp = tmp.with(NEXT_TARGET_DAY);
            if (!tmp.isAfter(to)) {
                results.add(tmp);
            }
        } while (!tmp.isAfter(to));
        return results;
    }

}