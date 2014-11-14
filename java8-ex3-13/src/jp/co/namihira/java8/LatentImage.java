/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {

    private Image in;
    private List<ImageTransformer> pendingImageTransformers;

    private LatentImage(Image in) {
        this.in = in;
        this.pendingImageTransformers = new LinkedList<>();
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
    public LatentImage transform(ImageTransformer f){
        if (f == null) {
            throw new IllegalArgumentException("f must not be null");
        }
        pendingImageTransformers.add(f);
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
        Image prevImage = in;
        WritableImage out = null;
        for (ImageTransformer f : pendingImageTransformers) {
            out = new WritableImage(width, height);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color c = f.apply(x, y, prevImage);
                    out.getPixelWriter().setColor(x, y, c);
                }
            }
            prevImage = out;
        }
        return out;
    }

}