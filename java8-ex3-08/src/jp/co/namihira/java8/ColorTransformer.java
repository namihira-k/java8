/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {

    /**
     *　指定された座標と色を用いて、新しいColorを返却します。
     * @param x x座標
     * @param y y座標
     * @param in 画像
     * @return 新しいColor
     */
    Color apply(int x, int y, Image in);

}