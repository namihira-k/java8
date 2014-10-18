/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 単一のArrayListがストリームの大きさと同じ大きさで生成されている場合、複数のArrayListをマージすることなく、
 * その単一のArrayListにストリームの結果を並行して収集できるはずです。なぜなら、互いに異なる位置へ並行して行う
 * set 操作であれば、スレッドセーフとなるからです。みなさんは、この収集をどうやって達成することができますか？
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // prepare
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> data1 = Arrays.asList(1, 2, 3);
        input.add(data1);
        List<Integer> data2 = Arrays.asList(4, 5, 6);
        input.add(data2);
        List<Integer> data3 = Arrays.asList(7, 8, 9);
        input.add(data3);

        // action
        List<Integer> result = StreamUtils.collect(input, Integer::sum);

        // check
        for (int i = 0; i < input.size(); i++) {
            System.out.println("index " + i);
            System.out.println("- result : " + result.get(i));
            System.out.println("- except : " + input.get(i).stream().reduce(Integer::sum).get());
        }
    }

}