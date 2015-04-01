/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * CheckQueueクラスの利点を示すプログラムを書きなさい。
 */
/*
 * A.
 * - log
 * NO_CHECKED_QUEUE
 * Integer : true
 * Number : true
 * OK
 *
 * CHECKED_QUEUE
 * Integer : true
 * Exception in thread "main" java.lang.ClassCastException: Attempt to insert class java.lang.String element into collection with element type class java.lang.Integer
 *     at java.util.Collections$CheckedCollection.typeCheck(Collections.java:3037)
 *     at java.util.Collections$CheckedCollection.add(Collections.java:3080)
 *     at jp.co.namihira.java8.Main.testAddElements(Main.java:44)
 *     at jp.co.namihira.java8.Main.main(Main.java:30)
 */

package jp.co.namihira.java8;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private final static Queue<Integer> NO_CHECKED_QUEUE = new LinkedList<>();
    private final static Queue<Integer> CHECKED_QUEUE = Collections.checkedQueue(new LinkedList<>(), Integer.class);

    public static void main(String[] args) {
        System.out.println("NO_CHECKED_QUEUE");
        System.out.println(testAddElements(NO_CHECKED_QUEUE) ? "OK" : "NG");
        System.out.println("");

        System.out.println("CHECKED_QUEUE");
        System.out.println(testAddElements(CHECKED_QUEUE) ? "OK" : "NG");
    }

    private static boolean testAddElements(final Queue queue){
        // setup
        final TableA tableA = new TableA();
        // action, check
        Object id = getId(tableA);
        System.out.println("Integer : " + queue.add(id));

        // setup
        final TableB tableB = new TableB();
        // action, check
        id = getId(tableB);
        System.out.println("Number : " + queue.add(id));

        return true;
    }

    /**
     * IDを取得します。
     * @param obj　取得対象
     * @return　ID
     */
    private static Object getId(Object obj){
        Object result = null;
        try {
            Method method = obj.getClass().getMethod("getId", null);
            result = method.invoke(obj, null);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * IDが数字として管理されているテーブル
     */
    private static class TableA {
        private Integer id;

        TableA(){
            id = 10;
        }

        public Integer getId(){
            return id;
        }
    }

    /**
     * IDが文字列として管理されているテーブル
     */
    private static class TableB {
        private String id;

        TableB(){
            id = "123-456";
        }

        public String getId(){
            return id;
        }
    }

}
