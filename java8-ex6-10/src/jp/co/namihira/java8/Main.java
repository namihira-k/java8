/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * ユーザにURLを問い合わせて、そのURLのウェブページを読み込み、すべてのリンクを表示するプログラムを作成しなさい。
 * 各ステップに対して、CompletableFutureを使用しなさい。
 * getを呼び出さないこと。プログラムの処理が終わる前に終了しないようにするために、次の呼び出しを行いなさい。
 *
 *　ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
 *
 */
/**
 * A.
 * サイト
 *      http://horstmann.com
 * リンク一覧（49個）：
 *      ["mailto:cay@horstmann.com", "caypubkey.txt", "http://www.uni-kiel.de/",
 *      "http://www.kiel.de/", "http://www.syr.edu", "http://www.umich.edu",
 *      "http://www.mathcs.sjsu.edu", "http://www.java.net/blogs/cayhorstmann/",
 *      "https://plus.google.com/117406678785944293188/posts",
 *      "http://www.sjsu.edu/people/cay.horstmann", "quotes.html", "resume.html",
 *      "http://www.family-horstmann.net", "java8/index.html", "scala/index.html",
 *      "python4everyone.html", "bigjava.html", "bigjava.html", "bjlo.html",
 *      "bjlo.html", "http://www.wiley.com/college/sc/horstmann/", "bigcpp.html",
 *      "cpp4everyone/index.html", "corejava.html", "http://corejsf.com",
 *      "design_and_patterns.html", "PracticalOO.html", "mood.html", "mcpp.html",
 *      "https://java-champions.dev.java.net/", "violet/index.html",
 *      "articles/Taming_the_GridBagLayout.html", "articles/BetterCleaner.html",
 *      "articles/ImportCleaner.html", "safestl.html", "http://www.stlport.org",
 *      "http://webhostingrating.com/libs/safestl-be", "cpp/pitfalls.html",
 *      "http://web.archive.org/web/20070714161114/http://www.3miasto.net/~chq/c/pitfalls.txt",
 *      "cpp/iostreams.html", "http://code.google.com/p/twisty/", "http://frotz.sourceforge.net",
 *      "http://www.csd.uwo.ca/Infocom/", "applets/RoadApplet/RoadApplet.html",
 *      "applets/Retire/Retire.html", "corejava.html", "applets/WeatherApplet/WeatherApplet.html",
 *      "corejava.html", "applets/Intersection/Intersection.html"]
 *
 */

package jp.co.namihira.java8;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // prepare
        final String url = "http://horstmann.com";

        // action
        final CompletableFuture<List<String>> links =
                CompletableFuture.supplyAsync(() -> URLUtils.readPage(url))
                                 .thenApply(Parser::getLinks);

        // check
        links.thenAccept(System.out::println);
        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }

}
