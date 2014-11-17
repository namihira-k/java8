/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class ImageUtilsTest {

    @Test
    public void test_toArrays() {
        // prepare
        Image image = new Image("black.png");

        // action
        Color[][] result = ImageUtils.toArrays(image);

        // check
        final int WIDTH = (int) image.getWidth();
        final int HEIGHT = (int) image.getHeight();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                assertEquals(image.getPixelReader().getColor(x, y), result[y][x]);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toArrays_null() {
        // prepare
        // nothing

        // action
        ImageUtils.toArrays(null);

        // check
        // - throw Exception
    }

    @Test
    public void test_toImage() {
        // prepare
        final int WIDTH = 3;
        Color[][] colors = new Color[WIDTH][WIDTH];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                colors[y][x] = Color.BLACK;
            }
        }

        // action
        Image result = ImageUtils.toImage(colors);

        // check
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                assertEquals(colors[y][x], result.getPixelReader().getColor(x, y));
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toImage_null() {
        // prepare
        // - nothing

        // action
        ImageUtils.toImage(null);

        // check
        // - checked Exception;
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toImage_null_in_array() {
        // prepare
        Color[][] colors = new Color[3][3];

        // action
        ImageUtils.toImage(colors);

        // check
        // - checked Exception;
    }


}