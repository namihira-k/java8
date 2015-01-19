/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentHashMapWrapperTest {

    private static final ConcurrentHashMapWrapper MAP = new ConcurrentHashMapWrapper();

    @Before
    public void setUp(){
        MAP.clear();
    }

    @After
    public void tearDown(){
        MAP.clear();
    }

    @Test
    public void test_getKeyWithMaxValue(){
        // prepare
        final String key1 = "key1";
        final Long value1 = 1L;
        MAP.put(key1, value1);

        final String key2 = "key2";
        final Long value2 = 2L;
        MAP.put(key2, value2);

        // action
        final Entry<String, Long> result = MAP.getEntryWithMaxValue();

        // check
        assertEquals(key2, result.getKey());
    }

    @Test
    public void test_getKeyWithMaxValue_single(){
        // prepare
        final String key1 = "key1";
        final Long value1 = 1L;
        MAP.put(key1, value1);

        // action
        final Entry<String, Long> result = MAP.getEntryWithMaxValue();

        // check
        assertEquals(key1, result.getKey());
    }

    @Test
    public void test_getKeyWithMaxValue_triple(){
        // prepare
        final String key1 = "key1";
        final Long value1 = 1L;
        MAP.put(key1, value1);

        final String key2 = "key2";
        final Long value2 = 2L;
        MAP.put(key2, value2);

        final String key3 = "key3";
        final Long value3 = -2L;
        MAP.put(key3, value3);

        // action
        final Entry<String, Long> result = MAP.getEntryWithMaxValue();

        // check
        assertEquals(key2, result.getKey());
    }

    @Test
    public void test_getKeyWithMaxValue_no_contents(){
        // prepare
        // - nothing

        // action
        final Entry<String, Long> result = MAP.getEntryWithMaxValue();

        // check
        assertEquals(null, result);
    }

}