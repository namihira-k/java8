/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordFileMappingTest {

    private static final WordFileMapping MAP = new WordFileMapping();

    @Before
    public void setUp(){
        MAP.clear();
    }

    @After
    public void tearDown(){
        MAP.clear();
    }

    @Test
    public void test_add(){
        // prepare
        final File file1 = getFile("sample1.txt");
        final File file2 = getFile("sample2.txt");

        // action
        MAP.add(file1);
        MAP.add(file2);
        final Set<File> result = MAP.get("dog");

        // check
        assertEquals(2, result.size());
        assertEquals(true, result.contains(file1));
        assertEquals(true, result.contains(file2));
    }

    @Test
    public void test_add_single(){
        // prepare
        final File file1 = getFile("sample1.txt");
        final File file2 = getFile("sample2.txt");

        // action
        MAP.add(file1);
        MAP.add(file2);
        final Set<File> result = MAP.get("sheep");

        // check
        assertEquals(1, result.size());
        assertEquals(true, result.contains(file1));
        assertEquals(false, result.contains(file2));
    }

    @Test(expected = NullPointerException.class)
    public void test_add_null(){
        // prepare
        // - nothing

        // action
        MAP.add(null);

        // check
        // - throw Exception
    }

    private File getFile(final String filename) {
        final String CLASS_PATH = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath();
        final String FILE_NAME = filename;
        return new File(CLASS_PATH + "/" + FILE_NAME);
    }

}