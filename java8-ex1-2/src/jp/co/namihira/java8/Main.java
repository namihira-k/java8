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
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
	    // prepare
        File root = new File(".");

        // action
        File[] directories = FileUtils.getAllSubDirsWithLambda(root);

        // check
        System.out.println("with Lambda");
        Stream.of(directories).forEach(System.out::println);

        // action
        directories = FileUtils.getAllSubDirsWithMethodReference(root);

        // check
        System.out.println("with Method Reference");
        Stream.of(directories).forEach(System.out::println);
	}

}
