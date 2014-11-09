/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 画像に任意の幅と色の枠を追加するColorTransformerを生成するように、
 * staticメソッドを書いて、練習問題5を汎用化しなさい。
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
        ColorTransformer colorTransformer = ImageUtils.getFrameColorTransformer(10, Color.GREEN);
        Image transformedImage = ImageUtils.transform(image, colorTransformer);

        // check
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
     }

}