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
public class LatentImageTest {

    @Test
    public void test_toImage_one() {
        // prepare
        Image image = new Image("black.png");
        final Color TRANSFORMED_COLOR_1 = Color.WHITE;

        // action
        final Image result = LatentImage.form(image)
                .transform((color) -> TRANSFORMED_COLOR_1)
                .toImage();

        // check
        final int WIDTH = (int) image.getWidth();
        final int HEIGHT = (int) image.getHeight();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                final Color COLOR_XY = result.getPixelReader().getColor(x, y);
                assertEquals(TRANSFORMED_COLOR_1, COLOR_XY);
            }
        }
    }

    @Test
    public void test_toImage_two() {
        // prepare
        Image image = new Image("black.png");
        final Color TRANSFORMED_COLOR_1 = Color.WHITE;
        final Color TRANSFORMED_COLOR_2 = Color.GREEN;

        // action
        final Image result = LatentImage.form(image)
                .transform((color) -> TRANSFORMED_COLOR_1)
                .transform((color) -> TRANSFORMED_COLOR_2)
                .toImage();

        // check
        final int WIDTH = (int) image.getWidth();
        final int HEIGHT = (int) image.getHeight();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                final Color COLOR_XY = result.getPixelReader().getColor(x, y);
                assertEquals(TRANSFORMED_COLOR_2, COLOR_XY);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_form_null() {
        // prepare
        final Color TRANSFORMED_COLOR_1 = Color.WHITE;

        // action
        LatentImage.form(null)
            .transform((color) -> TRANSFORMED_COLOR_1)
            .toImage();

        // check
        // - checked Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_transform_null() {
        // prepare
        Image image = new Image("black.png");

        // action
        LatentImage.form(image)
            .transform(null)
            .toImage();

        // check
        // - checked Exception
    }

}