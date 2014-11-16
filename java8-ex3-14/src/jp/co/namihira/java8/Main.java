/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * ピクセル単位の遅延評価を扱うために、今までのトランスフォーマを変更して、
 * 画像内の他のピクセルを読み込むことができるPixelReaderを渡すようにしなさい。
 * たとえば、(x, y, reader) -> reader.getColor(width - (x + 1), y)は鏡像操作です。
 * 前の練習問題からの畳込みフィルターであれば、リーダーの観点からは容易に実装できます。
 * 素直な操作は、単に(x, y, reader) -> reader.getColor(width - x, y).grayscale()の
 * 形式であり、UnaryOperation<Color>からのアダプターを提供することができます。
 * PixelReaderは、操作のパイプライン中の特定のレベルにあります。
 * パイプライン中の個々のレベルで最近読み込まれたピクセルのキャッシュを保持するようにしてください。
 * ピクセルを求められたら、リーダーはキャッシュ（あるいは、レベル0なら元画像）を調べる。
 * ピクセルがなければリーダーを構築し、そのリーダーはピクセルを前段階で求めます。
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
        ColorTransformer hueTransformer = ImageUtils.getHueImageTransformer(0, (int)image.getWidth() - 1,
                0, (int)image.getHeight() - 1);
        ColorTransformer edgeTransformer = ImageUtils.getEdgeImageTransformer(0, (int)image.getWidth() - 1,
                0, (int)image.getHeight() - 1);

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