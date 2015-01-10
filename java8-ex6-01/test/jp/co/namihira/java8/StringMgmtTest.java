/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringMgmtTest {

    @Test
    public void test_setStringIfLonger(){
        // prepare
        final String str = "test";

        // action
        StringMgmt.setStringIfLonger(str);

        // check
        assertEquals(str, StringMgmt.get());
    }

    @Test
    public void test_setStringIfLonger_update(){
        // prepare
        final String str1 = "t";
        final String str2 = "test";

        // action
        StringMgmt.setStringIfLonger(str1);
        StringMgmt.setStringIfLonger(str2);

        // check
        assertEquals(str2, StringMgmt.get());
    }

    @Test
    public void test_setStringIfLonger_no_update(){
        // prepare
        final String str1 = "test";
        final String str2 = "t";

        // action
        StringMgmt.setStringIfLonger(str1);
        StringMgmt.setStringIfLonger(str2);

        // check
        assertEquals(str1, StringMgmt.get());
    }

    @Test(expected = NullPointerException.class)
    public void test_setStringIfLonger_null(){
        // prepare
        // - nothing

        // action
        StringMgmt.setStringIfLonger(null);

        // check
        // - throw Exception
    }

}