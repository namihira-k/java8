/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void test_get13thOnFriday(){
        // prepare
        // - nothing

        // action
        final List<LocalDate> results = DateUtils.get13thOnFriday(20);

        // check
        final List<LocalDate> expects = DateUtils.getDays(1901, 2000, 13, DayOfWeek.FRIDAY);
        assertEquals(expects.size(), results.size());
        assertEquals(LocalDate.of(1901, Month.SEPTEMBER, 13), results.get(0));
        assertEquals(LocalDate.of(2000, Month.OCTOBER, 13), results.get(results.size() - 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_count13thOnFriday_minus(){
        // prepare
        // - nothing

        // action
        DateUtils.get13thOnFriday(-20);

        // check
        // throw Exception
    }

    @Test
    public void test_getDays(){
        // prepare
        // - nothing

        // action
        final List<LocalDate> results = DateUtils.getDays(2014, 2014, 1, DayOfWeek.MONDAY);

        // check
        assertEquals(2, results.size());
        assertEquals(LocalDate.of(2014, Month.SEPTEMBER, 1), results.get(0));
        assertEquals(LocalDate.of(2014, Month.DECEMBER, 1), results.get(1));
    }

    @Test
    public void test_getDays_two_years(){
        // prepare
        // - nothing

        // action
        final List<LocalDate> results =  DateUtils.getDays(2013, 2014, 13, DayOfWeek.FRIDAY);

        // check
        assertEquals(3, results.size());
        assertEquals(LocalDate.of(2013, Month.SEPTEMBER, 13), results.get(0));
        assertEquals(LocalDate.of(2013, Month.DECEMBER, 13), results.get(1));
        assertEquals(LocalDate.of(2014, Month.JUNE, 13), results.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getDays_minus_number_fromYear(){
        // prepare
        // - nothing

        // action
        DateUtils.getDays(-2014, 2014, 1, DayOfWeek.MONDAY);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getDays_minus_number_toYear(){
        // prepare
        // - nothing

        // action
        DateUtils.getDays(2014, -2014, 1, DayOfWeek.MONDAY);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getDays_minus_number_day(){
        // prepare
        // - nothing

        // action
        DateUtils.getDays(2014, 2014, -1, DayOfWeek.MONDAY);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getDays_null_weekDay(){
        // prepare
        // - nothing

        // action
        DateUtils.getDays(2014, 2014, 1, null);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getDays_reverse_year(){
        // prepare
        // - nothing

        // action
        DateUtils.getDays(2014, 2000, 1, DayOfWeek.MONDAY);

        // check
        // - throw Exception
    }

}