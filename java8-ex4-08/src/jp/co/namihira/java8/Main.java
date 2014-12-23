/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * FXMLファイルを解析する際には、JavaFX固有の知識は必要としません。
 * JavaJXでは何もせずに、入れ子になっているオブジェクトを持つオブジェクトをロードし、
 * FXML構文でプロパティを設定する例を作成しなさい。
 * 注入できたら、さらによいです。
 */

package jp.co.namihira.java8;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        HBox box = new HBox();
        box.getChildren().addAll(getPaneFXML());
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }

    private Pane getPaneFXML() {
        Pane pane = null;
        try {
            pane = new Pane(FXMLLoader.load(getClass().getResource("login.fxml")));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return pane;
    }

}