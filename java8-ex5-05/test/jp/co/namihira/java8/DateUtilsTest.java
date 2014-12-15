/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void test_countDays_namihira_birthday(){
        // prepare
        final LocalDate from = LocalDate.of(1985, Month.APRIL, 28);

        // action
        final long count = DateUtils.countDays(from);

        // check
        assertEquals(from.until(LocalDate.now(), ChronoUnit.DAYS), count);
    }

    @Test
    public void test_countDays_a_year_ago(){
        // prepare
        final LocalDate from = LocalDate.of(2013, Month.DECEMBER, 15);

        // action
        final long count = DateUtils.countDays(from);

        // check
        assertEquals(from.until(LocalDate.now(), ChronoUnit.DAYS), count);
    }


    @Test
    public void test_countDays_now(){
        // prepare
        final LocalDate now = LocalDate.now();

        // action
        final long count = DateUtils.countDays(now);

        // check
        assertEquals(0, count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_countDays_date_in_future(){
        // prepare
        final LocalDate from = LocalDate.of(3000, Month.JANUARY, 1);

        // action
        DateUtils.countDays(from);

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_countDays_null(){
        // prepare
        // - nothing

        // action
        DateUtils.countDays(null);

        // check
        // - throw Exception
    }

}