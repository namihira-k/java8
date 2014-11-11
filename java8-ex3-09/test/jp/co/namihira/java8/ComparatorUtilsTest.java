/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class ComparatorUtilsTest {

    @Test
    public void test_lexicographicComparator_one(){
        // prepare
        Person namihira1 = new Person("Kosuke", "Namihira");
        Person namihira2 = new Person("Kosuke", "Namihira");

        // action
        Comparator<Object> comp = ComparatorUtils.lexicographicComparator("lastname");

        // check
        final int RESULT = comp.compare(namihira1, namihira2);
        assertEquals(0, RESULT);
    }

    @Test
    public void test_lexicographicComparator_two(){
        // prepare
        Person namihira1 = new Person("Kosuke", "Namihira");
        Person namihira2 = new Person("Kosuke", "ZZZNamihira");

        // action
        Comparator<Object> comp = ComparatorUtils.lexicographicComparator("firstname", "lastname");

        // check
        final int RESULT = comp.compare(namihira1, namihira2);
        assertTrue(RESULT < 0);
    }

    @Test
    public void test_lexicographicComparator_three(){
        // prepare
        Person namihira1 = new Person("Kosuke", "Namihira", 29);
        Person namihira2 = new Person("Kosuke", "Namihira", 30);

        // action
        Comparator<Object> comp = ComparatorUtils.lexicographicComparator("firstname", "lastname", "age");

        // check
        final int RESULT = comp.compare(namihira1, namihira2);
        assertTrue(RESULT < 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_lexicographicComparator_mismatch_parameter(){
        // prepare
        Person namihira1 = new Person("Kosuke", "Namihira");
        Person namihira2 = new Person("Kosuke", "Namihira");

        // action
        Comparator<Object> comp = ComparatorUtils.lexicographicComparator("lastname", "mail");
        comp.compare(namihira1, namihira2);

        // check
        // - checked Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_lexicographicComparator_null_fieldname(){
        // prepare
        // - nothing

        // action
        ComparatorUtils.lexicographicComparator(null);

        // check
        // - checked Exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_compare_null_args(){
        // prepare
        // - nothing

        // action
        Comparator<Object> comp = ComparatorUtils.lexicographicComparator("lastname");
        comp.compare(null, null);

        // check
        // - checked Exception
    }


}