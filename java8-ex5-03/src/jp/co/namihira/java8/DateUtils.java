/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;

/**
 * Dateに関するユーティリティクラス
 */
public class DateUtils {

    /**
     * 指定されたpredicateの条件をもつTemporalAdjusterを返します。
     *
     * @param predicate 一致条件
     * @return　指定されたpredicateの条件をもつTemporalAdjuster
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     */
    public static TemporalAdjuster next(final Predicate<LocalDate> predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("predicate must not be null");
        }

        return w -> {
            LocalDate result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            } while (!predicate.test(result));
            return result;
        };
    }

}