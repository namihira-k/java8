/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * Scannerを単語、行、整数、あるいは、double値のストリームへ変換するメソッドを書きなさい。
 * ヒント：BufferedReader.linesのソースコードを調べなさい。
 */

package jp.co.namihira.java8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("toStreamWord : ");
        executeAndPrint(s -> ScannerUtils.toStreamWord(s));
        System.out.println("---------------");

        System.out.println("toStreamLine : ");
        executeAndPrint(s -> ScannerUtils.toStreamLine(s));
        System.out.println("---------------");

        System.out.println("toStreamNumber : ");
        executeAndPrint(s -> ScannerUtils.toStreamNumber(s));
        System.out.println("---------------");

        System.out.println("toStreamDouble : ");
        executeAndPrint(s -> ScannerUtils.toStreamDouble(s));
        System.out.println("---------------");
    }

    private static <T> void executeAndPrint(final Function<Scanner, Stream<T>> method){
        // setup
        final Scanner scanner = getTestScanner();

        // action
        Stream<T> result = method.apply(scanner);

        // check
        result.forEachOrdered(System.out::println);

        // teardown
        scanner.close();
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

/*
 * - log
toStreamWord :
﻿The
Project
Gutenberg
EBook
of
War
and
Peace,
by
Leo
Tolstoy.
Title:
War
and
Peace
0.5
Author:
Leo
Tolstoy
12.34
10
Posting
Date:
January
10,
2009
[EBook
#2600]
99.99
1.5
---------------
toStreamLine :
﻿The Project Gutenberg EBook of War and Peace, by Leo Tolstoy.

Title: War and Peace

0.5

Author: Leo Tolstoy 12.34

10

Posting Date: January 10, 2009 [EBook #2600] 99.99

1.5


---------------
toStreamNumber :
10
2009
---------------
toStreamDouble :
0.5
12.34
10.0
2009.0
99.99
1.5
---------------
 */

