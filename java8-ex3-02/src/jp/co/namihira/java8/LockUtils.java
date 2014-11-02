/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class LockUtils {

    /**
     * 指定されたLockを利用し、指定されたactionSupplierを排他的に実行します。
     *
     * @exception IllegalArgumentException 引数のいずれかがnullの場合。
     *
     * @param lock ロック
     * @param actionSupplier　処理
     */
    public static <T> void withLock(final ReentrantLock lock, final Supplier<T> actionSupplier) {
        if (lock == null) {
            throw new IllegalArgumentException("lock must not be null");
        }

        if (actionSupplier == null) {
            throw new IllegalArgumentException("actionSupplier must not be null");
        }

        lock.lock();
        try {
            actionSupplier.get();
        } finally {
            lock.unlock();
        }
    }
}