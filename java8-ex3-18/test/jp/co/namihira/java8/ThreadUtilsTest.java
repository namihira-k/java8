/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

import org.junit.Test;

public class ThreadUtilsTest {

    @Test(expected = RuntimeException.class)
    public void test_unchecked(){
        // prepare
        // - nothing

        // action
        Function<Path, String> function = ThreadUtils.unchecked(
                    (path) -> new String(Files.readAllBytes(path), StandardCharsets.UTF_8)
                );
        String str = function.apply(Paths.get("/etc/hoge"));

        // check
        // - no Compile Error
        // - throw RuntimeException
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_unchecked_null(){
        // prepare
        // - nothing

        // action
        ThreadUtils.unchecked(null);

        // check
        // - throw Exception
    }


}