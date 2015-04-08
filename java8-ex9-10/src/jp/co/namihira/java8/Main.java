/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 練習問題9のLabeledPointクラスにcompareToメソッドを実装しなさい。
 */
/*
 * 参考：
 * http://docs.oracle.com/javase/jp/8/docs/api/java/lang/Comparable.html
 */

package jp.co.namihira.java8;


public class Main {

    public static void main(String[] args){
        // setup
        final LabeledPoint p = new LabeledPoint("hoge", 2, 2);
        final LabeledPoint p2 = new LabeledPoint("hoge", 2, 2);
        final LabeledPoint p_big = new LabeledPoint("hoge", 3, 3);
        final LabeledPoint p_small = new LabeledPoint("hoge", 1, 1);

        // action, check
        int result = p.compareTo(p2);
        System.out.println("> equal");
        System.out.println("\t" + result);

        result = p.compareTo(p_big);
        System.out.println("> compare to big");
        System.out.println("\t" + result);

        result = p.compareTo(p_small);
        System.out.println("> compare to small");
        System.out.println("\t" + result);
    }

}
