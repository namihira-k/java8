/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class ImageUtilsTest {

    final int WIDTH = 3;
    WritableImage in;

    /*
     * (image)
     * RRR
     * GGG
     * BBB
     */
    @Before
    public void setUp(){
        in = new WritableImage(WIDTH, WIDTH);
        final Color COLOR_1 = Color.RED;
        for (int i = 0; i < WIDTH; i++) {
            in.getPixelWriter().setColor(i, 0, COLOR_1);
        }
        final Color COLOR_2 = Color.GREEN;
        for (int i = 0; i < WIDTH; i++) {
            in.getPixelWriter().setColor(i, 1, COLOR_2);
        }
        final Color COLOR_3 = Color.BLUE;
        for (int i = 0; i < WIDTH; i++) {
            in.getPixelWriter().setColor(i, 2, COLOR_3);
        }
    }

    @Test
    public void test_getHueImageTransformer() {
        // prepare
        // - nothing

        // action
        final Image result = LatentImage.form(in)
                .transform(ImageUtils.getHueImageTransformer())
                .toImage();

        // check
        final Color COLOR_CENTER = result.getPixelReader().getColor(1, 1);

        final double R = ((Color.RED.getRed() * WIDTH)
                        + (Color.GREEN.getRed() * (WIDTH - 1))
                        + (Color.BLUE.getRed() * WIDTH))
                        / ((WIDTH*WIDTH - 1));
        final double G = ((Color.RED.getGreen() * WIDTH)
                        + (Color.GREEN.getGreen() * (WIDTH - 1))
                        + (Color.BLUE.getGreen() * WIDTH))
                        / ((WIDTH*WIDTH - 1));
        final double B = ((Color.RED.getBlue() * WIDTH)
                        + (Color.GREEN.getBlue() * (WIDTH - 1))
                        + (Color.BLUE.getBlue() * WIDTH))
                        / ((WIDTH*WIDTH - 1));
        final Color EXPECT = Color.color(R, G, B);

        assertTrue((EXPECT.getRed() - COLOR_CENTER.getRed()) < 0.1);
        assertTrue((EXPECT.getGreen() - COLOR_CENTER.getGreen()) < 0.1);
        assertTrue((EXPECT.getBlue() - COLOR_CENTER.getBlue()) < 0.1);
    }

    @Test
    public void test_getEdgeImageTransformer() {
        // prepare
        // - nothing

        // action
        final Image result = LatentImage.form(in)
                .transform(ImageUtils.getEdgeImageTransformer())
                .toImage();

        // check
        final Color COLOR_CENTER = result.getPixelReader().getColor(1, 1);

        final double R = correct((4 * Color.GREEN.getRed())
                - Color.RED.getRed() - Color.GREEN.getRed()
                - Color.GREEN.getRed() - Color.BLUE.getRed());
        final double G = correct((4 * Color.GREEN.getGreen())
                - Color.RED.getGreen() - Color.GREEN.getGreen()
                - Color.GREEN.getGreen() - Color.BLUE.getGreen());
        final double B = correct((4 * Color.GREEN.getBlue())
                - Color.RED.getBlue() - Color.GREEN.getBlue()
                - Color.GREEN.getBlue() - Color.BLUE.getBlue());

        final Color EXPECT = Color.color(R, G, B);

        assertTrue((EXPECT.getRed() - COLOR_CENTER.getRed()) < 0.1);
        assertTrue((EXPECT.getGreen() - COLOR_CENTER.getGreen()) < 0.1);
        assertTrue((EXPECT.getBlue() - COLOR_CENTER.getBlue()) < 0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_transform_null() {
        // prepare
        // - nothing

        // action
        LatentImage.form(in).transform(null).toImage();

        // check
        // - checked Exception
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