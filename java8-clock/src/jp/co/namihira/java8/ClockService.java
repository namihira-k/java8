/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.LocalTime;
import java.time.ZoneId;

import javafx.beans.property.Property;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ClockService extends Service<Void> {

    void bindMessage(final Property<String> propety){
        propety.bind(this.messageProperty());
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                while(true) {
                    final LocalTime localTime = LocalTime.now(ZoneId.of("Asia/Tokyo"));
                    updateMessage(localTime.toString());
                }
            }
        };
    }

}