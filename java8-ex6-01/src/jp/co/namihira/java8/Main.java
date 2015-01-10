/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 多数のスレッドが更新する最大長の文字列を管理するプログラムを書きなさい。
 * AtomicReferenceと適切な累積関数を使用しなさい。
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args) {
        // prepare
        final String str1 = "a";
        final String str2 = "aa";
        final String str3 = "aaa";

        // action, check
        StringMgmt.setStringIfLonger(str2);
        System.out.println("- after set aa");
        System.out.println(StringMgmt.get());

        // action, check
        StringMgmt.setStringIfLonger(str1);
        System.out.println("- after set a");
        System.out.println(StringMgmt.get());

        // action, check
        StringMgmt.setStringIfLonger(str3);
        System.out.println("- after set aaa");
        System.out.println(StringMgmt.get());
    }

}
