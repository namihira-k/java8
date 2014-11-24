/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class ObservableUtilsTest {

    @Test
    public void test_observe_function() {
        // prepare
        final Rectangle gauge = new Rectangle();
        gauge.setWidth(200);

        // action
        ObservableValue<Boolean> result = ObservableUtils.observe(
                (t) -> t.intValue() > 100
                , gauge.widthProperty());

        // check
        assertEquals(true, result.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_observe_function_null_f() {
        // prepare
        final Rectangle gauge = new Rectangle();
        gauge.setWidth(200);

        // action
        ObservableUtils.observe(null, gauge.widthProperty());

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_observe_function_null_t() {
        // prepare

        // action
        ObservableUtils.observe((t) -> "hoge", null);

        // check
        // - throw Exception
    }

    @Test
    public void test_observe_bifunction() {
        // prepare
        final Rectangle gauge = new Rectangle();
        gauge.setWidth(200);
        final Button button = new Button();
        button.setDisable(true);

        // action
        ObservableValue<Boolean> result = ObservableUtils.observe(
                (g, b) -> g.intValue() > 100 && b.booleanValue(),
                gauge.widthProperty(), button.disableProperty());

        // check
        assertEquals(true, result.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_observe_bifunction_null_f() {
        // prepare
        final Rectangle gauge = new Rectangle();
        gauge.setWidth(200);
        final Button button = new Button();
        button.setDisable(true);

        // action
        ObservableUtils.observe(
                null,
                gauge.widthProperty(), button.disableProperty());

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_observe_bifunction_null_t() {
        // prepare
        final Button button = new Button();
        button.setDisable(true);

        // action
        ObservableUtils.observe(
                (t, u) -> "hoge",
                null, button.disableProperty());

        // check
        // - throw Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_observe_bifunction_null_u() {
        // prepare
        final Button button = new Button();
        button.setDisable(true);

        // action
        ObservableUtils.observe(
                (t, u) -> "hoge",
                button.disableProperty(), null);

        // check
        // - throw Exception
    }

    @Test
    public void test_observe_addListener_function() {
        // prepare
        final BooleanProperty target = new SimpleBooleanProperty(false);
        final BooleanProperty result = new SimpleBooleanProperty(false);

        // action
        ObservableValue<Boolean> tmp = ObservableUtils.observe((t) -> t, target);
        tmp.addListener((observable, oldValue, newValue) -> result.set(newValue));
        target.set(true);

        // check
        assertEquals(true, result.get());
    }

    @Test
    public void test_observe_addListener_bifunction() {
        // prepare
        final BooleanProperty target1 = new SimpleBooleanProperty(false);
        final BooleanProperty target2 = new SimpleBooleanProperty(false);
        final BooleanProperty result = new SimpleBooleanProperty(false);

        // action
        ObservableValue<Boolean> tmp = ObservableUtils.observe((t1, t2) -> t1 && t2, target1, target2);
        tmp.addListener((observable, oldValue, newValue) -> result.set(newValue));

        // check
        target1.set(true);
        assertEquals(false, result.get());
        target2.set(true); // both true
        assertEquals(true, result.get());
    }

}