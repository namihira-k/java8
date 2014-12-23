/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class DateTimeUtilsTest {

    @Test
    public void test_filter(){
        // prepare
        final Set<ZonedDateTime> plans = new HashSet<>();

        final ZonedDateTime plan = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(13, 00),
                ZoneId.of("Asia/Tokyo"));
        plans.add(plan);

        final ZonedDateTime dummy = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 31),
                LocalTime.of(1, 0),
                ZoneId.of("CET"));
        plans.add(dummy);

        // action
        final ZonedDateTime now = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(12, 00),
                ZoneId.of("Asia/Tokyo"));
        final Set<ZonedDateTime> result = DateTimeUtils.filter(plans, now, 60);

        // check
        assertEquals(1, result.size());
        assertEquals(true, result.contains(plan));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_filter_null_list(){
        // prepare
        // - nothing

        // action
        final ZonedDateTime now = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(12, 00),
                ZoneId.of("Asia/Tokyo"));
        DateTimeUtils.filter(null, now, 60);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_filter_null_base(){
        // prepare
        final Set<ZonedDateTime> plans = new HashSet<>();

        final ZonedDateTime plan = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(13, 00),
                ZoneId.of("Asia/Tokyo"));
        plans.add(plan);

        // action
        DateTimeUtils.filter(plans, null, 60);

        // check
        // - throw Exception
    }

    @Test
    public void test_filter_list_no_contents(){
        // prepare
        final Set<ZonedDateTime> plans = new HashSet<>();

        // action
        final ZonedDateTime now = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(12, 00),
                ZoneId.of("Asia/Tokyo"));
        final Set<ZonedDateTime> result = DateTimeUtils.filter(plans, now, 60);

        // check
        assertEquals(0, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_filter_diff_minus(){
        // prepare
        final Set<ZonedDateTime> plans = new HashSet<>();

        // action
        final ZonedDateTime now = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(12, 00),
                ZoneId.of("Asia/Tokyo"));
        DateTimeUtils.filter(plans, now, -60);

        // check
        // - throw new Exception
    }

}