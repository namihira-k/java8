/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {

    /**
     * 指定された座標と画像のピクセルリーダーを使用し色変換処理を行い、変換処理後の色を返却します。
     *
     * @param x x座標
     * @param y y座標
     * @param reader PixelReaderオブジェクト
     *
     * @return 変換処理後の色
     */
    Color apply(int x, int y, PixelReader reader);

}