/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class DateTimeUtilsTest {

    @Test
    public void test_getOffsetsAtNow(){
        // prepare
        final Set<String> zoneIds =ZoneId.getAvailableZoneIds();

        // action
        Map<ZoneId, ZoneOffset> results = DateTimeUtils.getOffsetsAtNow();

        // check
        assertEquals(zoneIds.size(), results.size());
        final ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        assertEquals(ZonedDateTime.of(LocalDateTime.now(), tokyo).getOffset(), results.get(tokyo));
    }

    @Test
    public void test_getOffsets(){
        // prepare
        final Set<String> zoneIds =ZoneId.getAvailableZoneIds();
        final LocalDateTime now = LocalDateTime.now();

        // action
        Map<ZoneId, ZoneOffset> results = DateTimeUtils.getOffsets(now);

        // check
        assertEquals(zoneIds.size(), results.size());
        final ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        assertEquals(ZonedDateTime.of(now, tokyo).getOffset(), results.get(tokyo));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOffsets_null(){
        // prepare
        // - nothing

        // action
        DateTimeUtils.getOffsets(null);

        // check
        // - throw Exception
    }

}