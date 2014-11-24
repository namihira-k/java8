/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 図4-7のTopとBottonのボタンを真ん中にそろえなさい。
 */

package jp.co.namihira.java8;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();

        Button top = new Button("Top");
        BorderPane.setAlignment(top, Pos.CENTER);
        pane.setTop(top);

        Button left = new Button("Left");
        BorderPane.setAlignment(left, Pos.CENTER);
        pane.setLeft(left);

        Button center = new Button("Center");
        BorderPane.setAlignment(center, Pos.CENTER);
        pane.setCenter(center);

        Button right = new Button("Right");
        BorderPane.setAlignment(right, Pos.CENTER);
        pane.setRight(right);

        Button bottom = new Button("Bottom");
        BorderPane.setAlignment(bottom, Pos.CENTER);
        pane.setBottom(bottom);

        stage.setScene(new Scene(pane));
        stage.show();
    }

}