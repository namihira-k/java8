/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * java.io.Fileクラスのlist(FilenameFilter)を使用して、指定されたディレクトリの下の指定された拡張子のもつ、
 * すべてのファイルを返すメソッドを書きなさい。
 * FilenameFileterではなく、ラムダ式を使用しなさい。
 * エンクロージングスコープからキャプチャされる変数はどれですか？
 */
/**
 * A.
 * パラメータ変数（pathname, extension）
 */

package jp.co.namihira.java8;

import java.io.File;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // prepare
        final String DIR_PATH = ".";
        final String EXTENSION = ".txt";

        // action
        File[] results = FileUtils.getAllFiles(DIR_PATH, EXTENSION);

        // check
        Stream.of(results).forEach(System.out::println);
    }

}
