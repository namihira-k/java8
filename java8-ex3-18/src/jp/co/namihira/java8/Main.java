/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 71ページの3.8節「例外の取り扱い」のuncheckedメソッドを次の内容に従って実装しなさい。
 * 具体的には、チェックされる例外をスローするラムダ式からFunction<T, U>を生成するようにしなさい。
 * 任意の例外をスローする抽象メソッドを持つ関数型インタフェースを見つけるか、
 * 作成する必要があることに注意しなさい。
 */
/**
 * A.
 * - ログ（一部）
 * Exception in thread "main" java.lang.RuntimeException: java.nio.file.NoSuchFileException: \etc\hoge
 * Caused by: java.nio.file.NoSuchFileException: \etc\hoge
 */
package jp.co.namihira.java8;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        Function<Path, String> function = ThreadUtils.unchecked(
                    (path) -> new String(Files.readAllBytes(path), StandardCharsets.UTF_8)
                );
        String str = function.apply(Paths.get("/etc/hoge"));

        // check
        // - no Compile Error
        // - throw RuntimeException
    }

}