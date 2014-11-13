/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class ImageUtilsTest {

    @Test
    public void test_transform() {
        // prepare
        Image image = new Image("black.png");
        final Color FRAME_COLOR = Color.GREEN;
        final int FRAME_WIDTH = 10;
        final Color TRANSFORMED_COLOR = Color.WHITE;

        // action
        final Image result = LatentImage.form(image)
                .transform((color) -> TRANSFORMED_COLOR)
                .transform(
                        (x, y, c) -> inFrame(x, y, image, FRAME_WIDTH) ? FRAME_COLOR : c
                )
                .toImage();

        // check
        final int WIDTH = (int) image.getWidth();
        final int HEIGHT = (int) image.getHeight();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                final Color COLOR_XY = result.getPixelReader().getColor(x, y);
                if (inFrame(x, y, image, FRAME_WIDTH)) {
                    assertEquals(FRAME_COLOR, COLOR_XY);
                } else {
                    assertEquals(TRANSFORMED_COLOR, COLOR_XY);
                }
            }
        }
    }

    @Test
    public void test_transform_order() {
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


    private boolean inFrame(final int x, final int y, final Image image, final int frameWidth) {
        final boolean result = x < frameWidth || x > (image.getWidth() - frameWidth) ||
                               y < frameWidth || y > (image.getHeight() - frameWidth);
        return result;
    }

    @Test
    public void test_toImage() {
        // prepare
        Image image = new Image("black.png");

        // action
        final Image result = LatentImage.form(image).toImage();

        // check
        final int WIDTH = (int) image.getWidth();
        final int HEIGHT = (int) image.getHeight();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                final Color COLOR_ORIGIN = image.getPixelReader().getColor(x, y);
                final Color COLOR_RESULT = result.getPixelReader().getColor(x, y);
                assertEquals(COLOR_ORIGIN, COLOR_RESULT);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_form() {
        // prepare
        // - nothing

        // action
        LatentImage.form(null);

        // check
        // - checked Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_transform_null_unaryOperator() {
        // prepare
        Image image = new Image("black.png");

        // action
        LatentImage.form(image).transform((UnaryOperator<Color>)null).toImage();

        // check
        // - checked Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_transform_null_colorTransformer() {
        // prepare
        Image image = new Image("black.png");

        // action
        LatentImage.form(image).transform((ColorTransformer)null).toImage();

        // check
        // - checked Exception
    }



}