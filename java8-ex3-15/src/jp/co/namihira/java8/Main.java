/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 69ページの3.6節「遅延」の遅延評価と70ページの3.7節「操作の並列化」の
 * 並列評価を組合せなさい。
 */

package jp.co.namihira.java8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // prepare
        Image image = new Image("queen-mary.png");

        // action
        Image transformedImage = LatentImage.form(image)
                .transform(Color::brighter)
                .transform(Color::grayscale)
                .toImage();

        // check
        stage.setScene(new Scene(new HBox(
                new ImageView(image), new ImageView(transformedImage))));
        stage.show();
        // - log
        // Thread[pool-2-thread-1,5,main] 0...95
        // Thread[pool-2-thread-2,5,main] 96...191
        // Thread[pool-2-thread-4,5,main] 288...383
        // Thread[pool-2-thread-3,5,main] 192...287
        // Thread[pool-3-thread-1,5,main] 0...95
        // Thread[pool-3-thread-3,5,main] 192...287
        // Thread[pool-3-thread-2,5,main] 96...191
        // Thread[pool-3-thread-4,5,main] 288...383
        // Thread[pool-4-thread-1,5,main] 0...95
        // Thread[pool-4-thread-4,5,main] 288...383
        // Thread[pool-4-thread-2,5,main] 96...191
        // Thread[pool-4-thread-3,5,main] 192...287
    }

}