/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * テキストフィールドとラベルを持つプログラムを作成しなさい。
 * 「Hello,JavaFX」プログラムと同じように、そのラベルは、
 * 文字列Hello, FXを100ポイントのフォントで初期化しなさい。
 * テキストフィールドを同じ文字列で初期化しなさい。
 * ユーザーがテキストフィールドを編集したらラベルも更新するようにしなさい。
 */

package jp.co.namihira.java8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private final VBox box = new VBox();
    private final Label label = new Label();
    private final TextField text = new TextField();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        buildScreen(stage);
        registerEventHandlers(stage);
    }

    private void buildScreen(Stage stage){
        final String title = "Hello";
        final String initMessage = "Hello, FX";

        label.setText(initMessage);
        label.setFont(new Font(100));

        text.setText(initMessage);

        box.getChildren().addAll(label, text);
        box.setPadding(new Insets(10));

        stage.setScene(new Scene(box));
        stage.setTitle(title);
        stage.show();
    }

    private void registerEventHandlers(Stage stage){
        text.textProperty().addListener(
                (property) -> {
                    label.setText(text.getText());
                    box.autosize();
                    stage.setWidth(box.getWidth() + 50);
                }
        );
    }

}