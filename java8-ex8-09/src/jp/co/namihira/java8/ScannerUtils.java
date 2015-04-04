/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ScannerUtils {

    /**
     * Scannerを単語のストリームへ変換します
     *
     * @param scanner　スキャナ
     * @return　単語のストリーム
     *
     * @throws NullPointerException scannerがnullの場合
     */
    public static Stream<String> toStreamWord(final Scanner scanner) {
        Objects.requireNonNull(scanner);

        final Iterator<String> iter = new Iterator<String>() {
            final Scanner s = scanner;

            @Override
            public boolean hasNext() {
                return s.hasNext();
            }

            @Override
            public String next() {
                return s.next();
            }

        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    /**
     * Scannerを行のストリームへ変換します
     *
     * @param scanner　スキャナ
     * @return　行のストリーム
     *
     * @throws NullPointerException scannerがnullの場合
     */
    public static Stream<String> toStreamLine(final Scanner scanner) {
        Objects.requireNonNull(scanner);

        final Iterator<String> iter = new Iterator<String>() {
            final Scanner s = scanner;

            @Override
            public boolean hasNext() {
                return s.hasNextLine();
            }

            @Override
            public String next() {
                return s.nextLine();
            }

        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    /**
     * Scannerを整数のストリームへ変換します
     *
     * @param scanner　スキャナ
     * @return　整数のストリーム
     *
     * @throws NullPointerException scannerがnullの場合
     */
    public static Stream<Long> toStreamNumber(final Scanner scanner) {
        Objects.requireNonNull(scanner);

        final Iterator<Long> iter = new Iterator<Long>() {
            final Scanner s = scanner;

            @Override
            public boolean hasNext() {
                boolean hasNumber = false;

                while (s.hasNext()) {
                    hasNumber = s.hasNextLong();
                    if (hasNumber) {
                        return true;
                    }
                    s.next();
                }

                return false;
            }

            @Override
            public Long next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return s.nextLong();
            }

        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    /**
     * Scannerをdouble値のストリームへ変換します
     *
     * @param scanner　スキャナ
     * @return　double値のストリーム
     *
     * @throws NullPointerException scannerがnullの場合
     */
    public static Stream<Double> toStreamDouble(final Scanner scanner) {
        Objects.requireNonNull(scanner);

        final Iterator<Double> iter = new Iterator<Double>() {
            final Scanner s = scanner;

            @Override
            public boolean hasNext() {
                boolean hasNumber = false;

                while (s.hasNext()) {
                    hasNumber = s.hasNextDouble();
                    if (hasNumber) {
                        return true;
                    }
                    s.next();
                }

                return false;
            }

            @Override
            public Double next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return s.nextDouble();
            }

        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

}
