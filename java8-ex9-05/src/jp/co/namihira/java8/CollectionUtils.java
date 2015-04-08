/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CollectionUtils {

    /**
     * 指定したbyte配列の順番を反転したbyte配列を返します。
     *
     * @param bytes 反転するbyte配列
     * @return　反転したbyte配列
     *
     * @throws NullPointerException 引数がnullの場合。
     */
    public static byte[] reverse(final byte[] bytes) {
        Objects.requireNonNull(bytes);

        if (bytes.length == 0) {
            return new byte[0];
        }

        final List<Byte> list = asList(bytes);
        Collections.reverse(list);
        return toArray(list);
    }

    /**
     * 指定したbyte配列をリストに変換します。
     *
     * @param bytes byte配列
     * @return　リスト
     *
     * @throws NullPointerException 引数がnullの場合。
     */
    private static List<Byte> asList(byte[] bytes){
        Objects.requireNonNull(bytes);

        if (bytes.length == 0) {
            return new ArrayList<>();
        }

        final List<Byte> list = new ArrayList<>(bytes.length);
        for (byte b : bytes) {
            list.add(b);
        }
        return list;
    }

    /**
     * 指定したbyteのリストを配列に変換します。
     *
     * @param bytes リスト
     * @return　byte配列
     *
     * @throws NullPointerException 引数がnullの場合。
     */
    private static byte[] toArray(List<Byte> bytes){
        Objects.requireNonNull(bytes);

        if (bytes.size() == 0) {
            return new byte[0];
        }

        final byte[] array = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            array[i] = bytes.get(i);
        }
        return array;
    }

}
