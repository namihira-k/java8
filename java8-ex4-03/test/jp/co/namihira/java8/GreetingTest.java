/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;
import javafx.beans.property.StringProperty;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class GreetingTest {

    @Test
    public void test_set_get() {
        // prepare
        final String str = "hoge";
        Greeting greeting = new Greeting();

        // action
        greeting.setText(str);

        // check
        assertEquals(str, greeting.getText());
    }

    @Test
    public void test_set_textProperty_get() {
        // prepare
        final String str = "hoge";
        Greeting greeting = new Greeting();

        // action
        greeting.setText(str);
        StringProperty property = greeting.textProperty();

        // check
        assertEquals(str, property.get());
    }

    @Test
    public void test_textProperty_set_get() {
        // prepare
        final String str = "hoge";
        Greeting greeting = new Greeting();

        // action
        StringProperty property = greeting.textProperty();
        property.set(str);

        // check
        assertEquals(str, greeting.getText());
        assertEquals(str, property.get());
    }

    @Test
    public void test_get() {
        // prepare
        Greeting greeting = new Greeting();

        // action
        String result = greeting.getText();

        // check
        assertEquals("", result);
    }

}