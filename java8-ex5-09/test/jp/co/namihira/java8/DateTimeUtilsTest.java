/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Set;

import org.junit.Test;

public class DateTimeUtilsTest {

    @Test
    public void test_getZoneIds(){
        // prepare
        final int diffHour = 1;

        // action
        final Set<ZoneId> results =  DateTimeUtils.getZoneIds(diffHour);

        // check
        final LocalDateTime now = LocalDateTime.now();
        final long count = results.stream()
                .filter(r -> {
                    final long offsetSec = ZonedDateTime.of(now, r).getOffset().getLong(ChronoField.OFFSET_SECONDS);
                    return Math.abs(offsetSec) < (60 * 60 * diffHour);})
                .count();

        assertEquals(results.size(), count);
    }

    @Test
    public void test_getZoneIds_zero(){
        // prepare
        final int diffHour = 0;

        // action
        final Set<ZoneId> results =  DateTimeUtils.getZoneIds(diffHour);

        // check
        final LocalDateTime now = LocalDateTime.now();
        final long count = results.stream()
                .filter(r -> {
                    final long offsetSec = ZonedDateTime.of(now, r).getOffset().getLong(ChronoField.OFFSET_SECONDS);
                    return Math.abs(offsetSec) < (60 * 60 * diffHour);})
                .count();

        assertEquals(results.size(), count);
    }


    @Test(expected= IllegalArgumentException.class)
    public void test_getZoneIds_minus(){
        // prepare
        final int diffHour = -1;

        // action
        DateTimeUtils.getZoneIds(diffHour);

        // check
        // - throw Exeption
    }

}