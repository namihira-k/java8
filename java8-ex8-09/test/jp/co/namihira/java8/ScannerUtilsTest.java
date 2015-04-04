/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScannerUtilsTest {

    private static final int WORDS_IN_FILE = 30;
    private static final int LINES_IN_FILE = 15;
    private static final int NUMBERS_IN_FILE = 2;
    private static final int DOUBLES_IN_FILE = 6;

    private Scanner scanner;

    @Before
    public void setup(){
        scanner = getTestScanner();
    }

    @After
    public void treadown(){
        scanner.close();
    }

    @Test
    public void test_toStreamWord(){
        // setup
        // - nothing

        // action, check
        assertEquals(WORDS_IN_FILE, ScannerUtils.toStreamWord(scanner).count());
    }

    @Test(expected = NullPointerException.class)
    public void test_toStreamWord_null(){
        // setup
        // - nothing

        // action
        ScannerUtils.toStreamWord(null);

        // check
        // - throw Exception
    }

    @Test
    public void test_toStreamLine(){
        // setup
        // - nothing

        // action, check
        assertEquals(LINES_IN_FILE, ScannerUtils.toStreamLine(scanner).count());
    }

    @Test(expected = NullPointerException.class)
    public void test_toStreamLine_null(){
        // setup
        // - nothing

        // action
        ScannerUtils.toStreamLine(null);

        // check
        // - throw Exception
    }

    @Test
    public void test_toStreamNumber(){
        // setup
        // - nothing

        // action, check
        assertEquals(NUMBERS_IN_FILE, ScannerUtils.toStreamNumber(scanner).count());
    }

    @Test(expected = NullPointerException.class)
    public void test_toStreamNumber_null(){
        // setup
        // - nothing

        // action
        ScannerUtils.toStreamNumber(null);

        // check
        // - throw Exception
    }

    @Test
    public void test_toStreamDouble(){
        // setup
        // - nothing

        // action, check
        assertEquals(DOUBLES_IN_FILE, ScannerUtils.toStreamDouble(scanner).count());
    }

    @Test(expected = NullPointerException.class)
    public void test_toStreamDouble_null(){
        // setup
        // - nothing

        // action
        ScannerUtils.toStreamNumber(null);

        // check
        // - throw Exception
    }

    private static Scanner getTestScanner(){
        final String classPath = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath();
        final String fileName = "test.txt";
        final File file = new File(classPath + "/" + fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
        return scanner;
    }

}
