/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {

    private Image in;
    private List<ColorTransformer> pendingColorTransformers;

    private LatentImage(Image in) {
        this.in = in;
        this.pendingColorTransformers = new LinkedList<>();
    }

    /**
     * LatentImageオブジェクトを返却します。
     *
     * @param in　画像
     * @return　LatentImageオブジェクト
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     */
    public static LatentImage form(Image in){
        if (in == null) {
            throw new IllegalArgumentException("in must not be null");
        }
        return new LatentImage(in);
    }

    /**
     *
     * 画像変換を処理します。
     * これは中間操作です。
     *
     * @param f 処理
     * @return　新しいLatentImageオブジェクト
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     */
    public LatentImage transform(UnaryOperator<Color> f){
        if (f == null) {
            throw new IllegalArgumentException("f must not be null");
        }
        return transform(toColorTransformer(f));
    }

   /**
    *
    * 画像変換を処理します。
    * これは中間操作です。
    *
    * @param f 処理
    * @return　新しいLatentImageオブジェクト
    *
    * @throws IllegalArgumentException 引数がnullの場合。
    */
    public LatentImage transform(ColorTransformer f){
        if (f == null) {
            throw new IllegalArgumentException("f must not be null");
        }
        pendingColorTransformers.add(f);
        return this;
    }

    /**
     * 変換処理された画像を返却します。
     * これは終端操作です。
     *
     * @return　変換処理された画像
     */
    public Image toImage(){
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color c = in.getPixelReader().getColor(x, y);
                for (ColorTransformer f : pendingColorTransformers) {
                    c = f.apply(x, y, c);
                }
                out.getPixelWriter().setColor(x, y, c);
            }
        }
        return out;
    }

    private ColorTransformer toColorTransformer(UnaryOperator<Color> op) {
        if (op == null) {
            throw new IllegalArgumentException("op must not be null");
        }
        return (x, y, colorAtXY) -> op.apply(colorAtXY);
    }

}