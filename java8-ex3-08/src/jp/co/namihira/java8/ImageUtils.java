/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * 画像に関するユーティリティクラス
 */
public class ImageUtils {

    /**
     * 指定された枠の幅と色での枠追加画像変換オブジェクトを返却します。
     *
     * @param frameWidth　枠の幅
     * @param frameColor　枠の色
     * @return　枠追加画像変換オブジェクト
     *
     * @throws IllegalArgumentException frameWidthが負の値の場合。frameColorがnullの場合。
     *
     */
    public static ColorTransformer getFrameColorTransformer(final int frameWidth, final Color frameColor){
        if (frameWidth < 0) {
           throw new IllegalArgumentException("frameWidth must be positive");
        }
        if (frameColor == null) {
            throw new IllegalArgumentException("frameColor must be positive");
        }

        ColorTransformer result = (x, y, image) -> {
            if (x < frameWidth || x > (image.getWidth() - frameWidth) ||
                    y < frameWidth || y > (image.getHeight() - frameWidth)) {
                return frameColor;
            } else {
                return image.getPixelReader().getColor(x, y);
            }
        };

        return result;
    }

    /**
     * 画像変換をします。
     *
     * @param in　画像
     * @param f 変換処理
     * @return 変換された画像
     *
     * @exception IllegalArgumentException 引数のいずれかがnullの場合。
     */
    public static Image transform(Image in, ColorTransformer f){
        if (in == null) {
            throw new IllegalArgumentException("in must not be null");
        }
        if (f == null) {
            throw new IllegalArgumentException("f must not be null");
        }

        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(x, y, in));
            }
        }
        return out;
    }

}