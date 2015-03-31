/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;

public class ComparatorFactory {

    public static Comparator<Point2D> getPoint2DComparator(){
        return Comparator.comparingDouble(Point2D::getX)
                         .thenComparingDouble(Point2D::getY);
    }

    public static Comparator<Rectangle2D> getRectangle2DComparator(){
        return Comparator.comparingDouble(Rectangle2D::getX)
                         .thenComparingDouble(Rectangle2D::getY)
                         .thenComparingDouble(Rectangle2D::getHeight)
                         .thenComparingDouble(Rectangle2D::getWidth);
    }


}
