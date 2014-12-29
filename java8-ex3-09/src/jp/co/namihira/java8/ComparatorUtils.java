/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Comparatorに関するユーティリティクラス
 */
public class ComparatorUtils {

    /**
     * 指定された順序で、指定されたフィールドを比較するコンパレータを生成します。
     *
     * @param fieldname 比較対象のフィールド
     * @return　コンパレータ
     *
     * @throws IllegalArgumentException fieldnameがnullの場合。
     *
     */
    @SuppressWarnings("unchecked")
    public static <T> Comparator<T> lexicographicComparator(String... fieldname) {
        if (fieldname == null) {
            throw new IllegalArgumentException("fieldname must not be null");
        }

        if (fieldname.length == 0) {
            throw new IllegalArgumentException("fieldname must not be empty");
        }

        Comparator<Object> comp = (Object obj1, Object obj2) -> {
            if (obj1 == null || obj2 == null) {
                throw new IllegalArgumentException("args must not be null");
            }

            Comparable<T> name1;
            Comparable<T> name2;
            try {
                name1 = (Comparable<T>) getAccessibleField(obj1, fieldname[0]);
                name2 = (Comparable<T>) getAccessibleField(obj2, fieldname[0]);
            } catch (IllegalArgumentException | NoSuchFieldException  e) {
                throw new IllegalArgumentException(e);
            } catch (IllegalAccessException | SecurityException e) {
                throw new RuntimeException(e);
            }

            final int result = name1.compareTo((T) name2);
            if (result == 0) {
                List<String> list = new LinkedList<>(Arrays.asList(fieldname));
                list.remove(0);
                return (list.size() == 0) ?
                        result : lexicographicComparator(list.toArray(new String[0])).compare(obj1, obj2);
            } else {
                return result;
            }
        };

        return (Comparator<T>) comp;
    }

    private static Object getAccessibleField(final Object obj, final String fieldname)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldname);
        field.setAccessible(true);
        return field.get(obj);
    }

}