/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

@SuppressWarnings("unchecked")
@SupportedAnnotationTypes({"jp.co.namihira.java8.TestCase", "jp.co.namihira.java8.TestCases"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestCaseProcessor extends AbstractProcessor{

    private final static Map<String, Class<Annotation>> MAP = new HashMap<>();

    static {
        try {
            MAP.put("TestCase",
                    (Class<Annotation>) Class.forName("jp.co.namihira.java8.TestCase"));
            MAP.put("TestCases",
                    (Class<Annotation>) Class.forName("jp.co.namihira.java8.TestCases"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv){
        for (TypeElement t : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(t)) {
               ElementKind kind = e.getKind();
               if (!kind.equals(ElementKind.METHOD)) {
                   throw new IllegalStateException("must give this annotation methods");
               }

               // TestCaseアノテーション一覧を取得する
               TestCase[] testCases = null;
               final String annotationName = t.getSimpleName().toString();
               Class<Annotation> annotationClass = MAP.get(annotationName);
               switch (annotationName) {
                   case "TestCase":
                       testCases = (TestCase[]) e.getAnnotationsByType(annotationClass);
                       break;
                   case "TestCases":
                       TestCases[] testCaseses = (TestCases[]) e.getAnnotationsByType(annotationClass);
                       testCases = Stream.of(testCaseses)
                                         .flatMap(tc -> Stream.of(tc.value()))
                                         .toArray(TestCase[]::new);
                       break;
                   default:
                       throw new AssertionError("no reached");
               }

               // Elementに対するMethodオブジェクトを取得する
               Optional<Method> method = null;
               try {
                   Method[] methods = Class.forName(e.getEnclosingElement().toString()).getMethods();
                   method = Stream.of(methods)
                                  .filter(m -> m.getName().equals(e.getSimpleName().toString()))
                                  .findFirst();
               } catch (ClassNotFoundException ex) {
                   throw new RuntimeException(ex);
               }

               // Methodオブジェクトを実行し、結果を確認する
               final Method m = method.get();
               Stream.of(testCases).forEach(testCase -> {
                   final int params = Integer.valueOf(testCase.params());
                   final long expected = Long.valueOf(testCase.expected());
                   try {
                       final long result = (long) m.invoke(null, params);
                       System.out.println(m);
                       System.out.println("params : " + params + "\t"
                                        + "result : " + result + "\t"
                                        + "expected : "  + expected);
                       if (expected != result) {
                           throw new IllegalStateException("not match annotation condition.");
                       }
                   } catch (ReflectiveOperationException ex) {
                       throw new RuntimeException(ex);
                   }
               });
            }
       }
       return true;
   }

}
