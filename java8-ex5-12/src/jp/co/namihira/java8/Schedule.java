/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class Schedule {

    private static Set<ZonedDateTime> scheduleSet = new HashSet<>();

    public static void addSchedule(final ZonedDateTime newSchedule){
        synchronized (scheduleSet) {
            scheduleSet.add(newSchedule);
        }
    }

    public static Set<ZonedDateTime> getSchedule(){
        return new HashSet<>(scheduleSet);
    }

}
