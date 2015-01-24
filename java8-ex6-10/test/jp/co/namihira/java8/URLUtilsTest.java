/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import org.junit.Test;

public class URLUtilsTest {

    @Test
    public void test_readPage(){
        // prepare
        final String url = "http://horstmann.com";

        // action
        final String result = URLUtils.readPage(url);

        // check
        assertNotNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void test_readPage_null(){
        // prepare
        // - nothing

        // action
        URLUtils.readPage(null);

        // check
        // - nothing
    }

}