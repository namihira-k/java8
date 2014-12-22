/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * ロサンジェルスからフランクフルト行きの便は、ローカル時刻の3時5分に出発し、
 * 10時間50分の飛行です。 何時に到着しますか。
 * このような計算を処理できるプログラムを書きなさい。
 */
/**
 * A.
 * フランクフルトでの時刻で、22時55分に到着する。
 *
 * 参考
 * - http://flyteam.jp/airline_route/fra_lax/flight_schedule
 */

package jp.co.namihira.java8;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        // prepare
        final ZonedDateTime dateTimeInLosAngeles = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(3, 5),
                ZoneId.of("America/Los_Angeles"));
        final ZoneId frankfurt = ZoneId.of("CET");

        // action
        final ZonedDateTime result = DateTimeUtils.getArrivalTime(dateTimeInLosAngeles, frankfurt, 10, 50);

        // check
        System.out.println("Departure[LA] : " + dateTimeInLosAngeles);
        System.out.println("Destination[Frankfurt] : " + result);
    }

}
