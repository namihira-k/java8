/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.io.File;
import java.util.stream.Stream;

import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void test_getAllFiles(){
        // prepare
        final String DIR_PATH = ".";
        final String EXTENSION = ".txt";

        // action
        File[] results = FileUtils.getAllFiles(DIR_PATH, EXTENSION);

        // check
        final long COUNT_FILES_WITH_EXTENSION = Stream.of(results)
                .filter(r -> (r.isFile()) && (r.getName().endsWith(EXTENSION)))
                .distinct()
                .count();
        assertEquals(results.length, COUNT_FILES_WITH_EXTENSION);
    }

    @Test
    public void test_getAllFiles_no_hit_dir_path(){
        // prepare
        final String DIR_PATH = "hoge";
        final String EXTENSION = ".txt";

        // action
        File[] results = FileUtils.getAllFiles(DIR_PATH, EXTENSION);

        // check
        final long COUNT_FILES_WITH_EXTENSION = Stream.of(results)
                .filter(r -> (r.isFile()) && (r.getName().endsWith(EXTENSION)))
                .distinct()
                .count();
        assertEquals(results.length, COUNT_FILES_WITH_EXTENSION);
    }

    @Test
    public void test_getAllFiles_no_hit_extension(){
        // prepare
        final String DIR_PATH = ".";
        final String EXTENSION = ".hoge";

        // action
        File[] results = FileUtils.getAllFiles(DIR_PATH, EXTENSION);

        // check
        final long COUNT_FILES_WITH_EXTENSION = Stream.of(results)
                .filter(r -> (r.isFile()) && (r.getName().endsWith(EXTENSION)))
                .distinct()
                .count();
        assertEquals(results.length, COUNT_FILES_WITH_EXTENSION);
    }

    @Test
    public void test_getAllFiles_null_extension(){
        // prepare
        final String DIR_PATH = ".";

        // action
        File[] results = FileUtils.getAllFiles(DIR_PATH, null);

        // check
        assertEquals(0, results.length);
    }

    @Test
    public void test_getAllFiles_null_dir_path(){
        // prepare
        final String EXTENSION = ".txt";

        // action
        File[] results = FileUtils.getAllFiles(null, EXTENSION);

        // check
        assertEquals(0, results.length);
    }

}
