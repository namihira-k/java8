/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

public interface JDefault {

    default void f(){
        System.out.println("called JDefault.f()");
    }

}
