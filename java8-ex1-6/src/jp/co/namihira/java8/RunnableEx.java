/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

@FunctionalInterface
interface RunnableEx{

    public abstract void run() throws Exception;

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
}