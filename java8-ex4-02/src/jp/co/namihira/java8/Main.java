/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * チャートやテーブルといった多くのJavaFXプロパティを持つクラスを考えなさい。
 * 特定のアプリケーションでは、ほとんどのプロパティには決してリスナーが
 * 登録されない可能性が高いです。
 * したがって、プロパティごとにプロパティオブジェクトを持つことは無駄です。
 * プロパティ値を保存するため最初に普通のフィールドを使用して、
 * 初めてxxxProperty()メソッドが呼び出されたときにだけプロパティオブジェクトを
 * 使用するように、要求に応じてプロパティを構築する方法に示しなさい。
 */

package jp.co.namihira.java8;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // prepare
        // - nothing

        // action
        // - nothing

        // check
        // - nothing
    }

}