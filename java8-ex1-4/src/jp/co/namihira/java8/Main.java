/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Fileオブジェクトの配列が与えられたとします。その配列をソートして、ファイルの前にディレクトリが来るようにし、
 * ファイルとディレクトリのそれぞれのグループではパス名でソートされるようにしなさい。
 * Comparatorではなく、ラムダ式を使用しなさい。
 */

package jp.co.namihira.java8;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // prepare
        final String DIR_PATH = ".";
        File[] beforeSorted = getAllFiles(new File(DIR_PATH));

        System.out.println("Before : ");
        Stream.of(beforeSorted).forEach(System.out::println);
        System.out.println("---------");

        // action
        File[] afterSorted = sort(beforeSorted);

        // check
        System.out.println("After : ");
        Stream.of(afterSorted).forEachOrdered(System.out::println);
    }

    private static File[] getAllFiles(final File pathname) {
        if (pathname == null) {
            return null;
        }

        File[] files = pathname.listFiles();

        if (files == null) {
            return files;
        }

        Set<File> results = new HashSet<File>(Arrays.asList(files));

        Stream.of(files)
            .filter(f -> f.isDirectory())
            .forEach(f -> results.addAll(Arrays.asList(getAllFiles(f))));

        return results.toArray(new File[]{});
    }

    private static File[] sort(File[] files) {
        if (files == null) {
            return null;
        }

        List<File> results = new LinkedList<File>();

        File[] tmp = Stream.of(files).filter(f -> f.isDirectory()).toArray(File[]::new);
        Arrays.sort(tmp, (first, second) -> first.getPath().compareTo(second.getPath()));
        results.addAll(Arrays.asList(tmp));

        tmp = Stream.of(files).filter(f -> f.isFile()).toArray(File[]::new);
        Arrays.sort(tmp, (first, second) -> first.getPath().compareTo(second.getPath()));
        results.addAll(Arrays.asList(tmp));

        return results.toArray(new File[]{});
    }

}
