/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 条件的なロギングを提供することで、遅延ロギング技法を強化しなさい。
 * 典型的な呼び出しは、logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10])
 * となります。
 * ロガーがメッセージをロギングしないのであれば、その条件を評価しないようにしなさい。
 */
/**
 * A.
 *  [ログ出力]
 * log
 * called filter
 * called filter
 *　called filter
 * called filter
 * called filter
 * called filter
 * called filter
 * called filter
 * called filter
 * called filter
 * called filter
 * called msg
 * called msg (※lastMessage = msgSupplier.get()のため出力される。)
 * 情報: a[10] = 10 [土 11 01 17:02:15 JST 2014]
 * no log
 */

package jp.co.namihira.java8;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    static {
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        // prepare
        final int SIZE = 11;
        Integer[] a = Stream.iterate(0, n -> n + 1).limit(SIZE).toArray(Integer[]::new);
        LoggerWrapper loggerWrapper = new LoggerWrapper(logger);

        /*** ログ出力される場合  ***/
        System.out.println("log");
        // action
        for (int i = 0; i < a.length; i++) {
            final int INDEX = i;
            loggerWrapper.logIf(Level.INFO,
                    () -> {
                        System.out.println("called filter");
                        return INDEX == 10;
                    },
                    () -> {
                        System.out.println("called msg");
                        return "a[10] = " + a[10];
                    }
                    );
        }

        // check
        // - check standard output

        /*** ログ出力されない場合  ***/
        System.out.println("no log");
        // action
        for (int i = 0; i < a.length; i++) {
            final int INDEX = i;
            loggerWrapper.logIf(Level.FINEST,
                    () -> {
                        System.out.println("called filter");
                        return INDEX == 10;
                    },
                    () -> {
                        System.out.println("called msg");
                        return "a[10] = " + a[10];
                    }
                    );
        }

        // check
        // - check no standard output
    }

}