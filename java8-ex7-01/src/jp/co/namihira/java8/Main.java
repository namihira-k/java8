/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * たとえば、ZoneDateTimeクラスなど、試してみたいJava APIを選びなさい。
 * そのうえで、オブジェクト生成、メソッドの呼び出し、戻り値の表示など、jjsを使った実験を
 * 行いなさい。また、Javaでテストプログラムを書くよりは簡単かどうかを検証しなさい。
 */
/**
 * A.
 * - ZoneDateTime：正常
 * jjs> var zoneDateTime = java.time.ZonedDateTime.now()
 * jjs> var year = zoneDateTime.getYear()
 * jjs> year
 * 2015
 *
 * - ストリーム：正常
 * jjs> var values = java.util.Arrays.asList(1,2,3)
 * jjs> var count = values.stream().count()
 * jjs> count
 * 3
 *
 * - ラムダ式：エラー
 * jjs> values.stream().forEach((v) -> System.out.println(v))
 * ECMAScript Exception: SyntaxError: <shell>:1:29 Expected an operand but found >
 * values.stream().forEach((v) -> System.out.println(v))
 *
 * jjs> values.stream().forEach(System.out::println)
 * ECMAScript Exception: SyntaxError: <shell>:1:34 Expected , but found :
 * values.stream().forEach(System.out::println)
 *
 * - Javaでテストプログラムを書くよりは簡単かどうかを検証しなさい。
 * 以下の理由により、簡単ではないという印象。（※操作に慣れていない）
 *    ・クラスのパッケージを含んだ指定について、補完機能がないと素早く書けない。
 *    ・ラムダ式について実行できない。
 * ただし、以下の点が優位な点と感じた。
 *    ・コンパイルなしで実行できるため、IDEやビルドツールの環境整備が不必要である点。
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        // - nothing

        // check
        // - nothing
    }

}
