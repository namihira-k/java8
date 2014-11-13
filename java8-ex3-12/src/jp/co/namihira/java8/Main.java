/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 69ページの3.6節「遅延」のLatentImageを機能拡張して、
 * UnaryOperator<Color>とColorTransformerの両方をサポートするようにしなさい。
 * ヒント：UnaryOperator<Color>をColorTransformerへ適応させなさい。
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
                .transform(
                        (x, y, colorAtXY) ->
                            ( x < 10 || x > (image.getWidth() - 10) ||
                              y < 10 || y > (image.getHeight() - 10) ?
                                  Color.GREEN : colorAtXY))
                .toImage();

        // check
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
    }

}