/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(TestCases.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestCase {
    String params();
    String expected();
}