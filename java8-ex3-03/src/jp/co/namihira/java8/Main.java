/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Java1.4は、予約語assertでもって、Java言語にアサーションを追加しました。
 * なぜ、アサーションは、ライブラリの機能として提供されなかったのでしょう。
 * Java8ではライブラリの機能として実装することができますか？
 */
/**
 * A.
 * - なぜ、アサーションは、ライブラリの機能として提供されなかったのでしょう。
 * ライブラリので提供した場合のデメリットは、
 *  1. 評価式の判定が必ず行われるため、実行時にコストがかかる。（※メソッドの引数でも評価はされる。）
 *  2. 上記の判定コストをなくすためには、if分岐で判定ロジックに入らないようにする実装が実装者に強制される。
 * 予約語として定義すれば、assert文全てが実行時のコードから削除（空行扱い）されるため、
 *　  1. 評価コストがかからない。
 *  2. 実装者がif分岐を意識して実装する必要がない。
 *
 *　- Java8ではライブラリの機能として実装することができますか？
 * できる。
 *  1. Java8のラムダ式によって、評価式の判定は必要になったとき（デバックモード時など）に遅延して評価されるため、
 *  メソッドの引数として渡してた場合（Java7以前）のいつでも評価される場合に比べ、実行時のコストが軽減できる。
 *  2. 上記により、実行コストを減らすためのif分岐を実装者に強制することがない。
 *
 * 参考：http://docs.oracle.com/javase/jp/8/technotes/guides/language/assert.html
 *
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