/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 次のメソッドを作成しなさい。
 *   public static <T> CompletableFuture<T> repeat(
 *        Supplier<T> action, Predicate<T> until)
 *
 * このメソッドは、until関数が受け入れる値を生成するまで、actionを非同期に繰り返します。
 * until関数も非同期に実行されるべきです。
 * コンソールからjava.net.PasswordAuthenticationを読み込む関数、
 * および、1秒間スリープすることで正当性検査をシミュレートし、
 * パスワードが"secret"であるかを検査する関数を用いてテストしなさい。ヒント：再帰を使用します。
 */

package jp.co.namihira.java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    private static PasswordAuthentication user = new PasswordAuthentication("", "secret".toCharArray());

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        CommonUtils.repeat(
                () -> {
                    return getPasswordAuthFromConsole();
                },
                (auth) -> {
                    try {
                        Thread.sleep(1_000);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    final boolean result = Arrays.equals(auth.getPassword(), user.getPassword());
                    System.out.println(result ? "OK!" : "NG!");
                    return result;
                }
        );

        // check
        // - nothing

        ForkJoinPool.commonPool().awaitQuiescence(10,  TimeUnit.SECONDS);
    }

    private static PasswordAuthentication getPasswordAuthFromConsole() {
        return new PasswordAuthentication("", getPasswordFromConsole().toCharArray());
    }

    private static String getPasswordFromConsole() {
         try {
            System.out.print("Input password > ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
    }

}
