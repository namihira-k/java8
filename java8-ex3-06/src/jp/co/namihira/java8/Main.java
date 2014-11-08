/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 65ページの3.4節「関数を返す」で見た次のメソッドを完成させなさい。
 * public static <T> Image transform(Image in, Bifuction<Color, T, Color> f, T arg)
 */

package jp.co.namihira.java8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
        Image brightenedImage = ImageUtils.transform(image,
                (c, factor) -> c.deriveColor(0, 1, factor, 1),
                1.2);

        Image moreBrightenedImage = ImageUtils.transform(image,
                (c, factor) -> c.deriveColor(0, 1, factor, 1),
                2.0);

        // check
        stage.setScene(new Scene(new HBox(
                new ImageView(image),
                new ImageView(brightenedImage),
                new ImageView(moreBrightenedImage))));
        stage.show();
     }

}