/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Greeting {

    private StringProperty text;

    public final StringProperty textProperty(){
        if (text == null) {
            text = new SimpleStringProperty("");
        }
        return text;
    }

    public final void setText(String newValue){
        textProperty().set(newValue);
    }

    public final String getText(){
        return textProperty().get();
    }

}