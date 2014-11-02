/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockUtilsTest {

    @Test
    public void test_withLock() {
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
                return true;
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
            return true;
        });

        // check
        // - checked no exception.
        // - checked standard output.
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_withLock_null_lock() {
        // prepare

        // action
        LockUtils.withLock(null, () -> {
            System.out.println("actionSupplier called");
            return true;
        });

        // check
        // - checked throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_withLock_null_supplier() {
        // prepare
        ReentrantLock lock = new ReentrantLock();

        // action
        LockUtils.withLock(lock, null);

        // check
        // - checked throw Exception
    }


}
