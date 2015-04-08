/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * 複数例外をキャッチするcatch節でキャッチした例外を再度スローする場合に、
 * その処理が書かれているメソッドのthrows宣言には、例外の型をどのように宣言しますか。
 * たとえば、次の例を考えなさい。
 *
 * public void process() throws ... {
 *      try {
 *         ...
 *      } catch (FileNotFoundException |
 *               UnknownHostException ex) {
 *        logger.log(Level.SEVERE, "...", ex);
 *        throw ex;
 *      }
 * }
 */
/*
 * A.
 * 共通の親の例外クラスを宣言する・・・呼び出し元との互換性を考えるとこちらが推奨。
 *  public void process() throws IOException
 *  public void process() throws Exception
 *
 * 各例外クラスをそれぞれ宣言する
 *  public void process_multi() throws FileNotFoundException, UnknownHostException
 */

package jp.co.namihira.java8;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.toString());

    public static void main(String[] args){
        // setup
        // - nothing

        // action
        // - nothing

        // check
        // - nothing
    }

    public void process() throws IOException {
        try {
            final String dummy = "";
            switch (dummy) {
                case "":
                    throw new FileNotFoundException();
                default:
                    throw new UnknownHostException();
            }
        } catch (FileNotFoundException | UnknownHostException ex) {
            logger.log(Level.SEVERE, "...", ex);
            throw ex;
        }
    }

    public void process_multi() throws FileNotFoundException, UnknownHostException  {
        try {
            final String dummy = "";
            switch (dummy) {
                case "":
                    throw new FileNotFoundException();
                default:
                    throw new UnknownHostException();
            }
        } catch (FileNotFoundException | UnknownHostException ex) {
            logger.log(Level.SEVERE, "...", ex);
            throw ex;
        }
    }

}
