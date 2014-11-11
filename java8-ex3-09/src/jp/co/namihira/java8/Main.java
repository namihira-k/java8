/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 指定された順序で、指定されたフィールドを比較するコンパレータを生成する
 * lexicographicComparator(String... fieldNames)メソッドを書きなさい。
 * たとえば、lexicographicComparator("lastname", "firstname")は、
 * 2つのオブジェクトを受け取り、リフレクションを使用して、lastnameフィールドの値を取得します。
 * 2つのオブジェクトのlastnameフィールドが異なれば、その差を返します。
 * 同じであれば、firstnameフィールドに移ります。すべてのフィールドが同じであれば、0を返します。
 */

package jp.co.namihira.java8;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        // prepare
        Person namihira1 = new Person("Kosuke", "Namihira");
        Person namihira2 = new Person("Kosuke", "Namihira");

        // action
        Comparator<Object> comp = ComparatorUtils.lexicographicComparator("firstname", "lastname");

        // check
        final int RESULT = comp.compare(namihira1, namihira2);
        System.out.println("obj1.getFirstname() : " + namihira1.getFirstname());
        System.out.println("obj2.getFirstname() : " + namihira2.getFirstname());
        System.out.println("obj1.getLastname() : " + namihira1.getLastname());
        System.out.println("obj2.getLastname() : " + namihira2.getLastname());
        System.out.println("compare result : " + RESULT);
    }

}