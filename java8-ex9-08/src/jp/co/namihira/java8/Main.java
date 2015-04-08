/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 224ページの9.3.3節「数値型の比較する」のPointクラスの
 * compareToメソッドを、Interger.compareToを使用せずに実装しなさい。
 */
/*
 * A.
 * - log
 * > bigger : MAX_VALUE - MIN_VALUE
 * 1
 * > equal : MAX_VALUE - MAX_VALUE
 * 0
 * > smaller : MIN_VALUE - MAX_VALUE
 * -1
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args){
        executeBigger();
        executeEqual();
        executeSmaller();
    }

    private static void executeBigger(){
        // setup
        final Point p1 = new Point(Integer.MAX_VALUE, 1);
        final Point p2 = new Point(Integer.MIN_VALUE, 1);

        // action
        final int result = p1.compareTo(p2);

        // check
        System.out.println("> bigger : MAX_VALUE - MIN_VALUE");
        System.out.println(result);
    }

    private static void executeEqual(){
        // setup
        final Point p1 = new Point(Integer.MAX_VALUE, 1);
        final Point p2 = new Point(Integer.MAX_VALUE, 1);

        // action
        final int result = p1.compareTo(p2);

        // check
        System.out.println("> equal : MAX_VALUE - MAX_VALUE");
        System.out.println(result);
    }


    private static void executeSmaller(){
        // setup
        final Point p1 = new Point(Integer.MIN_VALUE, 1);
        final Point p2 = new Point(Integer.MAX_VALUE, 1);

        // action
        final int result = p1.compareTo(p2);

        // check
        System.out.println("> smaller : MIN_VALUE - MAX_VALUE");
        System.out.println(result);
    }

}
