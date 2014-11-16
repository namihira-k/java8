/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class ImageUtils {

    /**
     * ぼかし処理を行う画像変換オブジェクトを返却します。
     *
     * @return　ぼかし処理を行う画像変換オブジェクト
     */
    public static ColorTransformer getHueImageTransformer(final int xStart, final int xEnd,
            final int yStart, final int yEnd){
        ColorTransformer transformer = (x, y, reader) -> {
            final PixelReaderWrapper readerWrapper = new PixelReaderWrapper(reader);
            if ( x <= xStart || xEnd <= x ||
                 y <= yStart || yEnd <= y ) {
                     return readerWrapper.getColor(x, y);
            }

            final int ELEMENTS_AROUND = 8;
            List<Color> elements = new ArrayList<>(ELEMENTS_AROUND);
            elements.add(readerWrapper.getColor(x-1, y-1));
            elements.add(readerWrapper.getColor(x-1, y  ));
            elements.add(readerWrapper.getColor(x-1, y+1));
            elements.add(readerWrapper.getColor(x  , y-1));
            elements.add(readerWrapper.getColor(x  , y+1));
            elements.add(readerWrapper.getColor(x+1, y-1));
            elements.add(readerWrapper.getColor(x+1, y  ));
            elements.add(readerWrapper.getColor(x+1, y+1));

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
    public static ColorTransformer getEdgeImageTransformer(final int xStart, final int xEnd,
            final int yStart, final int yEnd){
        ColorTransformer transformer = (x, y, reader) -> {
            final PixelReaderWrapper readerWrapper = new PixelReaderWrapper(reader);
            if ( x <= xStart || xEnd <= x ||
                 y <= yStart || yEnd <= y ) {
                    return readerWrapper.getColor(x, y);
            }
            final Color C = readerWrapper.getColor(x, y);
            final Color NORTH = readerWrapper.getColor(x,   y-1);
            final Color EAST  = readerWrapper.getColor(x+1, y);
            final Color SOUTH = readerWrapper.getColor(x,   y+1);
            final Color WEST  = readerWrapper.getColor(x-1, y);

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