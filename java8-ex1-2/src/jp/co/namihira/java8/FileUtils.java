/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.io.File;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * ファイル操作に関するユーティリティクラス
 */
public class FileUtils {

    /**
     * 指定されたディレクトリの下のすべてのサブディレクトリを返します。
     * nullが指定された場合、空の配列を返却します。
     * ※内部の実装でラムダを使用します。
     *
     * @param pathname ディレクトリ
     * @return すべてのサブディレクトリ
     */
    public static File[] getAllSubDirsWithLambda(final File pathname) {
        if (pathname == null) {
            return new File[0];
        }

        File[] directories = pathname.listFiles(path -> path.isDirectory());

        if (directories == null) {
            return new File[0];
        }

        SortedSet<File> results = new TreeSet<>(Arrays.asList(directories));
        for (File dir : directories) {
            results.addAll(Arrays.asList(getAllSubDirsWithLambda(dir)));
        }
        return results.toArray(new File[]{});
    }

    /**
     * 指定されたディレクトリの下のすべてのサブディレクトリを返します。
     * nullが指定された場合、空の配列を返却します。
     * ※内部の実装でメソッド参照を使用します。
     *
     * @param pathname ディレクトリ
     * @return すべてのサブディレクトリ
     */
    public static File[] getAllSubDirsWithMethodReference(final File pathname) {
        if (pathname == null) {
            return new File[0];
        }

        File[] directories = pathname.listFiles(File::isDirectory);

        if (directories == null) {
            return new File[0];
        }

        SortedSet<File> results = new TreeSet<>(Arrays.asList(directories));
        for (File dir : directories) {
            results.addAll(Arrays.asList(getAllSubDirsWithMethodReference(dir)));
        }
        return results.toArray(new File[]{});
    }

}
