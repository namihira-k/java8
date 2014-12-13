/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void test_getProgrammersDay(){
        // prepare
        final int year = 2014;
        final LocalDate expected = DateUtils.getProgrammersDayWithPlusDays(year);

        // action
        final LocalDate result = DateUtils.getProgrammersDay(year);

        // check
        assertTrue(result.isEqual(expected));
    }

    @Test
    public void test_getProgrammersDay_zero(){
        // prepare
        final int year = 0;
        final LocalDate expected = DateUtils.getProgrammersDayWithPlusDays(year);

        // action
        final LocalDate result = DateUtils.getProgrammersDay(year);

        // check
        assertTrue(result.isEqual(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getProgrammersDay_minus(){
        // prepare
        final int year = -2014;

        // action
        DateUtils.getProgrammersDay(year);

        // check
        // - throw Exception
    }


}