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
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // prepare
        final String DIR_PATH = ".";
        File[] beforeSorted = FileUtils.getAllFiles(new File(DIR_PATH));

        System.out.println("Before : ");
        Stream.of(beforeSorted).forEach(System.out::println);
        System.out.println("---------");

        // action
        File[] afterSorted = FileUtils.sort(beforeSorted);

        // check
        System.out.println("After : ");
        Stream.of(afterSorted).forEachOrdered(System.out::println);
    }

}
