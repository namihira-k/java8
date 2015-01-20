/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * みなさんのコンピュータでは、Arrays.parallelSortは、
 * 配列がどのくらいの大きさであればArrays.sortより速くなりますか。
 */
/**
 * A.
 * - 配列がどのくらいの大きさであればArrays.sortより速くなりますか。
 * 65536(2^16)あたりでArrays.sortより速くなる
 *
 * 測定結果（ログ）
 *　size  Arrays.parallelSort / Arrays.sort [msec]    ratio
 *　2       1 / 1       1.0
 *　4       1 / 0       1000000.0
 *　8       0 / 0       0.0
 *　16      0 / 0       0.0
 *　32      1 / 0       1000000.0
 *　64      1 / 0       1000000.0
 *　128     1 / 1       1.0
 *　256     1 / 4       0.25
 *　512     3 / 4       0.75
 *　1024        4 / 5       0.8
 *　2048        8 / 6       1.3333333333333333
 *　4096        22 / 11     2.0　
 *　8192        39 / 15     2.6
 *　16384       316 / 54        5.851851851851852
 *　32768       236 / 211       1.1184834123222749
 *　65536       326 / 458       0.7117903930131004
 *　131072      327 / 202       1.618811881188119
 *　262144      233 / 261       0.89272030651341
 *　524288      384 / 629       0.6104928457869634
 *　1048576     1091 / 1921     0.5679333680374805　
 *　2097152     2755 / 3696     0.7454004329004329
 *　4194304     3895 / 12402        0.31406224802451216
 *　8388608     3663 / 27166        0.1348376647279688　
 *　16777216        13996 / 60726       0.23047788426703553
 *
 * 動作環境：
 *  - OS : windows 8.1
 *  - CPU : Intel(R) Core(TM) i5-3317U CPU @ 1.70GHz 1.70 GHz
 *  - 実装メモリ（RAM） : 4.00 GB
 *  - 64ビット オペレーション システム、x64 ベース プロセッサ
 */

package jp.co.namihira.java8;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("size" + "\t" + "Arrays.parallelSort / Arrays.sort [msec]" + "\t" + "ratio");

        for (int i = 2; i < Integer.MAX_VALUE / 64; i = i << 1) {
            // prepare
            final Double[] data1 = Stream.generate(Math::random).limit(i).toArray(Double[]::new);
            final Double[] data2 = data1.clone();

            // action
            Duration result1 = measureRunTime(() -> Arrays.parallelSort(data1));
            Duration result2 = measureRunTime(() -> Arrays.sort(data2));

            // check
            System.out.println(i + "\t\t"
                    + result1.toMillis() + " / " + result2.toMillis() + "\t\t"
                    + ((double)result1.toNanos() / (result2.toNanos() == 0 ? 1.0 : (double)result2.toNanos())));
       }
    }

    private static Duration measureRunTime(final Runnable function){
        final Instant start = Instant.now();
        function.run();
        final Instant end = Instant.now();
        return Duration.between(start, end);
    }

}
