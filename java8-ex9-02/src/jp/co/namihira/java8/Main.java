/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 練習問題1を改善して、in.close()やout.close()によりスローされる例外を
 * 抑制された例外として、元々の例外へ追加するようにしなさい。
 */
/*
 * A.
 * - before(ex9-01)
 * *** Suppressed ***
 * *** StackTrace ***
 * java.io.UncheckedIOException: java.io.IOException: failed close
 *  at jp.co.namihira.java8.Main.throwException(Main.java:82)
 *  at jp.co.namihira.java8.Main.main(Main.java:39)
 * Caused by: java.io.IOException: failed close
 *  at jp.co.namihira.java8.Main.close(Main.java:90)
 *  at jp.co.namihira.java8.Main.throwException(Main.java:80)
 *  ... 1 more
 *
 * - after(ex9-02)
 * *** Suppressed ***
 * java.io.IOException: failed close
 *  at jp.co.namihira.java8.Main.close(Main.java:99)
 *  at jp.co.namihira.java8.Main.throwException(Main.java:80)
 *  at jp.co.namihira.java8.Main.main(Main.java:41)
 * *** StackTrace ***
 * java.lang.Exception: failed hasNext
 *  at jp.co.namihira.java8.Main.throwException(Main.java:74)
 *  at jp.co.namihira.java8.Main.main(Main.java:41)
 * Suppressed: java.io.IOException: failed close
 *      at jp.co.namihira.java8.Main.close(Main.java:99)
 *      at jp.co.namihira.java8.Main.throwException(Main.java:80)
 *      ... 1 more
 */

package jp.co.namihira.java8;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args){
        try {
            throwException();
        } catch (Exception e){
            System.out.println("*** Suppressed ***");
            Stream.of(e.getSuppressed()).forEachOrdered(ex -> ex.printStackTrace());

            System.out.println("*** StackTrace ***");
            e.printStackTrace();
        }
    }

    private static void throwException() throws Exception{
        final String classPath = getClassPath();

        Scanner in = null;
        try {
            in = new Scanner(Paths.get(classPath + "/in.txt"));
        } catch (IOException e) {
            throw e;
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(classPath + "/out.txt");
        } catch (FileNotFoundException e) {
            try {
                close(in);
            } catch (IOException ioe) {
                e.addSuppressed(ioe);
            }
            throw e;
        }

        try {
            // FIXME
            throw new Exception("failed hasNext");
//            while (in.hasNext()) {
//                out.println(in.next().toLowerCase());
//            }
        } catch (Exception e) {
            try {
                close(in, out);
            } catch (IOException ioe) {
                e.addSuppressed(ioe);
            }
            throw e;
        }

        // FIXME not reach when throw Exception
//        try {
//            close(in, out);
//        } catch (IOException e) {
//            throw e;
//        }
    }

    private static void close(final Closeable first, final Closeable second) throws IOException{
        try {
            // FIXME
            throw new IOException("failed close");
//            close(first);
        } finally {
            close(second);
        }
    }

    private static void close(final Closeable resource) throws IOException{
        if (resource == null) {
            return;
        }
        resource.close();
    }

    private static String getClassPath() {
        // FIXME /C:/Users/Kosuke/workspace/java8-ex9-01/bin/
        final String classPath = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath();
        return classPath.replaceFirst("/", "");
    }

    private void sample(){
        try ( Scanner in = new Scanner(Paths.get("/usr/share/dist/words"));
              PrintWriter out = new PrintWriter("/tmp/out.txt")) {
            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }
        } catch (IOException e) {
        }
    }

}
