/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

public class TestCaseAnnotationTest {

    @Test
    public void test_testCaseAnnotarion() throws ReflectiveOperationException{
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
        annotationMapping.forEach((method, annotations) -> {
            Stream.of(annotations).forEach(a -> {
                final int params = Integer.valueOf(a.params());
                final long expected = Long.valueOf(a.expected());
                try {
                    final long result = (long) method.invoke(null, params);
                    assertEquals(expected, result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

}