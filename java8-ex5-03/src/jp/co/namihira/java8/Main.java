/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Predicate<LocalDate>を受け取り、TemporalAdjusterを返すnextメソッドを
 * 実装しなさい。返されたTemporalAdjusterは、nextメソッドに渡された述語を
 * 満足する翌日の日付を生成します。
 * 例えば、次のコードを見てください。
 *
 *      today.with(next(w -> getDayOfWeek().getValue() < 6))
 *
 * このコードは、今日よりも後にあって平日となる最初の日付を返します。
 */

package jp.co.namihira.java8;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        // prepare
        // 2014-12-13(Sat)
        final LocalDate input = LocalDate.of(2014, Month.DECEMBER, 13);

        // action
        final LocalDate result = input.with(DateUtils.next(w -> w.getDayOfWeek().getValue() < 6));

        // check
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd E");
        System.out.println("Input : " + input.format(formatter));
        System.out.println("Except : " + input.plusDays(2).format(formatter));
        System.out.println("Result : " + result.format(formatter));
    }
}