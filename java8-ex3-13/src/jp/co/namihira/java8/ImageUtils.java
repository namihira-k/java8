/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

/**
 * 画像に関するユーティリティクラス
 */
public class ImageUtils {

    /**
     * ぼかし処理を行う画像変換オブジェクトを返却します。
     *
     * @return　ぼかし処理を行う画像変換オブジェクト
     */
    public static ImageTransformer getHueImageTransformer(){
        ImageTransformer transformer =
                (x, y, in) -> {
                    final PixelReader pr = in.getPixelReader();
                    if (onEdge(x, y, in)) {
                        return pr.getColor(x, y);
                    }

                    final int ELEMENTS_AROUND = 8;
                    List<Color> elements = new ArrayList<>(ELEMENTS_AROUND);
                    elements.add(pr.getColor(x-1, y-1));
                    elements.add(pr.getColor(x-1, y  ));
                    elements.add(pr.getColor(x-1, y+1));
                    elements.add(pr.getColor(x  , y-1));
                    elements.add(pr.getColor(x  , y+1));
                    elements.add(pr.getColor(x+1, y-1));
                    elements.add(pr.getColor(x+1, y  ));
                    elements.add(pr.getColor(x+1, y+1));

                    final double R = correct(elements.stream().reduce(0.0,
                            (total, color) -> total + color.getRed(),
                            (total1, total2) -> total1 + total2) / ELEMENTS_AROUND);
                    final double G = correct(elements.stream().reduce(0.0,
                            (total, color) -> total + color.getGreen(),
                            (total1, total2) -> total1 + total2) / ELEMENTS_AROUND);
                    final double B = correct(elements.stream().reduce(0.0,
                            (total, color) -> total + color.getBlue(),
                            (total1, total2) -> total1 + total2) / ELEMENTS_AROUND);

                    return Color.color(R, G, B);
                };
        return transformer;
    }

    /**
     * エッジ処理を行う画像変換オブジェクトを返却します。
     *
     * @return　ぼかし処理を行う画像変換オブジェクト
     */
    public static ImageTransformer getEdgeImageTransformer(){
        ImageTransformer transformer =
                (x, y, in) -> {
                    final PixelReader pr = in.getPixelReader();
                    if (onEdge(x, y, in)) {
                        return pr.getColor(x, y);
                    }
                    final Color C = pr.getColor(x, y);
                    final Color NORTH = pr.getColor(x,   y-1);
                    final Color EAST  = pr.getColor(x+1, y);
                    final Color SOUTH = pr.getColor(x,   y+1);
                    final Color WEST  = pr.getColor(x-1, y);

                    final double R = correct((4.0 * C.getRed())
                            - NORTH.getRed() - EAST.getRed() - SOUTH.getRed() - WEST.getRed());
                    final double G = correct((4.0 * C.getGreen())
                            - NORTH.getGreen() - EAST.getGreen() - SOUTH.getGreen() - WEST.getGreen());
                    final double B = correct((4.0 * C.getBlue())
                            - NORTH.getBlue() - EAST.getBlue() - SOUTH.getBlue() - WEST.getBlue());

                    return Color.color(R, G, B);
                };
        return transformer;
    }

    private static boolean onEdge(final int x, final int y, final Image in) {
        return ( x <= 0 || ((int) in.getWidth() - 1)  <= x ||
                 y <= 0 || ((int) in.getHeight() - 1) <= y );
    }

    private static double correct(final double value) {
        final double MIN = 0.0;
        final double MAX = 1.0;
        if (value < MIN) {
            return MIN;
        }
        if (MAX < value) {
            return MAX;
        }
        return value;
    }

}