/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerWrapper {

    private Logger logger;

    private String lastMessage = "";

    public LoggerWrapper(final Logger logger) {
        this.logger = logger;
    }

    /**
     * 指定したfilterSupplierの条件に一致した場合、指定したmsgSupplierの表現でログ出力します。
     *
     * @exception IllegalArgumentException いずれからの引数がnullの場合。
     *
     * @param level 出力したいログレベル
     * @param filterSupplier　出力条件
     * @param msgSupplier　出力メッセージ
     */
    public <T> void logIf(final Level level, Supplier<Boolean> filterSupplier, Supplier<String> msgSupplier) {
        if (level == null) {
            throw new IllegalArgumentException("level must not be null");
        }

        if (filterSupplier == null) {
            throw new IllegalArgumentException("filterSupplier must not be null");
        }

        if (msgSupplier == null) {
            throw new IllegalArgumentException("msgSupplier must not be null");
        }

        if (!logger.isLoggable(level)) {
            return;
        }

        if (filterSupplier.get() == false) {
            return;
        }

        logger.log(level, msgSupplier);
        lastMessage = msgSupplier.get();
    }

    /**
     * 最後に出力したメッセージを取得します。
     * @return 最後に出力したメッセージ
     */
    public String getLastMessage(){
        return lastMessage;
    }

}