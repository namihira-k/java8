/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 *　ほとんどがデフォルトから変化しない多くのJavaFXプロパティを持つクラスを考えなさい。
 * デフォルトではない値に設定されたり、xxxProperty()メソッドが最初に呼び出されたときに、
 * 要求に応じてプロパティを構築する方法を示しなさい。
 */

package jp.co.namihira.java8;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // prepare
        final String str = "hoge";
        Greeting greeting = new Greeting();

        // action
        greeting.setText(str);
        StringProperty property = greeting.textProperty();

        // check
        System.out.println("Expect : " + str);
        System.out.println("Result(property.get()) : " + property.get());
        System.out.println("Result(greeting.getText()) : " + greeting.getText());
    }
}