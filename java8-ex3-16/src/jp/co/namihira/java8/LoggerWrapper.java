/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerWrapper {

    private Logger logger;

    private String lastMessage = "";

    public LoggerWrapper(final Logger logger) {
        this.logger = logger;
    }

    /**
     * 指定したmsgSupplierの表現でログ出力します。
     *
     * @exception IllegalArgumentException いずれからの引数がnullの場合。
     *
     * @param level 出力したいログレベル
     * @param msg　出力メッセージ
     */
    public <T> void log(final Level level, final String msg) {
        if (level == null) {
            throw new IllegalArgumentException("level must not be null");
        }

        if (msg == null) {
            throw new IllegalArgumentException("msg must not be null");
        }

        if (!logger.isLoggable(level)) {
            return;
        }

        logger.log(level, msg);
        lastMessage = msg;
    }

    /**
     * 最後に出力したメッセージを取得します。
     * @return 最後に出力したメッセージ
     */
    public String getLastMessage(){
        return lastMessage;
    }

}