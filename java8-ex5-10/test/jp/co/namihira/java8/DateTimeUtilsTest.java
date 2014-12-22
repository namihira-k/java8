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

import org.junit.Test;

public class DateTimeUtilsTest {

    /**
     * @see http://flyteam.jp/airline_route/fra_lax/flight_schedule
     */
    @Test
    public void test_getZoneIds(){
        // prepare
        final ZonedDateTime dateTimeInLosAngeles = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(3, 5),
                ZoneId.of("America/Los_Angeles"));
        final ZoneId frankfurt = ZoneId.of("CET");

        // action
        final ZonedDateTime result = DateTimeUtils.getArrivalTime(dateTimeInLosAngeles, frankfurt, 10, 50);

        // check
        assertEquals(2014, result.getYear());
        assertEquals(Month.JANUARY, result.getMonth());
        assertEquals(1, result.getDayOfMonth());
        assertEquals(22, result.getHour());
        assertEquals(55, result.getMinute());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getZoneIds_null_dateTimeDeparture(){
        // prepare
        final ZoneId frankfurt = ZoneId.of("CET");

        // action
        DateTimeUtils.getArrivalTime(null, frankfurt, 10, 50);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getZoneIds_null_destination(){
        // prepare
        final ZonedDateTime dateTimeInLosAngeles = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(1, 1),
                ZoneId.of("America/Los_Angeles"));

        // action
        DateTimeUtils.getArrivalTime(dateTimeInLosAngeles, null, 10, 50);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getZoneIds_minus_hour(){
        // prepare
        final ZonedDateTime dateTimeInLosAngeles = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(1, 1),
                ZoneId.of("America/Los_Angeles"));
        final ZoneId frankfurt = ZoneId.of("CET");

        // action
        DateTimeUtils.getArrivalTime(dateTimeInLosAngeles, frankfurt, -10, 50);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getZoneIds_minus_minute(){
        // prepare
        final ZonedDateTime dateTimeInLosAngeles = ZonedDateTime.of(
                LocalDate.of(2014, Month.JANUARY, 1),
                LocalTime.of(1, 1),
                ZoneId.of("America/Los_Angeles"));
        final ZoneId frankfurt = ZoneId.of("CET");

        // action
        DateTimeUtils.getArrivalTime(dateTimeInLosAngeles, frankfurt, 10, -10);

        // check
        // - throw Exception
    }



}