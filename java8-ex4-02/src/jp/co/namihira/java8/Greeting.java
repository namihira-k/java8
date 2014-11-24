/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Greeting {

    private String text = "";
    private StringProperty textProperty;

    public final StringProperty textProperty(){
        textProperty = new SimpleStringProperty(text);
        return textProperty;
    }

    public final void setText(String newValue){
        if (textProperty != null) {
            textProperty.set(newValue);
        }
        this.text = newValue;
    }

    public final String getText(){
        return textProperty != null ? textProperty.get() : text;
    }

}