/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 *  ラムダ式が次のような拡張forループ内の値をキャプチャした場合には、どうなりますか。
 *      String[] names = { "Peter", "Paul", "Mary" };
 *      List<Runnable> runners = new ArrayList<>();
 *      for (String name : names)
 *          runners.add(() -> System.out.println(name));
 *  これは正当なコードでしょうか。各ラムダ式は異なる値をキャプチャするのでしょうか？
 *  それとも、すべてのラムダ式が最後の値をキャプチャするのでしょうか。
 *  従来のfor(int i = 0; i < names.length; i++)ループを使用すると、どうなるでしょうか？
 */
/**
 * A.
 *  正当なコード。各ラムダ式は異なる値をキャプチャできる。
 *  従来のforループでも同じ動作。
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // prepare
        // - nothing

        /*** 拡張for文 ***/
        // action
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for (String name : names)
            runners.add(() -> System.out.println(name));

        // check
        runners.stream().forEach(runner -> new Thread(runner).start());
        // -> standard output
        //  Peter
        //  Mary
        //  Paul

        /*** for文 ***/
        // action
        String[] namesFor = { "Peter", "Paul", "Mary" };
        List<Runnable> runnersFor = new ArrayList<>();
        for (int i = 0; i < namesFor.length; i++) {
            int index = i;
            runnersFor.add(() -> System.out.println(namesFor[index]));
        }

        // check
        runnersFor.stream().forEach(runner -> new Thread(runner).start());
        // -> standard output
        //  Paul
        //  Peter
        //  Mary
    }

}