/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 文字列から部分文字列を抽出することでリテラルでないJavaScript文字列を生成し、
 * getClassメソッドを呼び出しなさい。結果はどのクラスになりますか。
 * それから、そのクラスをjava.lang.String.class.castへ渡しなさい。
 * 何が起きますか。それが起きた理由は何ですか？
 */
/*
 * A.
 * - 結果はそのクラスになりますか。
 * Class<String>
 * jjs> 'hello'.slice(-2).getClass()
 * class java.lang.String
 *
 * - 何が起きますか。
 * 例外が発生した。
 * jjs> java.lang.String.class.cast('hello'.slice(-2).getClass())
 * java.lang.ClassCastException: Cannot cast java.lang.Class to java.lang.String
 *
 * - それが起きた理由は何ですか？
 * JavaScript文字列のclass型はJavaの文字列のclass型（Class<String>）と同等であり、
 * 今回のようなObjectが期待される箇所ではその型（Class<String>）のまま扱われたため。
 * ※≒文字列が期待されていない場所では文字列に変換しない。
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
