/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Runnable内でチェックされる例外を処理しなければならないことが、いつも面倒だと思っていませんか？
 * チェックされるすべての例外をキャッチし、それをチェックされない例外へ変えるuncheckメソッドを書きなさい。
 * たとえば、次のように使用します。
 *    new Thread(uncheck(() ->
 *       { System.out.println("Zzz");　Thread.sleep(1000); })).
 *       start();
 *          // catch (InterruptedException)が必要ありません！
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        new Thread(RunnableEx.uncheck(() -> {
            System.out.println("Zzz");
            Thread.sleep(1000);
        })).start();

        // check
        // - checked no compile error.
    }

}