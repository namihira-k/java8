/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * (指定された日付の午前10時から午前11時といった)カレンダーイベントに適した、
 * 時刻のインターバルを表すTimeIntervalクラスを実装しなさい。
 * 2つのインターバルが重なっているかを検査するメソッドを提供しなさい。
 */
/**
 * A.
 * - ログ
 * TimeInterval 1 : 2014-12-15T09:00 -> 2014-12-15T12:00
 * TimeInterval 2 : 2014-12-15T10:00 -> 2014-12-15T11:00
 * isOverlaped? : true
 * TimeInterval 1 : 2014-12-15T09:00 -> 2014-12-15T10:00
 * TimeInterval 2 : 2014-12-15T11:00 -> 2014-12-15T12:00
 * isOverlaped? : false
 */

package jp.co.namihira.java8;

import java.time.LocalDateTime;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        runOverlapCase();
        runNonOverlapCase();
    }

    private static void runOverlapCase(){
        // prepare
        final TimeInterval ti1 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15,  9, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 12, 00));

        final TimeInterval ti2 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 11, 00));

        // action
        final boolean result = ti1.isOverlaped(ti2);

        // check
        System.out.println("TimeInterval 1 : " + ti1.getFrom() + " -> " + ti1.getTo());
        System.out.println("TimeInterval 2 : " + ti2.getFrom() + " -> " + ti2.getTo());
        System.out.println("isOverlaped? : " + result);
    }

    private static void runNonOverlapCase(){
        // prepare
        final TimeInterval ti1 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15,  9, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00));

        final TimeInterval ti2 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15, 11, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 12, 00));

        // action
        final boolean result = ti1.isOverlaped(ti2);

        // check
        System.out.println("TimeInterval 1 : " + ti1.getFrom() + " -> " + ti1.getTo());
        System.out.println("TimeInterval 2 : " + ti2.getFrom() + " -> " + ti2.getTo());
        System.out.println("isOverlaped? : " + result);
    }

}
