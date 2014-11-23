/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CollectionUtilsTest {

    @Test
    public void test_map(){
        // prepare
        final String e1 = "1";
        final String e2 = "22";
        final String e3 = "333";
        List<String> list = Arrays.asList(e1, e2, e3);

        // action
        List<Integer> result = CollectionUtils.map(list, (t) -> t.length());

        // check
        assertEquals(e1.length(), result.get(0).intValue());
        assertEquals(e2.length(), result.get(1).intValue());
        assertEquals(e3.length(), result.get(2).intValue());
    }

    @Test
    public void test_map_empty_list(){
        // prepare
        List<String> list = Arrays.asList();

        // action
        List<Integer> result = CollectionUtils.map(list, (t) -> t.length());

        // check
        assertEquals(0, result.size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_map_null_list(){
        // prepare
        // - nothing

        // action
        CollectionUtils.map(null, (t) -> "hoge");

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_map_null_f(){
        // prepare
        final String e1 = "1";
        final String e2 = "22";
        final String e3 = "333";
        List<String> list = Arrays.asList(e1, e2, e3);

        // action
        CollectionUtils.map(list, null);

        // check
        // - throw Exception
    }


}