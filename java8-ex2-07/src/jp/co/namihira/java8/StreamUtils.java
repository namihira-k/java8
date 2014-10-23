/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.stream.Stream;

/**
 * Streamに関するクラス
 */
public class StreamUtils {

    /**
     * Streamの有限の上限を表す定数
     */
//    private static final long UPPER_LIMIT = Long.MAX_VALUE;
    public static final long UPPER_LIMIT = 10_000;

    /**
     * 指定されたStreamが有限ストリームかどうか。
     * nullが指定された場合、falseを返します。
     * 指定したStreamは消費されます。
     * 
     * @param stream
     * @return 有限ストリーム（上限（UpperLimit）含まない）ならtrue。無限ストリームなら、false。
     */
    public static <T> boolean isFinite(Stream<T> stream) {
        if (stream == null) {
            return false;
        }

        Counter counter = new Counter();
        stream.peek(e -> counter.countUp())
            .filter(e -> counter.getCount() == UPPER_LIMIT)
            .findFirst();
      return counter.getCount() < UPPER_LIMIT;
    }

    private static class Counter {
        long count = 0;

        private synchronized void countUp(){
            count++;
        }

        private long getCount(){
            return count;
        }
    }


}