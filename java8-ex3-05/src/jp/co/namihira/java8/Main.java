/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 次は、ColorTransformerの具体例です。次のように、画像の周りに枠を付加します。
 * 最初に、62ページの3.3節「関数型インタフェースの選択」のtransformメソッドを、
 * UnaryOperator<Color>の代わりにColorTransformerで実装しなさい。
 * それから、画像の周りの10ピクセルを灰色の枠で置き換えるために、そのtransformメソッド
 * を適切なラムダ式で呼び出しなさい。
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
        Image transformedImage = ImageUtils.transform(image,
                (x, y, c) ->
                x < 10 || x > (image.getWidth() - 10) || y < 10 || y > (image.getHeight() - 10)
                ? Color.GRAY : c);

        // check
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
     }

}