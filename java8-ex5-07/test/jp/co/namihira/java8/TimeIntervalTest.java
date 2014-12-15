/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

public class TimeIntervalTest {

    /**
     * ti1:<----->
     * ti2:  <->
     */
    @Test
    public void test_isOverlaped_ti1f_ti2f_ti2t_ti1t(){
        // prepare
        final TimeInterval ti1 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15,  9, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 12, 00));

        final TimeInterval ti2 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 11, 00));

        // action
        final boolean result = ti1.isOverlaped(ti2);

        // check
        assertEquals(true, result);
    }

    /**
     * ti1:  <->
     * ti2:<----->
     */
    @Test
    public void test_isOverlaped_ti2f_ti1f_ti1t_ti2t(){
        // prepare
        final TimeInterval ti1 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 11, 00));

        final TimeInterval ti2 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15,  9, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 12, 00));

        // action
        final boolean result = ti1.isOverlaped(ti2);

        // check
        assertEquals(true, result);
    }

    /**
     * ti1:<->
     * ti2:   <->
     */
    @Test
    public void test_isOverlaped_ti1f_ti1t_ti2f_ti2t(){
        // prepare
        final TimeInterval ti1 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15,  9, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00));

        final TimeInterval ti2 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 11, 00));

        // action
        final boolean result = ti1.isOverlaped(ti2);

        // check
        assertEquals(false, result);
    }

    /**
     * ti1:<--->
     * ti2:  <---->
     */
    @Test
    public void test_isOverlaped_ti1f_ti2f_ti1t_ti2t(){
        // prepare
        final TimeInterval ti1 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15,  9, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 11, 00));

        final TimeInterval ti2 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 12, 00));

        // action
        final boolean result = ti1.isOverlaped(ti2);

        // check
        assertEquals(true, result);
    }

    /**
     * ti1:   <->
     * ti2:<->
     */
    @Test
    public void test_isOverlaped_ti2f_ti2t_ti1f_ti1t(){
        // prepare
        final TimeInterval ti1 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 11, 00));

        final TimeInterval ti2 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15,  9, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00));

        // action
        final boolean result = ti1.isOverlaped(ti2);

        // check
        assertEquals(false, result);
    }

    /**
     * ti1:  <--->
     * ti2:<--->
     */
    @Test
    public void test_isOverlaped_ti2f_ti1f_ti2t_ti1t(){
        // prepare
        final TimeInterval ti1 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 12, 00));

        final TimeInterval ti2 = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15,  9, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 11, 00));

        // action
        final boolean result = ti1.isOverlaped(ti2);

        // check
        assertEquals(true, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_isOverlaped_null(){
        // prepare
        final TimeInterval ti = new TimeInterval(
                LocalDateTime.of(2014, Month.DECEMBER, 15,  9, 00),
                LocalDateTime.of(2014, Month.DECEMBER, 15, 10, 00));

        // action
        ti.isOverlaped(null);

        // check
        // - throw Exception
    }


}