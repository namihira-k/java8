/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * パスワード保護されたウェブページの内容を取得するプログラムを書きなさい。
 * URLConnection connection = url.openConnection();
 * を呼び出しなさい。
 * 文字列 usernale:password を生成して、それをBase64でエンコードしなさい。
 * それから、connection.setRequestProperty("Authorization", "Basic " + encoded string)
 * を呼び出し、次にconnection.connect()とconnection.getInputStream()を呼び出しなさい。
 *
 */
/* A.
 * - 参考 : http://x68000.q-e-d.net/~68user/net/http-auth-1.html
 */

package jp.co.namihira.java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        final URL url = new URL("http://X68000.q-e-d.net/~68user/net/sample/http-auth/secret.html");
        final String username = "hoge";
        final String password = "fuga";

        URLConnection connection;
        try {
            connection = url.openConnection();
            setAuthProperty(connection, username, password);
            connection.connect();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            reader.lines().forEachOrdered(System.out::println);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void setAuthProperty(final URLConnection connection, final String username, final String password){
        final Base64.Encoder encoder = Base64.getEncoder();
        final String original = username + ":" + password;
        final String encoded = encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));
        connection.setRequestProperty("Authorization", "Basic " + encoded);
    }

}