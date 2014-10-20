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
    public void test_getAllSubDirsWithLambda(){
        // prepare
        File root = new File(".");

        // action
        File[] directories = FileUtils.getAllSubDirsWithLambda(root);

        // check
        final long COUNT_DIRS = Stream.of(directories).filter(d -> d.isDirectory()).distinct().count();
        assertEquals(COUNT_DIRS, directories.length);
    }

    @Test
    public void test_getAllSubDirsWithLambda_no_hit(){
        // prepare
        File root = new File("hoge");

        // action
        File[] directories = FileUtils.getAllSubDirsWithLambda(root);

        // check
        assertEquals(0, directories.length);
    }

    @Test
    public void test_getAllSubDirsWithLambda_null(){
        // prepare
        // - nothing

        // action
        File[] directories = FileUtils.getAllSubDirsWithLambda(null);

        // check
        assertEquals(0, directories.length);
    }

    @Test
    public void test_getAllSubDirsWithMethodReference(){
        // prepare
        File root = new File(".");

        // action
        File[] directories = FileUtils.getAllSubDirsWithMethodReference(root);

        // check
        final long COUNT_DIRS = Stream.of(directories).filter(d -> d.isDirectory()).distinct().count();
        assertEquals(COUNT_DIRS, directories.length);
    }

    @Test
    public void test_getAllSubDirsWithMethodReference_no_hit(){
        // prepare
        File root = new File("hoge");

        // action
        File[] directories = FileUtils.getAllSubDirsWithMethodReference(root);

        // check
        assertEquals(0, directories.length);
    }

    @Test
    public void test_getAllSubDirsWithMethodReference_null(){
        // prepare
        // - nothing

        // action
        File[] directories = FileUtils.getAllSubDirsWithMethodReference(null);

        // check
        assertEquals(0, directories.length);
    }

}
