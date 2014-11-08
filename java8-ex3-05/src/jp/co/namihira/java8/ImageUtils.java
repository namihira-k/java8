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
     * 画像変換をします。
     *
     * @exception IllegalArgumentException 引数のいずれかがnullの場合。
     *
     * @param in　画像
     * @param f 変換処理
     * @return 変換された画像
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

    /**
     * 画像変換をします。
     *
     * @exception IllegalArgumentException 引数のいずれかがnullの場合。
     *
     * @param in　画像
     * @param f 変換処理
     * @return 変換された画像
     */
    public static Image transform(Image in, UnaryOperator<Color> f){
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
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }

}