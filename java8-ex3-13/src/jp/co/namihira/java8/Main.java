/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * ぼかし検出、あるいは、エッジ検出といった畳込みフィルターは、隣接するピクセルから1つのピクセルを計算します。
 * 画像をぼやかすためには、ピクセルとその隣接する8個のピクセルの平均で、個々の色値を置き換えます。
 * エッジ検出には、個々の色値cを 4c - n - e - s - wで置き換えます。
 * ここで、他の色は、北（north）、東（east）、南（south）、西（west）のピクセルの色値です。
 * これは、69ページの3.6節「遅延」で説明された方法を用いた遅延では実装できないことに注意してください。
 * なぜならば、計算するために、前段の画像（あるいは、少なくとも隣接するピクセル）が必要だからです。
 * これらの操作を行うために遅延画像処理の機能を強化しなさい。
 * これらの演算の1つが評価される際に、前段の計算が強制されるようにしなさい。
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
        ImageTransformer edgeTransformer = ImageUtils.getEdgeImageTransformer();
        ImageTransformer hueTransformer = ImageUtils.getHueImageTransformer();

        // action
        Image hueImage = LatentImage.form(image)
                .transform(hueTransformer)
                .toImage();

        Image edgedImage = LatentImage.form(image)
                .transform(edgeTransformer)
                .toImage();

        // check
        stage.setScene(new Scene(new HBox(
                new ImageView(image), new ImageView(hueImage), new ImageView(edgedImage))));
        stage.show();
    }

}