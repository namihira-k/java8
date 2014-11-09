/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    @SuppressWarnings("unchecked")
    public void test_comparing_once(){
        // prepare
        List<String> words = Arrays.asList("c", "b", "a");

        // action
        Comparator<String> comp = ComparatorUtils.comparing(
                (first, second) -> first.compareTo(second));
        List<String> clone = new ArrayList<>(words);
        clone.sort(comp);

        // check
        Collections.reverse(words);
        assertArrayEquals(words.toArray(new String[]{}), clone.toArray(new String[]{}));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_comparing_twice(){
        // prepare
        List<String> words = Arrays.asList("c", "bb", "ba", "a");
        List<String> answer = Arrays.asList("a", "c", "ba", "bb");

        // action
        Comparator<String> comp = ComparatorUtils.comparing(
                (first, second) -> Integer.compare(first.length(), second.length()),
                (first, second) -> first.compareTo(second));
        words.sort(comp);

        // check
        assertArrayEquals(answer.toArray(new String[]{}), words.toArray(new String[]{}));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_comparing_third(){
        // prepare
        List<String> words = Arrays.asList("c", "bb", "ba", "a", "_a");
        List<String> answer = Arrays.asList("_a", "a", "c", "ba", "bb");

        // action
        Comparator<String> comp = ComparatorUtils.comparing(
                (first, second) -> first.contains("_") ? -1 : 0,
                (first, second) -> Integer.compare(first.length(), second.length()),
                (first, second) -> first.compareTo(second));
        words.sort(comp);

        // check
        assertArrayEquals(answer.toArray(new String[]{}), words.toArray(new String[]{}));
    }

    @Test (expected = IllegalArgumentException.class)
    @SuppressWarnings("unchecked")
    public void test_comparing_null(){
        // prepare
        // - nothing

        // action
        ComparatorUtils.comparing(null);

        // check
        // - checked Exception
    }

}