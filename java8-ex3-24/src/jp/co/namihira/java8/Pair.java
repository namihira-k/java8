/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.function.BiFunction;

public class Pair<T> {

    private T left;
    private T right;

    public Pair(T left, T right) {
        this.left = left;
        this.right = right;
    }

    /**
     * このPairに対して、指定された関数を適用した結果から構成されるPairを返します。
     * mapperの引数として、left値とrigth値が渡されます。（left, right）
     *
     * @param mapper 処理内容
     * @return 適用した結果から構成されるPair
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     */
    public <U> Pair<U> flatMap(BiFunction<? super T, ? super T, Pair<U>> mapper) {
        if (mapper == null) {
            throw new IllegalArgumentException("mapper must not be null");
        }
        return mapper.apply(this.left, this.right);
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