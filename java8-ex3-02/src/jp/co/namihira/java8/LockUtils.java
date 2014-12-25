/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.concurrent.locks.ReentrantLock;

public class LockUtils {

    /**
     * 指定されたLockを利用し、指定されたrunnableを排他的に実行します。
     *
     * @exception IllegalArgumentException 引数のいずれかがnullの場合。
     *
     * @param lock ロック
     * @param runnable　処理
     *
     */
    public static <T> void withLock(final ReentrantLock lock, final Runnable runnable) {
        if (lock == null) {
            throw new IllegalArgumentException("lock must not be null");
        }

        if (runnable == null) {
            throw new IllegalArgumentException("actionSupplier must not be null");
        }

        lock.lock();
        try {
            runnable.run();
        } finally {
            lock.unlock();
        }
    }
}