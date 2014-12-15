/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.time.LocalDateTime;

/**
 * 時刻のインターバルを示すクラス
 */
public class TimeInterval {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public TimeInterval(final LocalDateTime from, final LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    /**
     * 指定されたインターバルと重なっているか
     *
     * @param other　インターバル
     * @return　重なっている場合true、重なっていない場合false
     *
     * @throws IllegalArgumentException 引数がnullの場合。
     */
    public boolean isOverlaped(final TimeInterval other) {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }

        return from.isBefore(other.getTo()) && to.isAfter(other.getFrom());
    }

    public LocalDateTime getFrom(){
        return from;
    }

    public LocalDateTime getTo(){
        return to;
    }

}

