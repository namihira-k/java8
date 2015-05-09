/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double diffX = 0;
    private double diffY = 0;

    private final String KEY_X = "X";
    private final String KEY_Y = "Y";

    private final String KEY_WIDTH = "width";
    private final String KEY_HEIGHT = "height";

    private final static Preferences cache = Preferences.userNodeForPackage(Main.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initialize(stage);

        Label timeLabel = new Label();

        final ClockService clock = new ClockService();
        clock.bindMessage(timeLabel.textProperty());
        clock.restart();

        PropertyDialog.bindFont(timeLabel.fontProperty());

        Scene scene = new Scene(timeLabel);

        scene.setOnMousePressed((event) -> {
            final MouseButton clickedButton = event.getButton();
            if (clickedButton == MouseButton.PRIMARY) {
                PropertyDialog.close();
                diffX = event.getSceneX();
                diffY = event.getSceneY();
            } else if (clickedButton == MouseButton.SECONDARY) {
                PropertyDialog.show(event.getScreenX(), event.getScreenY());
            }
        });

        scene.setOnMouseDragged((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                stage.setX(event.getScreenX() - diffX);
                stage.setY(event.getScreenY() - diffY);
            }
        });

        timeLabel.fontProperty().addListener((font) -> {
            timeLabel.autosize();
            stage.setWidth(timeLabel.getWidth() * 1.1);
            stage.setHeight(timeLabel.getHeight() * 1.1);
        });

        PropertyDialog.bindFontColor(timeLabel.textFillProperty());
        PropertyDialog.bindBackground(timeLabel.backgroundProperty());

        // 枠なしの制御
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.show();

        PropertyDialog.setExitActionLinstener(() -> {
            cache.putDouble(KEY_X, stage.getX());
            cache.putDouble(KEY_Y, stage.getY());
            cache.putDouble(KEY_WIDTH, stage.getWidth());
            cache.putDouble(KEY_HEIGHT, stage.getHeight());
        });
    }

    private void initialize(final Stage stage){
        stage.setX(cache.getDouble(KEY_X, 100));
        stage.setY(cache.getDouble(KEY_Y, 100));
        stage.setWidth(cache.getDouble(KEY_WIDTH, 100));
        stage.setHeight(cache.getDouble(KEY_HEIGHT, 100));
    }

}