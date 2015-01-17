/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorWrapper {

    private LongAccumulator maxAccumulator;
    private LongAccumulator minAccumulator;

    public LongAccumulatorWrapper(long identity){
        maxAccumulator = new LongAccumulator(Math::max, identity);
        minAccumulator = new LongAccumulator(Math::min, identity);
    }

    public synchronized void accumulate(long x){
        maxAccumulator.accumulate(x);
        minAccumulator.accumulate(x);
    }

    public long max() {
        return maxAccumulator.get();
    }

    public long min() {
        return minAccumulator.get();
    }

    public void reset() {
        maxAccumulator.reset();
        minAccumulator.reset();
    }

}
