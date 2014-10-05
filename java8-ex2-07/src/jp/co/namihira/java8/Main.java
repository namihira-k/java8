/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * みなさんの上司が、メソッドとして
 * public static <T> boolean isFinite(Stream<T> stream)
 * を作成するように求めています。それは、良くない考えでしょうか？
 * まずは作成してから、考えてみなさい。
 */
/**
 * A.
 * 良くない考え。
 * [理由]
 *  1. Streamは終端処理から要求されてから要素への要求（遅延して）が行われるため、実行せずに有限か無限かの情報を取得することができない。
 *　 　　  実行した場合、無限Streamの場合無限ループに陥るため、どこからの上限を設定し有限と割り切る必要があるため、
 *　　　  無限のケース（falseを返す）が意味として正しくない。
 *
 *  2. 関数に渡させたstreamを使用して検査するので、呼び出し元はその渡したStreamを使うことができない。
 *　 　　  そのため、同じStreamを2つ作成するコストが発生する。
 */

package jp.co.namihira.java8;

import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        /*** 有限Stream ***/
        // prepare
        Stream<Double> stream = Stream.generate(Math::random).limit(StreamUtils.UPPER_LIMIT - 1);

        // action
        boolean result = StreamUtils.isFinite(stream);

        // check
        System.out.println("finite Stream...");
        System.out.println(" isFinite -> " + result);


        /*** 無限Stream ***/
        // prepare
        stream = Stream.generate(Math::random);

        // action
        result = StreamUtils.isFinite(stream);

        // check
        System.out.println("infinite Stream...");
        System.out.println(" isFinite -> " + result);

        /*** 再利用できない ***/
        // -> IllegalStateException 発生
        stream.count();
    }

}