/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * TestCase アノテーションを定義し、そのアノテーションを持つクラスをロードして、
 * アノテーションが付けられたメソッドを呼び出し、メソッドが期待した値を生成するかを
 * 検査するプログラムを実装しなさい。
 * パラメータと戻り値は整数だと想定しなさい。
 *
 */

package jp.co.namihira.java8;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args){
        // setup
        final Map<Method, TestCase[]> annotationMapping = new HashMap<>();
        final Class<CommonUtils> testClass = CommonUtils.class;

        // action
        final Method[] methods = testClass.getMethods();
        Stream.of(methods).forEachOrdered(m -> {
            final TestCase[] annotations = m.getAnnotationsByType(TestCase.class);
            if (annotations == null || annotations.length == 0) {
                return;
            }
            annotationMapping.put(m, annotations);
        });

        // check
        System.out.println("- params\texpected\tresult");
        annotationMapping.forEach((method, annotations) -> {
            System.out.println(method);
            Stream.of(annotations).forEach(a -> {
                final int params = Integer.valueOf(a.params());
                final long expected = Long.valueOf(a.expected());
                try {
                    final long result = (long) method.invoke(null, params);
                    System.out.println(params + "\t" + expected + "\t" + result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });

    }

}