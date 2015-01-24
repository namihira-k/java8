/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Arrays;
import java.util.Objects;

/**
 * フィボナッチ数に関するクラス
 */
public class Fibonacci {

    /**
     * 指定したインデックスのフィボナッチ数を返します
     *
     * @param index インデックス
     * @return　指定したインデックスのフィボナッチ数
     *
     * @throws IllegalArgumentException インデックスが負の値の場合
     *
     */
    public static long getFibonacciNumber(final int index){
        if (index < 0) {
            throw new IllegalArgumentException("index must be positive number");
        }

        if (index == 0 || index == 1) {
            return index;
        }

        final Matrix[] fs = new Matrix[index];
        Arrays.parallelSetAll(fs, i -> new Matrix());
        Arrays.parallelPrefix(fs, (f1, f2) -> f1.times(f2));

        return fs[index - 1].getElement(0, 1);
    }

    /**
     * フィボナッチ数の行列に関するクラス
     */
    public static class Matrix {

        private final int size = 2;
        private int[][] elements = new int[size][size];

        public Matrix(){
            this.setElement(0, 0, 1);
            this.setElement(0, 1, 1);
            this.setElement(1, 0, 1);
            this.setElement(1, 1, 0);
        }

        public Matrix times(Matrix matrix) {
            Objects.requireNonNull(matrix);

            final int result00 = this.getElement(0, 0) * matrix.getElement(0, 0)
                               + this.getElement(0, 1) * matrix.getElement(1, 0);

            final int result01 = this.getElement(0, 0) * matrix.getElement(0, 1)
                               + this.getElement(0, 1) * matrix.getElement(1, 1);

            final int result10 = this.getElement(1, 0) * matrix.getElement(0, 0)
                               + this.getElement(1, 1) * matrix.getElement(1, 0);

            final int result11 = this.getElement(1, 0) * matrix.getElement(0, 1)
                               + this.getElement(1, 1) * matrix.getElement(1, 1);

            this.setElement(0, 0, result00);
            this.setElement(0, 1, result01);
            this.setElement(1, 0, result10);
            this.setElement(1, 1, result11);
            return this;
        }

        public int getElement(final int row, final int column){
            check(row, column);
            return this.elements[row][column];
        }

        public void setElement(final int row, final int column, final int newElement){
            check(row, column);
            this.elements[row][column] = newElement;
        }

        private void check(final int row, final int column){
            if (row < 0 || size <= row) {
                throw new IllegalArgumentException("row must be in " + size);
            }
            if (column < 0 || size <= column) {
                throw new IllegalArgumentException("column must be in " + size);
            }
        }
    }
}
