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
    public void test_transform() throws InterruptedException {
        // prepare
        Image image = new Image("queen-mary.png");

        // action
        Image result = ImageUtils.transform(image, (x, y, c) -> Color.GRAY);

        // check
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                assertEquals(Color.GRAY, result.getPixelReader().getColor(x, y));
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_transform_null_image() throws InterruptedException {
        // prepare
        // - nothing

        // action
        ImageUtils.transform(null, (x, y, c) -> Color.GRAY);

        // check
        // - checked Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_transform_null_colorTransformer() throws InterruptedException {
        // prepare
        Image image = new Image("queen-mary.png");

        // action
        ImageUtils.transform(image, (ColorTransformer) null);

        // check
        // - checked Exception
    }

}