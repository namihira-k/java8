/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * jjsを実行し、ストリームライブラリを使用して、次の問題に対する解法をインタラクティブに求めなさい。
 * 問題：
 *  あるファイルに含まれている長い単語（12文字より長い）を重複なしですべてソートして表示しなさい。
 *  最初に単語を読み込んで、長い単語を選択してという具合に行いなさい。
 * このインタラクティブな取り組み方は、通常のワークフローと比較してどうですか？
 */
/**
 * A.
 * jjs> var path = java.nio.file.Paths.get('C:\\Users\\Kosuke\\workspace\\java8-ex7-02\\src\\jp\\co\\namihira\\java8\\TestData.txt')
 * jjs> var words = java.nio.file.Files.readAllLines(path)
 * jjs> words.stream().filter(function(x) x.length() > 12).sorted().forEachOrdered(function(x) print(x))
 * 12345678901234
 * 1234567890123456
 * aaaaaaaaaaaaaa
 * ddddddddddddd
 * null
 *
 * - このインタラクティブな取り組み方は、通常のワークフローと比較してどうですか？
 *  良い点：
 *      コンパイルなしで素早く実行できる。
 *      各処理（行）ごとに実行・結果確認ができ、ミスに早く気づける。
 *  悪い点：
 *      各変数にどのような値が現在格納されているかを意識する必要がある。（<->通常のワークフローならば一気通貫で実行され一貫性がある）
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
