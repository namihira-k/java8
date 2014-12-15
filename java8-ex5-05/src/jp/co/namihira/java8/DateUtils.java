/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Dateに関するユーティリティクラス
 */
public class DateUtils {

    /**
     * 指定した日から現在までの日数を返します。
     *
     * @param from　基準とする日
     * @return　経過日数
     *
     * @throws IllegalArgumentException 引数がnullの場合。未来の日付だった場合。
     */
    public static long countDays(final LocalDate from) {
        if (from == null) {
            throw new IllegalArgumentException("from must not be null");
        }

        final LocalDate now = LocalDate.now();

        if (from.isAfter(now)) {
            throw new IllegalArgumentException("from must be the past day");
        }

        return from.until(now, ChronoUnit.DAYS);
    }

}