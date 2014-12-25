/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * ReentrantLockを使用する場合には、次のイデオムでロックとアンロックをする必要があります。
 *   myLock.lock();
 *   try {
 *       何らかの処理
 *   } finally {
 *       myLock.unlock();
 *   }
 * 次のように呼び出すことができるwithLockメソッドを提供しなさい。
 *   withLock(myLock, () -> { 何らかの処理 })
 */
/**
 * A.
 * 2つのスレッドからwithLock()を実行し、Lockに対して排他的に実行されていることを確認した。
 * [ログ]
 * withLock() called in Main Thread
 * withLock() called in Sub Thread
 * actionSupplier called in Main Thread　※ここで他スレッドの処理が待機される
 * actionSupplier called in Sub Thread
 *
 */

package jp.co.namihira.java8;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        // prepare
        ReentrantLock lock = new ReentrantLock();

        // action
        new Thread(() -> {
            System.out.println("withLock() called in Sub Thread");
            LockUtils.withLock(lock, () -> {
                System.out.println("actionSupplier called in Sub Thread");
                try {
                    Thread.sleep(10_000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }).start();

        System.out.println("withLock() called in Main Thread");
        LockUtils.withLock(lock, () -> {
            System.out.println("actionSupplier called in Main Thread");
            try {
                Thread.sleep(10_000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // check
        // - checked no exception.
        // - checked standard output.
    }

}