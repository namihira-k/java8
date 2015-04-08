/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Objects;

public class LabeledPoint {

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
        LabeledPoint other = (LabeledPoint) obj;
        return    Objects.equals(this.label, other.label)
               && Objects.equals(this.x, other.x)
               && Objects.equals(this.y, other.y);
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
