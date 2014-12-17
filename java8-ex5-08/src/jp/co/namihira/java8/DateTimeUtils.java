/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DateTimeUtils {

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
