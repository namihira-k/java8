/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

public interface IDefault {

    default void f(){
        System.out.println("called IDefault.f()");
    }

}
