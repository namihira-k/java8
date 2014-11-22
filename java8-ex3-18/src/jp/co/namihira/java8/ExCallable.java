/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
package jp.co.namihira.java8;

@FunctionalInterface
public interface ExCallable<T, U> {

    U call(T t) throws Exception;

}