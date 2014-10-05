/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.stream.Stream;

/**
 * 新規乱数ジェネレータのクラス
 */
public class Random {

    private long seed;

    public Random(long seed) {
        this.seed = seed;
    }

    /**
     * 乱数の無限ストリームを作成します。
     * @return　乱数の無限ストリーム
     */
    public Stream<Long> generate(){
        final long A = 25214903917L;
        final long C = 11L;
        final long M = (long) Math.pow(2, 48);

        Stream<Long> result = Stream.iterate(seed, (x) -> (A * x + C) % M);

        return result;
    }

}