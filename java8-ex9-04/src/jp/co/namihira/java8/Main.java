/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * Javaライブラリを使用しているとき、複数例外をキャッチすることで恩恵を得られる状況に
 * 遭遇したライブラリには何がありますか？
 * あるいは、共通の例外となるスーパークラスにより恩恵を得られる状況に遭遇した
 * ライブラリには何がありますか？
 * （ヒント：XML解析）
 */
/*
 * A.
 * - 複数例外をキャッチすることで恩恵を得られる状況に遭遇したライブラリには何がありますか？
 * 特になし
 *
 * - 共通の例外となるスーパークラスにより恩恵を得られる状況に遭遇したライブラリには何がありますか？
 * SpringFramework での DataAccessException
 *
 * JavaからDBへのアクセスする際にはSQLExceptionが発生する可能性があるが、
 * SpringFrameworkでのDBの操作時のエラーはDataAccessExceptionでラッピングされる。
 * java.sql.SQLExceptionはチェックされる例外であるが、DataAccessExceptionは実行時エラー のため
 * try-catchが必要なくコードが簡潔になる。
 * ※DBアクセス時のエラーはほとんど復旧作業が難しい、後作業が無いためcatch節の出番がない。
 * 対応が必要な場合、そのDataAccessExceptionをハンドルするメソッドを1つ用意するだけで済むため、
 * 処理の局所化も実現できる。
 *
 *　参考：
 * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/dao/DataAccessException.html
 * http://docs.oracle.com/javase/jp/8/docs/api/java/sql/SQLException.html
 *
 */

package jp.co.namihira.java8;

public class Main {

    public static void main(String[] args){
        // setup
        // - nothing

        // action
        // - nothing

        // check
        // - nothing
    }

}
