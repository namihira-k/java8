/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DateTimeUtils {

    /**
     *　指定した時間未満のオフセットをもつすべてのタイムゾーンを返します
     *
     * @param diffHour UTCとの時間差
     * @return　タイムゾーンの一覧
     *
     * @throws IllegalArgumentException 引数がnullの場合
     */
    public static Set<ZoneId> getZoneIds(final int diffHour){
        if (diffHour < 0) {
            throw new IllegalArgumentException("diffHour must not be negative number");
        }

        final Map<ZoneId, ZoneOffset> map = getOffsetsAtNow();
        final Set<ZoneId> result = map.entrySet().stream()
                .filter(e -> {
                    final long offsetSec = e.getValue().getLong(ChronoField.OFFSET_SECONDS);
                    return Math.abs(offsetSec) < (60 * 60 * diffHour);})
                .map(e -> e.getKey())
                .collect(Collectors.toSet());

        Set<ZoneId> sorted = new TreeSet<>(
                (first, second) -> first.toString().compareTo(second.toString()));
        sorted.addAll(result);
        return sorted;
    }

    /**
     * 現在の時刻インスタンスに対してサポートされるすべてのタイムゾーンにおけるオフセット（UTCとの差）を返します。
     *
     * @return　タイムゾーンとオフセットの一覧
     */
    public static Map<ZoneId, ZoneOffset> getOffsetsAtNow(){
        return getOffsets(LocalDateTime.now());
    }

    /**
     * 指定した時刻インスタンスに対してサポートされるすべてのタイムゾーンにおけるオフセット（UTCとの差）を返します。
     *
     * @return　タイムゾーンとオフセットの一覧
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     */
    public static Map<ZoneId, ZoneOffset> getOffsets(final LocalDateTime localDateTime){
        if (localDateTime == null) {
            throw new IllegalArgumentException("localDateTime must not be null");
        }

        final Set<String> zoneIds =ZoneId.getAvailableZoneIds();
        final Map<ZoneId, ZoneOffset> result = zoneIds.stream().collect(
                Collectors.toMap(
                        id -> ZoneId.of(id),
                        id -> ZonedDateTime.of(localDateTime, ZoneId.of(id)).getOffset()
                 ));

        Map<ZoneId, ZoneOffset> sorted = new TreeMap<>(
                (first, second) -> first.toString().compareTo(second.toString()));
        sorted.putAll(result);
        return sorted;
    }

}
