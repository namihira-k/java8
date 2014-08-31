/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * java.io.FileクラスのlistFiles(FileFilter)メソッドとisDirectoryメソッドを使用して、
 * 指定されたディレクトリの下のすべてのサブディレクトリを返すメソッドを書きなさい。
 * FileFilterオブジェクトではなく、ラムダ式を使用しなさい。
 * 同じことをメソッド参照を用いて行いなさい。
 */

package jp.co.namihira.java8;

import java.io.File;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;


public class Main {

	public static void main(String[] args) {
        File root = new File(".");

        System.out.println("with Lambda");
        File[] directories = getAllSubDirectoriesWithLambda(root);
	    Stream.of(directories).forEach(System.out::println);

        System.out.println("with Method Reference");
        directories = getAllSubDirectoriesWithMethodReference(root);
        Stream.of(directories).forEach(System.out::println);
	}

	/**
	 * Lambda version.
	 * @param pathname
	 * @return
	 */
    private static File[] getAllSubDirectoriesWithLambda(final File pathname) {
        if (pathname == null) {
            return null;
        }

        File[] directories = pathname.listFiles(path -> path.isDirectory());

        if (directories == null) {
            return directories;
        }

        SortedSet<File> results = new TreeSet<File>(Arrays.asList(directories));
        for (File dir : directories) {
            results.addAll(Arrays.asList(getAllSubDirectoriesWithLambda(dir)));
        }
        return results.toArray(new File[]{});
    }

    /**
     * Method Reference version.
     * @param pathname
     * @return
     */
    private static File[] getAllSubDirectoriesWithMethodReference(final File pathname) {
        if (pathname == null) {
            return null;
        }

        File[] directories = pathname.listFiles(File::isDirectory);

        if (directories == null) {
            return directories;
        }

        SortedSet<File> results = new TreeSet<File>(Arrays.asList(directories));
        for (File dir : directories) {
            results.addAll(Arrays.asList(getAllSubDirectoriesWithLambda(dir)));
        }
        return results.toArray(new File[]{});
    }

}
