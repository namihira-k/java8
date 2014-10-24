/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void test_sort(){
        // prepare
        final String DIR_PATH = ".";
        File[] beforeSorted = FileUtils.getAllFiles(new File(DIR_PATH));

        // action
        File[] afterSorted = FileUtils.sort(beforeSorted);

        // check
        int cnt_dirs = 0;
        for (int i = 0; i < afterSorted.length; i++) {
            if (afterSorted[i].isFile()) {
                break;
            }
            cnt_dirs++;
            if (i == 0) {
                continue;
            }
            assertTrue(afterSorted[i].getAbsolutePath().compareTo(afterSorted[i - 1].getAbsolutePath()) > 0);
        }

        int cnt_files = 0;
        for (int i = cnt_dirs; i < afterSorted.length; i++) {
            if (afterSorted[i].isDirectory()) {
                fail("must not include dirs");
            }
            cnt_files++;
            if (i == cnt_dirs) {
                continue;
            }
            assertTrue(afterSorted[i].getAbsolutePath().compareTo(afterSorted[i - 1].getAbsolutePath()) > 0);
        }

        assertEquals(afterSorted.length, cnt_dirs + cnt_files);
    }

    @Test
    public void test_sort_no_hit(){
        // prepare
        final String DIR_PATH = "hoge";
        File[] beforeSorted = FileUtils.getAllFiles(new File(DIR_PATH));

        // action
        File[] afterSorted = FileUtils.sort(beforeSorted);

        // check
        assertEquals(0, afterSorted.length);
    }

    @Test
    public void test_sort_null(){
        // prepare
        // - nothing

        // action
        File[] afterSorted = FileUtils.sort(null);

        // check
        assertEquals(0, afterSorted.length);
    }

    @Test
    public void test_sort_empty_array(){
        // prepare
        // - nothing

        // action
        File[] afterSorted = FileUtils.sort(new File[0]);

        // check
        assertEquals(0, afterSorted.length);
    }

}
