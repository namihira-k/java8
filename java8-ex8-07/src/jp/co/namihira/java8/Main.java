/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * reversed を呼び出すことなく、nullsFirst(naturalOrder()).reversed()
 * を表現しなさい。
 */
/*
 * - log
 * before :
 * b,null,a,
 *
 * after :
 * b,a,null,
 *
 * before :
 * b,null,a,
 *
 * after :
 * b,a,null,
 */

package jp.co.namihira.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // setup
        final Person a = new Person("a");
        final Person b = new Person("b");
        final Person n = new Person();

        final Person[] people = new Person[]{b, n, a};
        System.out.println("before : ");
        printArray(people);
        System.out.println("\n");

        // action
        Arrays.sort(people, Comparator.comparing(Person::getName, Comparator.nullsLast(Comparator.<String>reverseOrder())));

        // check
        System.out.println("after : ");
        printArray(people);
        System.out.println("\n");

        /*
         * sample
         */
        // setup
        final Person[] people2 = new Person[]{b, n, a};
        System.out.println("before : ");
        printArray(people2);
        System.out.println("\n");

        // action
        Arrays.sort(people2, Comparator.comparing(Person::getName, Comparator.nullsFirst(Comparator.<String>naturalOrder()).reversed()));

        // check
        System.out.println("after : ");
        printArray(people2);
    }

    private static <T extends Person> void printArray(final T[] array) {
        Stream.of(array).forEachOrdered(p -> System.out.print(p.getName() + ","));
    }

    private static class Person {
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
