/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtils {

    /**
     * ローカル時刻と、経過時間と、目的地のタイムゾーンを指定して、目的地の到着時間を返します。
     *
     *
     * @param dateTimeDeparture　出発地でのローカル時間
     * @param destination　目的地のタイムゾーン
     * @param hour　経過時間（時）
     * @param minute　経過時間（分）
     * @return　目的地の到着時間
     *
     * @throws IllegalArgumentException 引数がnullの場合。引数が負の値の場合。
     */
    public static ZonedDateTime getArrivalTime(final ZonedDateTime dateTimeDeparture,
            final ZoneId destination, final int hour, final int minute){
        if (dateTimeDeparture == null || destination == null) {
            throw new IllegalArgumentException("args must not be null");
        }

        if (hour < 0 || minute < 0) {
            throw new IllegalArgumentException("time must be positive number");
        }

        final ZonedDateTime arrivalTime = dateTimeDeparture.plusHours(hour).plusMinutes(minute);
        return arrivalTime.toInstant().atZone(destination);
    }

}
