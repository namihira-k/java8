/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 *　練習問題12を繰り返しなさい。ただし、ソースコードのアノテーションプロセッサを構築しなさい。
 *　そのプロセッサは実行されると、mainメソッドでテストを実行するプログラムを生成します。
 *　（ソースコードのアノテーション処理については、
 *　Horstmann、Cornell著、Core Java, 9th Edition, Volume 2の10.6節を参照しなさい。）
 */
/*
 * A.
 * - コマンド
 * > javac jp\co\namihira\java8\TestCaseProcessor.java -encoding UTF-8
 * > javac  -processor jp.co.namihira.java8.TestCaseProcessor jp\co\namihira\java8\*.java -encoding UTF-8
 *
 * - log
 * public static long jp.co.namihira.java8.CommonUtils.nonFactorial(int)
 * params : 4      result : 4      expected : 4
 * public static long jp.co.namihira.java8.CommonUtils.factorial(int)
 * params : 4      result : 24     expected : 24
 * public static long jp.co.namihira.java8.CommonUtils.factorial(int)
 * params : 0      result : 1      expected : 1
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args){
        // setup
        // - nothing

        // action
        // - nothing

        // check
        // - nothing
    }

}