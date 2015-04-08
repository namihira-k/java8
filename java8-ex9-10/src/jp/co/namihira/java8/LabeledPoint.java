/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Objects;

public class LabeledPoint implements Comparable<LabeledPoint> {

    private String label;
    private int x;
    private int y;

    public LabeledPoint(String label, int x, int y){
        this.label = label;
        this.x = x;
        this.y = y;
    }

    public String getLable() {
        return label;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(LabeledPoint other) {
        Objects.requireNonNull(other);

        int diff = Objects.toString(label, "").compareTo(
                        Objects.toString(other.label, ""));
        if (diff != 0) {
            return diff;
        }

        diff = Integer.compare(x, other.x);
        if (diff != 0) {
            return diff;
        }

        return Integer.compare(y, other.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LabeledPoint)) {
            return false;
        }
        LabeledPoint o = (LabeledPoint) obj;
        return    Objects.equals(this.label, o.label)
               && Objects.equals(this.x, o.x)
               && Objects.equals(this.y, o.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, x, y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("label:" + Objects.toString(label));
        sb.append(",x:" + x);
        sb.append(",y:" + y);
        return sb.toString();
    }

}
