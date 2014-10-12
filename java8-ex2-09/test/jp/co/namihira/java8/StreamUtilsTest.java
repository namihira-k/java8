/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StreamUtilsTest {

    /*
     * test for flatListWithReduce 1 args()
     */

    @Test
    public void test_flatListWithReduce1args() {
        // prepare
        List<List<Integer>> lists  = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        // action
        List<Integer> result = StreamUtils.flatListWithReduce1args(lists.stream());

        // check
        for (int i = 0; i < list1.size(); i++) {
            assertEquals(list1.get(i), result.get(i));
        }

        int offset = list1.size();
        for (int i = 0; i < list2.size(); i++) {
            assertEquals(list2.get(i), result.get(i + offset));
        }

        offset += list2.size();
        for (int i = 0; i < list3.size(); i++) {
            assertEquals(list3.get(i), result.get(i + offset));
        }
    }

    @Test
    public void test_flatListWithReduce1args_null() {
        // prepare
        // - nothing

        // action
        List<Integer> result = StreamUtils.flatListWithReduce1args(null);

        // check
        assertEquals(0, result.size());
    }

    @Test
    public void test_flatListWithReduce1args_streamOfEmptyList() {
        // prepare
        List<List<Integer>> lists  = new ArrayList<>();
        List<Integer> list = Arrays.asList();

        lists.add(list);

        // action
        List<Integer> result = StreamUtils.flatListWithReduce1args(lists.stream());

        // check
        assertEquals(0, result.size());
    }

    /*
     * test for flatListWithReduce 2 args()
     */

    @Test
    public void test_flatListWithReduce2args() {
        // prepare
        List<List<Integer>> lists  = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        // action
        List<Integer> result = StreamUtils.flatListWithReduce2args(lists.stream());

        // check
        for (int i = 0; i < list1.size(); i++) {
            assertEquals(list1.get(i), result.get(i));
        }

        int offset = list1.size();
        for (int i = 0; i < list2.size(); i++) {
            assertEquals(list2.get(i), result.get(i + offset));
        }

        offset += list2.size();
        for (int i = 0; i < list3.size(); i++) {
            assertEquals(list3.get(i), result.get(i + offset));
        }
    }

    @Test
    public void test_flatListWithReduce2args_null() {
        // prepare
        // - nothing

        // action
        List<Integer> result = StreamUtils.flatListWithReduce2args(null);

        // check
        assertEquals(0, result.size());
    }

    @Test
    public void test_flatListWithReduce2args_streamOfEmptyList() {
        // prepare
        List<List<Integer>> lists  = new ArrayList<>();
        List<Integer> list = Arrays.asList();

        lists.add(list);

        // action
        List<Integer> result = StreamUtils.flatListWithReduce2args(lists.stream());

        // check
        assertEquals(0, result.size());
    }



    /*
     * test for flatListWithReduce 3 args()
     */

    @Test
    public void test_flatListWithReduce3args() {
        // prepare
        List<List<Integer>> lists  = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        // action
        List<Integer> result = StreamUtils.flatListWithReduce3args(lists.stream());

        // check
        for (int i = 0; i < list1.size(); i++) {
            assertEquals(list1.get(i), result.get(i));
        }

        int offset = list1.size();
        for (int i = 0; i < list2.size(); i++) {
            assertEquals(list2.get(i), result.get(i + offset));
        }

        offset += list2.size();
        for (int i = 0; i < list3.size(); i++) {
            assertEquals(list3.get(i), result.get(i + offset));
        }
    }

    @Test
    public void test_flatListWithReduce3args_null() {
        // prepare
        // - nothing

        // action
        List<Integer> result = StreamUtils.flatListWithReduce3args(null);

        // check
        assertEquals(0, result.size());
    }

    @Test
    public void test_flatListWithReduce3args_streamOfEmptyList() {
        // prepare
        List<List<Integer>> lists  = new ArrayList<>();
        List<Integer> list = Arrays.asList();

        lists.add(list);

        // action
        List<Integer> result = StreamUtils.flatListWithReduce3args(lists.stream());

        // check
        assertEquals(0, result.size());
    }

}
