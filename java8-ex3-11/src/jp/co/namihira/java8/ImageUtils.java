/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * 画像に関するユーティリティクラス
 */
public class ImageUtils {

    /**
     * 2つのColorTransformaerオブジェクトを合成します。
     *
     * @param first 1番目に実行する処理内容
     * @param second 2番目に実行する処理内容
     * @return　合成されたColorTransformaerオブジェクト
     *
     * @throws IllegalArgumentException 引数のいずれかがnullの場合。
     */
    public static ColorTransformer compose(ColorTransformer first, ColorTransformer second) {
        if (first == null) {
            throw new IllegalArgumentException("first must not be null");
        }
        if (second == null) {
            throw new IllegalArgumentException("second must not be null");
        }

        ColorTransformer transformer = (x, y, colorAtXY) -> {
            Color color = first.apply(x, y, colorAtXY);
            return second.apply(x, y, color);
        };

        return transformer;
    }

    /**
     * ColorTransformerへUnaryOperatir<Color>を変換します。
     * @param op 処理内容
     * @return 変換し生成されたColorTransformer
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     *
     */
    public static ColorTransformer toColorTransformer(UnaryOperator<Color> op) {
        if (op == null) {
            throw new IllegalArgumentException("op must not be null");
        }

        return (x, y, colorAtXY) -> op.apply(colorAtXY);
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
                out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }

}