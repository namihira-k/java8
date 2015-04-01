/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */


package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;


public class MainTest {

    @Test
    public void test_sort(){
        // setup
        final Person a = new Person("a");
        final Person b = new Person("b");
        final Person n = new Person();

        final Person[] people = new Person[]{b, n, a};
        final Person[] people2 = people.clone();

        // action
        Arrays.sort(people, Comparator.comparing(Person::getName, Comparator.nullsLast(Comparator.<String>reverseOrder())));

        // check
        Arrays.sort(people2, Comparator.comparing(Person::getName, Comparator.nullsFirst(Comparator.<String>naturalOrder()).reversed()));
        assertTrue(Arrays.equals(people, people2));
    }

    private class Person {
        private String name;

        Person() {
        }

        Person(String name) {
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }

}
