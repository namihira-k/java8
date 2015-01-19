/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapWrapper {

    private final ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>();

    /**
     * 最大値をもつエントリーを返します。
     * @return　最大値
     */
    public Entry<String, Long> getEntryWithMaxValue(){
        return map.reduceEntries(1, (first, second) -> first.getValue() > second.getValue() ? first : second);
    }

    /**
     * マップに値をセットします。
     *
     * @param key キー
     * @param value　値
     * @return 値
     *
     * @throws NullPointerException keyがnullの場合
     */
    public Long put(final String key, final Long value){
        Objects.nonNull(key);
        return map.put(key, value);
    }

    /**
     * マップのエントリーセットを返します。
     *
     * @return　マップのエントリーセット
     */
    public Set<Entry<String, Long>> entrySet(){
        return map.entrySet();
    }

    /**
     * マップの値をクリアします。
     */
    public void clear(){
        map.clear();
    }

}
