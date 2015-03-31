/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * Comparatorインターフェースのメソッドだけを使用して、
 * Point2Dに対する全順序（total ordering）なコンパレータを定義しなさい。
 * （すなわち、同値のオブジェクトに対してのみ0を返すコンパレータです）。
 * ヒント：最初にx座標を比較し、その後に、y座標を比較します。
 * 同じことをRectangle2Dに対して行いなさい。
 */

package jp.co.namihira.java8;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Point2DComparator");
        excutePoint2DComparator();

        System.out.println("Rectangle2DComparator");
        excuteRectangle2DComparator();
    }

    private static void excutePoint2DComparator(){
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
        System.out.println("comparator.compare(p1, p) : " + comparator.compare(p1, p));
        System.out.println("comparator.compare(p1, p0) : " + comparator.compare(p1, p0));
        System.out.println("comparator.compare(p1, p11) : " + comparator.compare(p1, p11));
        System.out.println("comparator.compare(p1, p12) : " + comparator.compare(p1, p12));
        System.out.println("comparator.compare(p1, p21) : " + comparator.compare(p1, p21));
    }

    private static void excuteRectangle2DComparator(){
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
        System.out.println("comparator.compare(r1, r) : " + comparator.compare(r1, r));
        System.out.println("comparator.compare(r1, r0) : " + comparator.compare(r1, r0));
        System.out.println("comparator.compare(r1, r1111) : " + comparator.compare(r1, r1111));
        System.out.println("comparator.compare(r1, r2111) : " + comparator.compare(r1, r2111));
        System.out.println("comparator.compare(r1, r1211) : " + comparator.compare(r1, r1211));
        System.out.println("comparator.compare(r1, r1121) : " + comparator.compare(r1, r1121));
        System.out.println("comparator.compare(r1, r1112) : " + comparator.compare(r1, r1112));
    }

}
