/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * CollectionのサブインタフェースであるCollection2を作成して、
 * デフォルトメソッドとして、void forEachIf(Consumer<T> action, Predicate<T> filter)を追加しなさい。
 * そのメソッドは、filterがtrueを返してきた個々の要素に対してactionを適用します。
 * どのような場面で、そのメソッドを活用できるでしょうか？
 */
/**
 * A.
 *　・コレクション内の特定の条件にあった値のみに処理を実行したい場合（～Stream.filter）。
 *　・
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args) {
        // prepare
        Collection2<String> names = new LinkedListEx<String>();
        names.add("Paul");
        names.add("Peter");
        names.add("Mary");

        // action
        // check
        names.forEachIf(System.out::println, (name) -> name.length() > 4);
    }
}