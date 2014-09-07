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
        new Thread(uncheck(() -> {
            System.out.println("Zzz");
            Thread.sleep(1000);
        })).start();

        // check
        // - checked no compile error.
    }

    public static Runnable uncheck(RunnableEx runner) {
        Runnable runnerWrapper = () -> {
            try {
                runner.run();
            } catch ( Exception e ) {
                throw new RuntimeException(e);
            }
        };
        return runnerWrapper;
    }

    @FunctionalInterface
    private interface RunnableEx{
        public abstract void run() throws Exception;
    }

}