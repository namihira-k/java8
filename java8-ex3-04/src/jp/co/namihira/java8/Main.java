/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * Filterという名前を持つ関数型インタフェースが、Java API にはいくつありますか。
 * そのうちのどれが、Predicate<T>よりも付加価値がありますか。
 */
/**
 * A.
 * - Filterという名前を持つ関数型インタフェースが、Java API にはいくつありますか。
 * 以下の4つ。
 * [関数型インタフェース]
 * java.util.logging.Filter　-> Predicate<T>と同等
 * java.nio.file.DirectoryStream$Filter -> 例外処理（IOException）追加できる
 * javax.imageio.spi.ServiceRegistry.Filter -> Predicate<T>と同等
 * com.sun.org.apache.xalan.internal.xsltc.dom.Filter　-> Predicate<T>と同等
 *
 * [関数型インタフェースではない]
 * javax.xml.crypto.dsig.spec.XPathType.Filter
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