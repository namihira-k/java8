/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.io.File;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * Fileに関するユーティリティクラス
 */
public class FileUtils {

    /**
     * 指定されたディレクトリの下の指定された拡張子のもつ、すべてのファイルを返します。
     * 引数のいずれかがnullの場合、空の配列を返却します。
     * @param pathname ディレクトリ名
     * @param extension　拡張子
     * @return
     */
    public static File[] getAllFiles(final String pathname, final String extension) {
        if (pathname == null || extension == null) {
            return new File[0];
        }

        File root = new File(pathname);
        File[] dirs = getAllSubDirectories(root);

        if (dirs == null) {
            return new File[0];
        }

        SortedSet<File> results = new TreeSet<File>();
        for (File dir : dirs) {
            String[] files = getFiles(dir, extension);
            Stream.of(files).forEach(file -> results.add(new File(buildFilename(dir, file))));
        }

        return results.toArray(new File[]{});
    }

    private static String[] getFiles(final File pathname, final String extension) {
        if (pathname == null || extension == null) {
            return null;
        }

        final String[] files = pathname.list((dir, name) -> {
            File file = new File(buildFilename(dir, name));
            if (!file.isFile()) {
                return false;
            }
            return name.endsWith(extension);
        });
        return files;
    }

    private static File[] getAllSubDirectories(final File pathname) {
        if (pathname == null) {
            return null;
        }

        File[] directories = pathname.listFiles(path -> path.isDirectory());

        if (directories == null) {
            return directories;
        }

        SortedSet<File> results = new TreeSet<File>(Arrays.asList(directories));
        for (File dir : directories) {
            results.addAll(Arrays.asList(getAllSubDirectories(dir)));
        }
        return results.toArray(new File[]{});
    }

    private static String buildFilename(final File pathname, final String filename) {
        if (pathname == null || filename == null) {
            return null;
        }
        return pathname.getPath() + "\\" + filename;
    }

}
