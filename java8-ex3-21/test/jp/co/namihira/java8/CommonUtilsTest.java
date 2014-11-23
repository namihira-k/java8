/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class CommonUtilsTest {

    @Test
    public void test_map(){
        // prepare
        final String str = "hoge";
        final ExecutorService executor =  Executors.newSingleThreadExecutor();
        final Future<String> f = executor.submit(() -> str);

        // action
        final Future<Integer> result = CommonUtils.map(
                f,
                (t) -> {
                    return t.length();
                });

        // check
        try {
            assertEquals(str.length(), result.get().intValue());
            assertEquals(true, result.isDone());
            assertEquals(false, result.isCancelled());
        } catch (InterruptedException | ExecutionException e) {
            fail("must not reach");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_map_null_f(){
        // prepare
        // - nothing

        // action
        CommonUtils.map(null, (t) -> "");

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_map_null_action(){
        // prepare
        final String str = "hoge";
        final ExecutorService executor =  Executors.newSingleThreadExecutor();
        final Future<String> f = executor.submit(() -> str);

        // action
        CommonUtils.map(f, null);

        // check
        // - throw Exception
    }

}