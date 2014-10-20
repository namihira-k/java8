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

/**
 * Fileに関するユーティリティクラス
 */
public class FileUtils {

    /**
     *　与えられた配列について、ファイルの前にディレクトリが来るようにし、
     * ファイルとディレクトリのそれぞれのグループについてパス名でソートします。
     * @param files　ソート対象のファイルオブジェクトの配列
     * @return　ソートされたファイルオブジェクトの配列
     */
    public static File[] sort(File[] files) {
        if (files == null) {
            return new File[0];
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

    /**
     * 指定されたパス配下のファイルとディレクトリを返却します。
     * @param pathname　パス名
     * @return　ファイルとディレクトリの一覧
     */
    public static File[] getAllFiles(final File pathname) {
        if (pathname == null) {
            return new File[0];
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

}
