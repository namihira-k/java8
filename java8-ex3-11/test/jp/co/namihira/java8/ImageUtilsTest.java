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
    public void test_compose() {
        // prepare
        Image image = new Image("black.png");
        final Color FRAME_COLOR = Color.GREEN;
        final int FRAME_WIDTH = 10;
        final Color TRANSFORMED_COLOR = Color.WHITE;

        // action
        final ColorTransformer colorTransformer = ImageUtils.compose(
                ImageUtils.toColorTransformer((color) -> TRANSFORMED_COLOR),
                (x, y, c) ->
                    (x < FRAME_WIDTH || x > (image.getWidth() - FRAME_WIDTH) ||
                     y < FRAME_WIDTH || y > (image.getHeight() - FRAME_WIDTH) ?
                             FRAME_COLOR : c));
        final Image result = ImageUtils.transform(image, colorTransformer);

        // check
        final int WIDTH = (int) image.getWidth();
        final int HEIGHT = (int) image.getHeight();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                final Color COLOR_XY = result.getPixelReader().getColor(x, y);
                if ( x < FRAME_WIDTH || x > (image.getWidth() - FRAME_WIDTH) ||
                     y < FRAME_WIDTH || y > (image.getHeight() - FRAME_WIDTH)) {
                    assertEquals(FRAME_COLOR, COLOR_XY);
                } else {
                    assertEquals(TRANSFORMED_COLOR, COLOR_XY);
                }
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_compose_null_first() {
        // prepare
        // - nothing

        // action
        ImageUtils.compose(null, ImageUtils.toColorTransformer((color) -> Color.WHITE));

        // check
        // - nothing
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_compose_null_second() {
        // prepare
        // - nothing

        // action
        ImageUtils.compose(ImageUtils.toColorTransformer((color) -> Color.WHITE), null);

        // check
        // - nothing
    }

    @Test
    public void test_toColorTransformer() {
        // prepare
        Image image = new Image("black.png");
        final Color TRANSFORMED_COLOR = Color.WHITE;

        // action
        final ColorTransformer colorTransformer =  ImageUtils.toColorTransformer((color) -> TRANSFORMED_COLOR);
        final Image result = ImageUtils.transform(image, colorTransformer);

        // check
        final int WIDTH = (int) image.getWidth();
        final int HEIGHT = (int) image.getHeight();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                final Color COLOR_XY = result.getPixelReader().getColor(x, y);
                assertEquals(TRANSFORMED_COLOR, COLOR_XY);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toColorTransformer_null() {
        // prepare
        // - nothing

        // action
        ImageUtils.toColorTransformer(null);

        // check
        // - checked Exception
    }


}