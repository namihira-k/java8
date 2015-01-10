/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class StringMgmt {

    private static final AtomicReference<String> LONGEST = new AtomicReference<>("");

    private StringMgmt(){
    }

    public static String setStringIfLonger(final String s){
        Objects.requireNonNull(s);
        return LONGEST.accumulateAndGet(s, (oldValue, newValue) -> {
            return (oldValue.length() >= newValue.length()) ? oldValue : newValue;
        });
    }

    public static String get(){
        return LONGEST.get();
    }

}
