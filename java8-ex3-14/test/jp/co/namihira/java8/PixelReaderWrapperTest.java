/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class PixelReaderWrapperTest {

    @Test
    public void test_getColor() {
        // prepare
        final int WIDTH = 1;
        WritableImage in = new WritableImage(WIDTH, WIDTH);
        final Color COLOR = Color.RED;
        in.getPixelWriter().setColor(0, 0, COLOR);

        PixelReaderWrapper reader = new PixelReaderWrapper(in.getPixelReader());

        // action
        Color result = reader.getColor(0, 0);

        // check
        assertEquals(COLOR, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getColor_negative() {
        // prepare
        final int WIDTH = 1;
        WritableImage in = new WritableImage(WIDTH, WIDTH);
        final Color COLOR = Color.RED;
        in.getPixelWriter().setColor(0, 0, COLOR);
        PixelReaderWrapper reader = new PixelReaderWrapper(in.getPixelReader());

        // action
        reader.getColor(-1, -1);

        // check
        // - checked Exception
    }

    @Test
    public void test_getColor_cached() {
        // prepare
        final int WIDTH = 1;
        WritableImage in = new WritableImage(WIDTH, WIDTH);
        final Color COLOR = Color.RED;
        in.getPixelWriter().setColor(0, 0, COLOR);

        PixelReaderWrapper reader = new PixelReaderWrapper(in.getPixelReader());

        // action
        reader.getColor(0, 0);

        // check
        assertEquals(true, reader.isCachedColor(0, 0));
    }

    @Test
    public void test_getColor_no_cached() {
        // prepare
        final int WIDTH = 1;
        WritableImage in = new WritableImage(WIDTH, WIDTH);
        final Color COLOR = Color.RED;
        in.getPixelWriter().setColor(0, 0, COLOR);

        PixelReaderWrapper reader = new PixelReaderWrapper(in.getPixelReader());

        // action
        reader.getColor(0, 0);

        // check
        assertEquals(false, reader.isCachedColor(1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_isCached_negative() {
        // prepare
        final int WIDTH = 1;
        WritableImage in = new WritableImage(WIDTH, WIDTH);
        final Color COLOR = Color.RED;
        in.getPixelWriter().setColor(0, 0, COLOR);
        PixelReaderWrapper reader = new PixelReaderWrapper(in.getPixelReader());

        // action
        reader.isCachedColor(-1, -1);

        // check
        // - checked Exception
    }
}