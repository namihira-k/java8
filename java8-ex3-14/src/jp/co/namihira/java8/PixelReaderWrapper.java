/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class PixelReaderWrapper {

    private PixelReader reader;
    private Map<Integer, Map<Integer, Color>> cache = new HashMap<>();

    public PixelReaderWrapper(PixelReader reader) {
        this.reader = reader;
    }

    /**
     *　指定した座標の色を返却します。
     *
     * @param x x座標
     * @param y y座標
     * @return　色
     *
     * @throws IllegalArgumentException 引数が負の値の場合。
     */
    public Color getColor(final int x, final int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("args must be positive");
        }

        Color result = null;
        Map<Integer, Color> colors = cache.get(x);
        if (colors == null) {
            result = reader.getColor(x, y);
            Map<Integer, Color> newColors = new HashMap<>();
            newColors.put(y, result);
            cache.put(x, newColors);
        } else {
            result = colors.get(y);
            if (result == null) {
                result = reader.getColor(x, y);
                colors.put(y, result);
            }
        }
        return result;
    }

    /**
     * 指定された座標の色がキャッシュされているか
     *
     * @param x x座標
     * @param y y座標
     * @return　キャッシュされているか
     *
     * @throws IllegalArgumentException 引数が負の値の場合。
     */
    public boolean isCachedColor(final int x, final int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("args must be positive");
        }

        Map<Integer, Color> colors = cache.get(x);
        if (colors == null) {
            return false;
        }
        return colors.get(y) != null;
    }

}