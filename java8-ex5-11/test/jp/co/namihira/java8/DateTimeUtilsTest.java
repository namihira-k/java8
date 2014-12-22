/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class DateTimeUtilsTest {

    @Test
    public void test_between(){
        // prepare
        final ZonedDateTime t1 = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(14, 5),
                ZoneId.of("CET"));//GMT+01:00

        final ZonedDateTime t2 = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(16, 40),
                ZoneId.of("America/Los_Angeles"));//GMT-08:00

        // action
        final Duration result = DateTimeUtils.between(t1, t2);

        // check
        assertEquals(695, result.toMinutes());
    }

    @Test
    public void test_between_sametime(){
        // prepare
        final ZonedDateTime t1 = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(1, 0),
                ZoneId.of("CET"));

        final ZonedDateTime t2 = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(1, 0),
                ZoneId.of("America/Los_Angeles"));

        // action
        final Duration result = DateTimeUtils.between(t1, t2);

        // check
        assertEquals(9, result.toHours());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_between_null_t1(){
        // prepare
        final ZonedDateTime t2 = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(1, 0),
                ZoneId.of("America/Los_Angeles"));

        // action
        DateTimeUtils.between(null, t2);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_between_null_t2(){
        // prepare
        final ZonedDateTime t1 = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(1, 0),
                ZoneId.of("America/Los_Angeles"));

        // action
        DateTimeUtils.between(t1, null);

        // check
        // - throw Exception
    }

}