/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 2つのColorTransformaerオブジェクトを合成できる static メソッドを実装しなさい。
 * そして、x座標とy座標を無視するColorTransformerへUnaryOperatir<Color>を変えるstaticメソッドを実装しなさい。
 * それから、変換によって明るくなった画像に灰色の枠を追加するために、実装したメソッドを使用しなさい。
 * （灰色の枠に関しては練習問題5を参照しなさい。）
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
        ColorTransformer colorTransformer = ImageUtils.compose(
                ImageUtils.toColorTransformer(Color::brighter),
                (x, y, c) ->
                    ( x < 10 || x > (image.getWidth() - 10) ||
                      y < 10 || y > (image.getHeight() - 10) ?
                              Color.GRAY : c));
        Image transformedImage = ImageUtils.transform(image, colorTransformer);

        // check
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
    }

}