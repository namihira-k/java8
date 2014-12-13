/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void test_next(){
        // prepare
        // 2014-12-13(Sat)
        final LocalDate input = LocalDate.of(2014, Month.DECEMBER, 13);

        // action
        final LocalDate result = input.with(DateUtils.next(w -> w.getDayOfWeek().getValue() < 6));

        // check
        assertEquals(input.plusDays(2), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_next_null(){
        // prepare
        // 2014-12-13(Sat)
        final LocalDate input = LocalDate.of(2014, Month.DECEMBER, 13);

        // action
        input.with(DateUtils.next(null));

        // check
        // - throw Exception
    }


}