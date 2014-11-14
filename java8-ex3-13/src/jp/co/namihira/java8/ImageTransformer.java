/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ImageTransformer {

    /**
     * 指定された座標と画像を利用し、その座標の色を返却します。
     *
     * @param x x座標
     * @param y y座標
     * @param in　画像
     * @return　その座標の色
     */
    Color apply(int x, int y, Image in);

}