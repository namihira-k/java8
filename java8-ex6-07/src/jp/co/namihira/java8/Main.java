/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * ConcurrentHashMap<String, Long>内で、最大値を持つキーを見つけなさい。
 * （同じ最大値を持つキーがあれば、どちらのキーでもかまいません）。
 * ヒント：reduceEntries
 */

package jp.co.namihira.java8;

import java.util.Map.Entry;

public class Main {

    private static final ConcurrentHashMapWrapper MAP = new ConcurrentHashMapWrapper();

    public static void main(String[] args) {
        // prepare
        final String key1 = "key1";
        final Long value1 = 1L;
        MAP.put(key1, value1);

        final String key2 = "key2";
        final Long value2 = 2L;
        MAP.put(key2, value2);

        final String key3 = "key3";
        final Long value3 = -2L;
        MAP.put(key3, value3);

        // action
        final Entry<String, Long> result = MAP.getEntryWithMaxValue();

        // check
        System.out.println("MAP : " + MAP.entrySet());
        System.out.println("key with max value : " + result.getKey());
    }

}
