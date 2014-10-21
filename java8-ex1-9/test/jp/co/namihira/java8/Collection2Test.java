/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Collection2Test {

    @Test
    public void test_forEachIf(){
        // prepare
        Collection2<Integer> values = new LinkedListEx<Integer>();
        final int VALUE = 1;
        for (int i = 0; i < 10; i++) {
            values.add(VALUE + i);
        }


        // action
        List<Integer> result = new ArrayList<>();
        values.forEachIf(result::add, v -> v > VALUE);

        // check
        values.remove(VALUE);
        assertEquals(values, result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_forEachIf_null_action(){
        // prepare
        Collection2<Integer> values = new LinkedListEx<Integer>();
        final int VALUE = 1;
        for (int i = 0; i < 10; i++) {
            values.add(VALUE + i);
        }

        // action
        values.forEachIf(null, v -> v > VALUE);

        // check
        // throw new IllegalArgumentException
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_forEachIf_null_filter(){
        // prepare
        Collection2<Integer> values = new LinkedListEx<Integer>();
        final int VALUE = 1;
        for (int i = 0; i < 10; i++) {
            values.add(VALUE + i);
        }

        // action
        List<Integer> result = new ArrayList<>();
        values.forEachIf(result::add, null);

        // check
        // throw new IllegalArgumentException
    }



}