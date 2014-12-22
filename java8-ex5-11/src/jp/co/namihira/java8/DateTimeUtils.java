/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.Duration;
import java.time.temporal.Temporal;

public class DateTimeUtils {

    public static Duration between(final Temporal t1, final Temporal t2){
        if (t1 == null || t2 == null) {
            throw new IllegalArgumentException("args must not be null");
        }
        return Duration.between(t1, t2);
    }

}
