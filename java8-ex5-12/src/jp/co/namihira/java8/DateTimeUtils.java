/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class DateTimeUtils {

    /**
     * 指定された時刻の一覧について、指定された基準時刻からの差（分）に一致する時刻の一覧を返します。
     *
     * @param list 時刻の一覧
     * @param base　基準時刻
     * @param minute 差（分）
     * @return　条件に一致する時刻の一覧
     *
     * @throws IllegalArgumentException 引数がnullの場合。minuteが負の値の場合
     *
     */
    public static Set<ZonedDateTime> filter(final Set<ZonedDateTime> list,
            final ZonedDateTime base, final long minute){
        if (list == null || base == null) {
            throw new IllegalArgumentException("args must not be null");
        }

        if (minute < 0) {
            throw new IllegalArgumentException("minute must be potitive");
        }

        final Set<ZonedDateTime> result =list.stream()
                .filter(p -> {
                    final long diff = Duration.between(base, p).toMinutes();
                    return (diff == 60);
                })
                .collect(Collectors.toSet());

        return result;
    }

}
