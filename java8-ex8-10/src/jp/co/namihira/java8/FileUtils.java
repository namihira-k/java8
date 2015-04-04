/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtils {

    /**
     * 指定された ZIP ファイルを、指定されたパスに、ファイル名のディレクトリを作成して解凍します。
     *
     * @param zipFileFullPath Zipファイルのフルパス
     * @param distPath 解凍するパス
     *
     * @throws NullPointerException 引数がnullの場合
     * @throws UncheckedIOException ファイル操作でエラーが発生した場合
     */
    public static void unzip(final Path zipFileAbsolutePath, final Path distPath) {
        Objects.requireNonNull(zipFileAbsolutePath);
        Objects.requireNonNull(distPath);

        try(final ZipFile zipFile = new ZipFile(zipFileAbsolutePath.toFile())) {
            final Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = (ZipEntry) entries.nextElement();

                final String name = zipEntry.getName();

                final Path file = Paths.get(distPath.toString(), name);
                if (name.endsWith("/")) {
                    Files.createDirectory(file);
                    continue;
                }

                final Path parent = file.getParent();
                if (parent != null) {
                    Files.createDirectories(parent);
                }

                try (final InputStream is = zipFile.getInputStream(zipEntry);
                     final FileOutputStream fos = new FileOutputStream(file.toFile())){
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = is.read(bytes)) >= 0) {
                        fos.write(bytes, 0, length);
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
                System.out.print(".");
            }
            System.out.println("unzipped");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
