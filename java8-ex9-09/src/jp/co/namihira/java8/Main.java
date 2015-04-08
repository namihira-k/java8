/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 次のLabeledPointクラスのequalsメソッドとhashCodeメソッドを
 * 実装しなさい。
 *
 * public class LabeledPoint {
 *    private String lable;
 *    private int x;
 *    private int y;
 * }
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args){
        executeEquals();
        executeHashcode();
    }

    private static void executeEquals(){
        System.out.println("*** Equals");
        // setup
        LabeledPoint p1 = new LabeledPoint("hoge", 1, 1);
        LabeledPoint p2 = new LabeledPoint("hoge", 1, 1);

        // action
        boolean result = p1.equals(p2);

        // check
        printResult(p1, p2, result);

        System.out.println("*** Equals Null");
        // setup
        p1 = new LabeledPoint(null, 1, 1);
        p2 = new LabeledPoint(null, 1, 1);

        // action
        result = p1.equals(p2);

        // check
        printResult(p1, p2, result);

        System.out.println("*** Not Equals ");
        // setup
        p1 = new LabeledPoint("hoge", 1, 1);
        p2 = new LabeledPoint("foo", 1, 1);
        LabeledPoint p22 = new LabeledPoint("hoge", 2, 1);
        LabeledPoint p23 = new LabeledPoint("hoge", 1, 2);

        // action, check
        result = p1.equals(p2);
        printResult(p1, p2, result);

        result = p1.equals(p22);
        printResult(p1, p22, result);

        result = p1.equals(p23);
        printResult(p1, p23, result);
    }

    private static void executeHashcode(){
        System.out.println("*** hashcode");
        // setup
        final LabeledPoint p1 = new LabeledPoint("hoge",  1, 1);
        final LabeledPoint p2 = new LabeledPoint("hoge",  1, 1);
        final LabeledPoint p22 = new LabeledPoint(null,   1, 1);
        final LabeledPoint p23 = new LabeledPoint("foo",  1, 1);
        final LabeledPoint p24 = new LabeledPoint("hoge", 2, 1);
        final LabeledPoint p25 = new LabeledPoint("hoge", 1, 2);

        // action, check
        printResult(p1,   p1.hashCode());
        printResult(p2,   p2.hashCode());
        printResult(p22, p22.hashCode());
        printResult(p23, p23.hashCode());
        printResult(p24, p24.hashCode());
        printResult(p25, p25.hashCode());
    }

    private static void printResult(final LabeledPoint p1, final LabeledPoint p2, final boolean result) {
        System.out.println(p1);
        System.out.println(p2);
        System.out.println("result:" + result);
    }

    private static void printResult(final LabeledPoint p1, final int result) {
        System.out.println(p1);
        System.out.println("result:" + result);
    }

}

/*
 * - log
*** Equals
label:hoge,x:1,y:1
label:hoge,x:1,y:1
result:true
*** Equals Null
label:null,x:1,y:1
label:null,x:1,y:1
result:true
*** Not Equals
label:hoge,x:1,y:1
label:foo,x:1,y:1
result:false
label:hoge,x:1,y:1
label:hoge,x:2,y:1
result:false
label:hoge,x:1,y:1
label:hoge,x:1,y:2
result:false
*** hashcode
label:hoge,x:1,y:1
result:-1211829404
label:hoge,x:1,y:1
result:-1211829404
label:null,x:1,y:1
result:29823
label:foo,x:1,y:1
result:97642437
label:hoge,x:2,y:1
result:-1211829373
label:hoge,x:1,y:2
result:-1211829403
*/
