/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * Objects.requireNonNullメソッドの使用方法を示し、
 * そのメソッドをより、もっと役立つエラーメッセージとなる方法を示しなさい。
 */
/*
 * A.
 * - log
 * java.lang.NullPointerException:     [2015-04-05T12:49:56.251Z]
 *  at java.util.Objects.requireNonNull(Objects.java:290)
 *  at jp.co.namihira.java8.Main.requireNonNullWrapper(Main.java:79)
 *  at jp.co.namihira.java8.Main.length(Main.java:61)
 *  at jp.co.namihira.java8.Main.main(Main.java:38)
 * java.lang.NullPointerException: s1 must be not null [2015-04-05T12:49:56.345Z]
 *  at java.util.Objects.requireNonNull(Objects.java:290)
 *  at jp.co.namihira.java8.Main.requireNonNullWrapper(Main.java:79)
 *  at jp.co.namihira.java8.Main.addLength(Main.java:66)
 *  at jp.co.namihira.java8.Main.main(Main.java:44)
 * java.lang.NullPointerException: s must be not null  [2015-04-05T12:49:56.345Z]
 *  at java.util.Objects.requireNonNull(Objects.java:290)
 *  at jp.co.namihira.java8.Main.requireNonNullWrapper(Main.java:79)
 *  at jp.co.namihira.java8.Main.delete(Main.java:72)
 *  at jp.co.namihira.java8.Main.main(Main.java:50)
 */

package jp.co.namihira.java8;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args){
        // setup
        // - nothing

        // action
        try {
            length(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            addLength(null, "");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            delete(null, 'a');
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        // check
        // - nothing
    }

    private static int length(final String s){
        requireNonNullWrapper(s, null);
        return s.length();
    }

    private static int addLength(final String s1, final String s2){
        requireNonNullWrapper(s1, () -> "s1 must be not null");
        requireNonNullWrapper(s2, () -> "s2 must be not null");
        return s1.length() + s2.length();
    }

    private static String delete(final String s, final char c){
        String tmp = requireNonNullWrapper(s, () -> "s must be not null");
        return tmp.replaceAll(String.valueOf(c), "");
    }

    private static <T> T requireNonNullWrapper(final T obj,
            final Supplier<String> messageSupplier) {
        final String msg = (messageSupplier == null ? "" : messageSupplier.get());
        return Objects.requireNonNull(obj, () -> msg + "\t[" + Instant.now() + "]");
    }

}