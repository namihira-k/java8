/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 現在の時刻インスタンスに対してサポートされるすべてのタイムゾーンにおいて、
 * 今日の日付のオフセット（UTCとの差）を取得しなさい。
 * その際、ZoneId.getAvailableIdsをストリームへ変換してから、ストリーム操作を
 * 使用することによって取得しなさい。
 */

package jp.co.namihira.java8;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        Map<ZoneId, ZoneOffset> results = DateTimeUtils.getOffsetsAtNow();

        // check
        System.out.println("num of ZoneIds : " + results.size());
        System.out.println("Offsets : ");
        results.entrySet().stream().forEach(System.out::println);
    }

}
