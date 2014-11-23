/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * T型の対となる2つのオブジェクトを表すPair<T>クラスに対するmap操作を定義しなさい。
 */
/**
 * A.
 * - ログ
 * Before :
 * - class java.lang.String
 * - hoge
 * - foo
 * After :
 * - class java.lang.Integer
 * - 4
 * - 3
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args) {
        // prepare
        final String left = "hoge";
        final String right = "foo";
        Pair<String> pair = new Pair<>(left, right);

        // action
        Pair<Integer> result = pair.map((t) -> t.length());

        // check
        System.out.println("Before : ");
        System.out.println(" - " + pair.getLeft().getClass());
        System.out.println(" - " + pair.getLeft());
        System.out.println(" - " + pair.getRight());
        System.out.println("After : ");
        System.out.println(" - " + result.getLeft().getClass());
        System.out.println(" - " + result.getLeft());
        System.out.println(" - " + result.getRight());
    }

}