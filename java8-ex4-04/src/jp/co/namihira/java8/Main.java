/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 91ページの4.5節「バインディング」のプログラムについて、円が真ん中に配置され、
 * シーンの4つの辺の少なくとも2つの辺に常に接するように機能拡張しなさい。
 */

package jp.co.namihira.java8;

import static javafx.beans.binding.Bindings.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    private Scene scene;
    private Pane pane = new Pane();
    private Circle circle = new Circle();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        buildScreen(stage);
        registerEventHandlers(stage);
    }

    private void buildScreen(Stage stage){
        circle.setRadius(100);
        pane.getChildren().add(circle);
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void registerEventHandlers(Stage stage){
        circle.centerXProperty().bind(divide(scene.widthProperty(), 2));
        circle.centerYProperty().bind(divide(scene.heightProperty(), 2));

        circle.radiusProperty().bind(
                when(greaterThanOrEqual(scene.widthProperty(), scene.heightProperty()))
                .then(divide(scene.heightProperty(), 2))
                .otherwise(divide(scene.widthProperty(), 2))
                );
    }

}