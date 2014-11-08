/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {

    /**
     *　指定された座標と色を用いて、新しいColorを返却します。
     * @param x x座標
     * @param y y座標
     * @param colorAtXY 色
     * @return 新しい色
     */
    Color apply(int x, int y, Color colorAtXY);

}