/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class WordFileMapping {

    private final ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<String, Set<File>>();

    /**
     * 指定されたファイルについて、単語とファイルのマッピング情報に追加します。
     *
     * @param file 追加するファイル
     *
     * @throws NullPointerException 引数がnullの場合
     */
    public void add(final File file){
        Objects.nonNull(file);

        final Set<String> words = getWords(file);
        words.forEach(word -> {
            map.computeIfAbsent(word, (w) -> new TreeSet<>()).addAll(this.set(file));
        });
    }

    /**
     * 指定された単語に紐づくファイル一覧を返します。
     *
     * @param word　単語
     * @return　ファイル一覧
     */
    public Set<File> get(final String word){
        return map.get(word);
    }

    /**
     * 単語とファイルのマッピング情報を返します。
     *
     * @return　マッピング情報
     */
    public Set<Entry<String, Set<File>>> entrySet(){
        return map.entrySet();
    }

    /**
     * マッピング情報をクリアします。
     */
    public void clear(){
        map.clear();
    }

    private Set<File> set(final File file){
        final SortedSet<File> set = new TreeSet<>();
        set.add(file);
        return set;
    }

    private Set<String> getWords(final File file){
        final Set<String> result = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = br.readLine();
            while(str != null){
                result.add(str.trim());
                str = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
