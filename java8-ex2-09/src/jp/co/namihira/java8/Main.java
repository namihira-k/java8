/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Stream<ArrayList<T>>内のすべての要素を、1つのArrayList<T> へまとめなさい。
 * 具体的には、3つの形式のreduceを用いる方法を示しなさい。
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
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
        print("List1 : ", list1);
        print("List2 : ", list2);
        print("List3 : ", list3);
        print("Result : ", result);
    }

    private static <T> void print(final String message, final List<T> list){
        System.out.print(message);
        System.out.println(list.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

}