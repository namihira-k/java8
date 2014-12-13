/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * LocalDate.of(2000, 2, 29)に1年を加算すると何が起きますか。
 * 4年を加算するとどうですか。さらに、1年を4回加算するとどうなりますか？
 */
/**
 * A.
 * LocalDate.of(2000, 2, 29)に1年を加算すると何が起きますか。
 * - 2001-02-28となる。2月末日に丸められる。
 *
 * 4年を加算するとどうですか。さらに、1年を4回加算するとどうなりますか？
 * - 2004-02-28となる。
 *   2004年はうるう年であり、2004-02-29が存在する。
 *   しかし、年加算の過程で一度丸められるとその日付に対して1年が足されるため、2004-02-28となる。
 */

package jp.co.namihira.java8;

import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
        // prepare
        final LocalDate date = LocalDate.of(2000, 2, 29);

        // action
        final LocalDate result1 = date.plusYears(1);
        final LocalDate result4 = date.plusYears(1).plusYears(1).plusYears(1).plusYears(1);

        // check
        System.out.println("added 1 year : " + result1);
        System.out.println("added 1 year * 4 : " + result4);
        System.out.println("2004 is leap year : " + LocalDate.of(2004, 2, 29));
    }

}