/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.function.BiFunction;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * 画像に関するユーティリティクラス
 */
public class ImageUtils {

    /**
     * 指定された画像について、変換処理を行います。
     * 指定されたargを変換処理のパラメータとして使用します。
     *
     * @exception IllegalArgumentException 引数のいずれかがnullの場合。
     *
     * @param in 画像
     * @param f 変換処理
     * @param arg 変換処理のパラメータ
     * @return　変換後の画像
     */
    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg){
        if (in == null) {
            throw new IllegalArgumentException("in must not be null");
        }
        if (f == null) {
            throw new IllegalArgumentException("f must not be null");
        }
        if (arg == null) {
            throw new IllegalArgumentException("arg must not be null");
        }

        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y), arg));
            }
        }
        return out;
    }

}