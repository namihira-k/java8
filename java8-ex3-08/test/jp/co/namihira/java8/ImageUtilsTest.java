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
    public void test_transform() {
        // prepare
        Image image = new Image("black.png");
        final Color FRAME_COLOR = Color.GREEN;
        final int FRAME_WIDTH = 10;

        // action
        final ColorTransformer colorTransformer = ImageUtils.getFrameColorTransformer(FRAME_WIDTH, FRAME_COLOR);
        final Image result = ImageUtils.transform(image, colorTransformer);

        // check
        final int WIDTH = (int) image.getWidth();
        final int HEIGHT = (int) image.getHeight();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                final Color COLOR_XY = result.getPixelReader().getColor(x, y);
                if (x < FRAME_WIDTH || x > (image.getWidth() - FRAME_WIDTH) ||
                        y < FRAME_WIDTH || y > (image.getHeight() - FRAME_WIDTH)) {
                    assertEquals(FRAME_COLOR, COLOR_XY);
                } else {
                    assertEquals(Color.BLACK, COLOR_XY);
                }
            }
        }
    }

    @Test
    public void test_getFrameColorTransformer_frame_width_over() {
        // prepare
        Image image = new Image("black.png");
        final Color FRAME_COLOR = Color.GREEN;
        final int FRAME_WIDTH = Integer.MAX_VALUE;

        // action
        final ColorTransformer colorTransformer = ImageUtils.getFrameColorTransformer(FRAME_WIDTH, FRAME_COLOR);

        // check
        final int WIDTH = (int) image.getWidth();
        final int HEIGHT = (int) image.getHeight();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Color result = colorTransformer.apply(x, y, image);
                assertEquals(FRAME_COLOR, result);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getFrameColorTransformer_frame_width_negative() {
        // prepare
        Image image = new Image("black.png");
        final Color FRAME_COLOR = Color.GREEN;
        final int FRAME_WIDTH = Integer.MIN_VALUE;

        // action
        final ColorTransformer colorTransformer = ImageUtils.getFrameColorTransformer(FRAME_WIDTH, FRAME_COLOR);
        final int TMP_POSITION = 10;
        colorTransformer.apply(TMP_POSITION, TMP_POSITION, image);

        // check
        // - checked Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getFrameColorTransformer_null_frame_color() {
        // prepare
        Image image = new Image("black.png");
        final int FRAME_WIDTH = 10;

        // action
        final ColorTransformer colorTransformer = ImageUtils.getFrameColorTransformer(FRAME_WIDTH, null);
        final int TMP_POSITION = 10;
        colorTransformer.apply(TMP_POSITION, TMP_POSITION, image);

        // check
        // - checked Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_transform_null_image() {
        // prepare
        final Color FRAME_COLOR = Color.GREEN;
        final int FRAME_WIDTH = 10;
        final ColorTransformer colorTransformer = ImageUtils.getFrameColorTransformer(FRAME_WIDTH, FRAME_COLOR);

        // action
        ImageUtils.transform(null, colorTransformer);

        // check
        // - checked Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_transform_null_colorTransformer() {
        // prepare
        Image image = new Image("black.png");

        // action
        ImageUtils.transform(image, null);

        // check
        // - checked Exception
    }


}