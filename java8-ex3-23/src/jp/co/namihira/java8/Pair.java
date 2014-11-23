/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.function.Function;

public class Pair<T> {

    private T left;
    private T right;

    public Pair(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public <U> Pair<U> map(Function<? super T,? extends U> mapper) {
        if (mapper == null) {
            throw new IllegalArgumentException("mappper must not be null");
        }

        return new Pair<>(mapper.apply(this.left), mapper.apply(this.right));
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public T getRight() {
        return right;
    }

    public void setRight(T right) {
        this.right = right;
    }

}