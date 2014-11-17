/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class LatentImage {

    private Image in;
    private List<UnaryOperator<Color>> pendingOperations;

    private LatentImage(Image in) {
        this.in = in;
        this.pendingOperations = new LinkedList<>();
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
        pendingOperations.add(f);
        return this;
    }

    /**
     * 変換処理後のImageオブジェクトを返します。
     * これは終端操作です。
     *
     * @return　変換処理後のImageオブジェクト
     */
    public Image toImage(){
        Color[][] out = ImageUtils.toArrays(in);
        for (UnaryOperator<Color> f : pendingOperations) {
            out = ImageUtils.parallelTransform(out, f);
        }
        return ImageUtils.toImage(out);
    }

}