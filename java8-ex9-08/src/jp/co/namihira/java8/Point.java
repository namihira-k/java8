/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int compareTo(Point other) {
        long diff = (long)x - other.x;
        if (diff != 0) {
            return diff < 0 ? -1 : 1;
        }

        diff = (long)y - other.y;
        if (diff != 0) {
            return diff < 0 ? -1 : 1;
        } else {
            return 0;
        }
    }

    public int compareToWithIntegerCompareTo(Point other) {
        int diff = Integer.compare(x, other.x);
        if (diff != 0)
            return diff;
        return Integer.compare(y, other.y);
    }

}
