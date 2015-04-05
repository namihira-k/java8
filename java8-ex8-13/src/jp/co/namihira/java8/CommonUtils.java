/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

public class CommonUtils {

    @TestCase(params="4", expected="24")
    @TestCase(params="0", expected="1")
    public static long factorial(int n){
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    @TestCase(params="4", expected="4")
    public static long nonFactorial(int n){
        return n;
    }

}