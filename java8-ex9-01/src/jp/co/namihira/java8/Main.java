/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 212ページの9.1.1節「try-with-resouces文」の最後にあるScannerとPrinterを生成しているコードを、
 * try-with-resources文を使用しないで実装しなさい。
 * 両方のオブジェクトが適切に生成された場合には、両方のオブジェクトをきちんとクローズしなさい。
 * 次の事項を考慮すること。
   * 　・Scannerのコンストラクタは、例外をスローする。
 *　・PrintWriterのコンストラクタは、例外をスローする。
 *　・hasNext、next、printlnメソッドは、例外をスローする。
 *　・in.close()は、例外をスローする。
 *　・out.close()は、例外をスローする。
 */

package jp.co.namihira.java8;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        try {
            excute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void excute() throws Exception{
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
            close(in);
            throw e;
        }

        try {
            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close(in, out);
        }
    }

    private static void close(final Closeable first, final Closeable second) throws IOException{
        try {
            close(first);
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
