/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Comparator<String>を生成するメソッドを書きなさい。普通の順序、逆順、大文字小文字を区別、
 * 大文字小文字を区別しない、空白を含める、空白を除外する、あるいは、
 * これらの組み合わせとすることができるComparator<String>にしなさい。
 * メソッドは、ラムダ式を返すようにしなさい。
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // prepare
        List<String> input = Arrays.asList("c", "bb", "ba", "a");

        // action
        Comparator<String> comp = ComparatorUtils.comparing(
                (first, second) -> Integer.compare(first.length(), second.length()),
                (first, second) -> first.compareTo(second));

        List<String> resutlt = new ArrayList<String>(input);
        resutlt.sort(comp);

        // check
        System.out.println("input : " + input);
        System.out.println("result : " + resutlt);
    }

}