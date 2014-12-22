/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 帰りの便は、フランクフルトを14時5分に出発し、ロサンジェルスに16時40分に到着します。
 * 飛行時間は、何時間何分ですか。
 * このような計算を処理できるプログラムを書きなさい。
 */
/**
 * A.
 * 11時間35分
 *
 * - 出力ログ
 * Departure[Frankfurt] : 2014-01-01T14:05+01:00[CET]
 * Destination[LA] : 2014-01-01T16:40-08:00[America/Los_Angeles]
 * Diff : 11[h]35[m]
 */

package jp.co.namihira.java8;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        // prepare
        final ZonedDateTime from = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(14, 5),
                ZoneId.of("CET"));//GMT+01:00

        final ZonedDateTime to = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(16, 40),
                ZoneId.of("America/Los_Angeles"));//GMT-08:00

        // action
        final Duration result = DateTimeUtils.between(from, to);

        // check
        System.out.println("Departure[Frankfurt] : " + from);
        System.out.println("Destination[LA] : " + to);
        System.out.println("Diff : " + result.toHours() + "[h]" + result.toMinutes() % 60 + "[m]");
    }

}
