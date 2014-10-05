/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class RandomTest {

    @Test
    public void test_generate() {
        // prepare
        final long SEED = 123456L;
        final int SIZE = 1_000_000;

        // action
        Stream<Long> random = new Random(SEED).generate().limit(SIZE);
        Set<Long> result = random.collect(Collectors.toCollection(HashSet::new));

        // check
        assertEquals(SIZE, result.size());
    }

    @Test
    public void test_generate_seedIsZero() {
        // prepare
        final long SEED = 0L;
        final int SIZE = 1_000_000;

        // action
        Stream<Long> random = new Random(SEED).generate().limit(SIZE);
        Set<Long> result = random.collect(Collectors.toCollection(HashSet::new));

        // check
        assertEquals(SIZE, result.size());
    }

}
