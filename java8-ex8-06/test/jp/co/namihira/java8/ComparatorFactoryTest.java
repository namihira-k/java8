/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;

import org.junit.Test;

public class ComparatorFactoryTest {

    @Test
    public void test_getPoint2DComparator(){
        // setup
        final Point2D p = new Point();
        final Point2D p0 = new Point(0, 0);
        final Point2D p1 = new Point(1, 1);
        final Point2D p11 = new Point(1, 1);
        final Point2D p12 = new Point(1, 2);
        final Point2D p21 = new Point(2, 1);

        // action
        final Comparator<Point2D> comparator = ComparatorFactory.getPoint2DComparator();

        // check
        assertTrue(0 < comparator.compare(p1, p));
        assertTrue(0 < comparator.compare(p1, p0));
        assertTrue(0 == comparator.compare(p1, p11));
        assertTrue(0 > comparator.compare(p1, p12));
        assertTrue(0 > comparator.compare(p1, p21));
    }

    @Test(expected = NullPointerException.class)
    public void test_getPoint2DComparator_null_1st(){
        // setup
        final Point2D p = new Point();
        final Comparator<Point2D> comparator = ComparatorFactory.getPoint2DComparator();

        // action
        comparator.compare(null, p);

        // check
        // - throw Exception
   }

    @Test(expected = NullPointerException.class)
    public void test_getPoint2DComparator_null_2st(){
        // setup
        final Point2D p = new Point();
        final Comparator<Point2D> comparator = ComparatorFactory.getPoint2DComparator();

        // action
        comparator.compare(p, null);

        // check
        // - throw Exception
   }

    @Test
    public void test_getRectangle2DComparator(){
        // setup
        final Rectangle2D r = new Rectangle();
        final Rectangle2D r0 = new Rectangle(0, 0, 0, 0);
        final Rectangle2D r1 = new Rectangle(1, 1, 1, 1);
        final Rectangle2D r1111 = new Rectangle(1, 1, 1, 1);
        final Rectangle2D r1211 = new Rectangle(1, 2, 1, 1);
        final Rectangle2D r2111 = new Rectangle(2, 1, 1, 1);
        final Rectangle2D r1121 = new Rectangle(1, 1, 2, 1);
        final Rectangle2D r1112 = new Rectangle(1, 1, 1, 2);

        // action
        final Comparator<Rectangle2D> comparator = ComparatorFactory.getRectangle2DComparator();

        // check
        assertTrue(0 < comparator.compare(r1, r));
        assertTrue(0 < comparator.compare(r1, r0));
        assertTrue(0 == comparator.compare(r1, r1111));
        assertTrue(0 > comparator.compare(r1, r2111));
        assertTrue(0 > comparator.compare(r1, r1211));
        assertTrue(0 > comparator.compare(r1, r1121));
        assertTrue(0 > comparator.compare(r1, r1112));
    }

    @Test(expected = NullPointerException.class)
    public void test_Rectangle2D_null_1st(){
        // setup
        final Rectangle2D r = new Rectangle();
        final Comparator<Rectangle2D> comparator = ComparatorFactory.getRectangle2DComparator();

        // action
        comparator.compare(null, r);

        // check
        // - throw Exception
   }

    @Test(expected = NullPointerException.class)
    public void test_getRectangle2DComparator_null_2st(){
        // setup
        final Rectangle2D r = new Rectangle();
        final Comparator<Rectangle2D> comparator = ComparatorFactory.getRectangle2DComparator();

        // action
        comparator.compare(r, null);

        // check
        // - throw Exception
   }

}
