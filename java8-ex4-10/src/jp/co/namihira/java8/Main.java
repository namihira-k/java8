/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * WebViewerを使用して、URLバーと戻るボタンをもつブラウザを実装しなさい。
 * ヒント：WebEngine.getHistory()。
 */

package jp.co.namihira.java8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {

    private Button backButon = new Button();
    private TextField addressBar = new TextField();
    private WebView browser = new WebView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final String location = "http://horstmann.com";
        WebEngine engine = browser.getEngine();
        engine.load(location);
        engine.locationProperty().addListener((property, oldValue, newValue) -> {
            addressBar.setText(newValue);
        });

        addressBar.setText(location);
        addressBar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                engine.load(addressBar.getText());
            }
        });

        backButon.setText("Back");
        backButon.setOnAction(event -> {
            WebHistory history = engine.getHistory();
            if (history.getCurrentIndex() <= 0) {
                return;
            }
            engine.getHistory().go(-1);
        });

        GridPane gridPane = new GridPane();
        gridPane.add(backButon,  0, 0, 1, 1);
        gridPane.add(addressBar, 1, 0, GridPane.REMAINING, 1);
        gridPane.add(browser,    0, 1, GridPane.REMAINING, GridPane.REMAINING);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
        stage.centerOnScreen();
    }

}