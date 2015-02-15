/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * jjsを実行しなさい。そして、次の呼び出しを行いなさい。
 *  var b = new java.math.BigInteger('1234567890987654321')
 * それから、bを表示しなさい。（単に b と入力してEnterを推します）。 何が得られましたか。
 * b.mod(java.math.BigInteger.TEN)の値は何ですか。
 * bはなぜ、奇妙に表示されるのですか？bの実際の値をどうやって表示できますか？
 */
/*
 * A.
 * - 何が得られましたか。
 * jjs> var b = new java.math.BigInteger('1234567890987654321')
 * jjs> b
 * 1234567890987654400
 *
 * - b.mod(java.math.BigInteger.TEN)の値は何ですか。
 * jjs> b.mod(java.math.BigInteger.TEN)
 * 1
 *
 * - bはなぜ、奇妙に表示されるのですか？
 * Nashorn(JavaScript)は整数をJavaでのdoubleとして扱う。
 * 上記の場合の「1234567890987654321」は「1.2345678909876544E18」として扱われる。
 * その値を（整数として）表示する場合に桁落ちが発生し、
 * 「12345678909876544"00"」として表示される。
 *
 * - bの実際の値をどうやって表示できますか？
 * jjs> java.lang.String.format('%.20s', b)
 * 1234567890987654321
 *
 * [参考]https://bugs.openjdk.java.net/browse/JDK-8010732
 *
 */

package jp.co.namihira.java8;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
       // prepare
        // - nothing

        // action
        // - nothing

        // check
       final BigInteger bi = new BigInteger("1234567890987654321");
       System.out.println(bi);
       System.out.println(bi.doubleValue());
       // - log
       // 1234567890987654321
       // 1.2345678909876544E18
    }

}
