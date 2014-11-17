/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * 画像に関するユーティリティクラス
 */
public class ImageUtils {

    /**
     * 各Color要素について、色変換処理を並列実行します。
     *
     * @param in 画像を示すColor配列
     * @param f　色変換処理
     * @return　変換後のColor配列
     */
    public static Color[][] parallelTransform(Color[][] in, UnaryOperator<Color> f) {
        int n = Runtime.getRuntime().availableProcessors();
        int height = in.length;
        int width = in[0].length;
        Color[][] out = new Color[height][width];
        try {
           ExecutorService pool = Executors.newCachedThreadPool();
           for (int i = 0; i < n; i++) {
              int fromY = i * height / n;
              int toY = (i + 1) * height / n;
              pool.submit(() -> {
                    System.out.printf("%s %d...%d\n", Thread.currentThread(), fromY, toY - 1);
                    for (int x = 0; x < width; x++)
                       for (int y = fromY; y < toY; y++)
                          out[y][x] = f.apply(in[y][x]);
                 });
           }
           pool.shutdown();
           pool.awaitTermination(1, TimeUnit.HOURS);
        }
        catch (InterruptedException ex) {
           ex.printStackTrace();
        }
        return out;
     }

    /**
     * 指定されたImegeオブジェクトについて、Color配列に変換します。
     * @param in Imageオブジェクト
     * @return Color配列
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     *
     */
    public static Color[][] toArrays(final Image in) {
        if (in == null) {
            throw new IllegalArgumentException("in must not be null");
        }

        final int WIDTH = (int) in.getWidth();
        final int HEIGHT = (int) in.getHeight();
        Color[][] out = new Color[HEIGHT][WIDTH];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                out[y][x] = in.getPixelReader().getColor(x, y);
            }
        }
        return out;
    }

    /**
     * 指定されたColor配列を、Imageオブジェクトに変換します。
     *
     * @param colors　Color配列
     * @return Imageオブジェクト
     *
     * @throws IllegalArgumentException 引数がnullの場合。Color配列にnullが存在する場合。
     */
    public static Image toImage(final Color[][] colors) {
        if (colors == null) {
            throw new IllegalArgumentException("colors must not be null");
        }

        final int WIDTH = (int) colors[0].length;
        final int HEIGHT = (int) colors.length;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (colors[y][x] == null) {
                    throw new IllegalArgumentException("colors must not contain null");
                }
            }
        }

        WritableImage out = new WritableImage(WIDTH, HEIGHT);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                out.getPixelWriter().setColor(x, y, colors[y][x]);
            }
        }
        return out;
    }


}