/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * インターフェースにメソッドを追加することは既存のコードを動作させなくするので、過去には、悪いことであると言われていました。
 * 今では、デフォルト実装も提供するのであれば、新たなメソッドを追加することは問題ありません。そのような追加は、どれだけ安全なのでしょうか。
 * Collectionインタフェースの新たなstreamメソッドが古いコードのコンパイルを失敗させるシナリオを述べなさい。バイナリ互換性についてはどうでしょうか？
 * JARファイルからの古いコードは、動作するでしょうか？
 */
/**
 * A.
 * Collectionインタフェースの新たなstreamメソッドが古いコードのコンパイルを失敗させるシナリオ：
 *  -> Collectionクラスを実装した既存クラスがstreamメソッドを実装していた場合、
 *　          戻りの型が 、Collection<E>.stream()と互換性（Stream<E>のサブクラス）/共変でないため、コンパイルエラーになる。
 *
 *　バイナリ互換性についてはどうでしょうか？JARファイルからの古いコードは、動作するでしょうか？:
 *　　-> Collenction関連については、バイナリ互換性が維持されている。動作する。
 *　　　　 ※Java8にて廃止されているAPIもあるので、それらの非互換部分については動作しない。
 *　参考：http://www.oracle.com/technetwork/jp/java/javase/overview/8-compatibility-guide-2156366-ja.html
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args) {
        // prepare
        // - nothing

        // action
        // - nothing

        // check
        // - nothing
    }

}