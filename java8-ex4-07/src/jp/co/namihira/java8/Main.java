/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * CSSを使用しないで、コントロールの境界を設定する方法を調べなさい。
 */
/**
 * A.
 * - Java記法で設定する。
 * - Java+CSS記法で設定する。
 * - FXML記法で設定する。
 */

package jp.co.namihira.java8;import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        HBox box = new HBox();
        box.getChildren().addAll(getPaneCss(), getPaneJava(), getPaneJavaCSS(), getPaneFXML());
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * CSSで設定する。
     */
    private Pane getPaneCss() {
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(true);
        pane.setId("pane");

        Label usernameLabel = new Label("User name:");
        Label passwordLabel = new Label("Password:");
        TextField username = new TextField();
        PasswordField password = new PasswordField();

        Button okButton = new Button("Ok");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getStyleClass().add("buttonrow");

        buttons.getChildren().addAll(okButton, cancelButton);

        pane.add(usernameLabel, 0, 0);
        pane.add(username, 1, 0);
        pane.add(passwordLabel, 0, 1);
        pane.add(password, 1, 1);
        pane.add(buttons, 0, 2, 2, 1);

        GridPane.setHalignment(usernameLabel, HPos.RIGHT);
        GridPane.setHalignment(passwordLabel, HPos.RIGHT);
        pane.getStylesheets().add("scene.css");

        return pane;
    }

    /**
     * Java記法のみで設定
     */
    private Pane getPaneJava() {
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(true);

        Label usernameLabel = new Label("User name:");
        usernameLabel.setTextFill(Color.BLACK);
        usernameLabel.setFont(Font.font("Comic Sans MS"));

        Label passwordLabel = new Label("Password:");
        passwordLabel.setTextFill(Color.BLACK);
        passwordLabel.setFont(Font.font("Comic Sans MS"));

        TextField username = new TextField();
        PasswordField password = new PasswordField();

        Button okButton = new Button("Ok");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(okButton, cancelButton);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);

        pane.add(usernameLabel, 0, 0);
        pane.add(username, 1, 0);
        pane.add(passwordLabel, 0, 1);
        pane.add(password, 1, 1);
        pane.add(buttons, 0, 2, 2, 1);

        GridPane.setHalignment(usernameLabel, HPos.RIGHT);
        GridPane.setHalignment(passwordLabel, HPos.RIGHT);

        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        return pane;
    }

    /**
     * Java＋CSS記法で設定する
     */
    private Pane getPaneJavaCSS() {
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(true);

        Label usernameLabel = new Label("User name:");
        usernameLabel.setStyle(
                "-fx-text-fill: black;"
                + "-fx-font-family: \"Comic Sans MS\";");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle(
                "-fx-text-fill: black;"
                + "-fx-font-family: \"Comic Sans MS\";");

        TextField username = new TextField();
        PasswordField password = new PasswordField();

        Button okButton = new Button("Ok");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(okButton, cancelButton);
        buttons.setStyle(
                "-fx-spacing: 0.5em;"
                + "-fx-alignment: center;");

        pane.add(usernameLabel, 0, 0);
        pane.add(username, 1, 0);
        pane.add(passwordLabel, 0, 1);
        pane.add(password, 1, 1);
        pane.add(buttons, 0, 2, 2, 1);

        GridPane.setHalignment(usernameLabel, HPos.RIGHT);
        GridPane.setHalignment(passwordLabel, HPos.RIGHT);

        pane.setStyle(
                "-fx-padding: 0.5em;"
                + "-fx-hgap: 0.5em;"
                + "-fx-vgap: 0.5em;");

        return pane;
    }

    /**
     * FXML記法で設定
     */
    private Pane getPaneFXML() {
        Pane pane = null;
        try {
            pane = new Pane(FXMLLoader.load(getClass().getResource("dialog.fxml")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return pane;
    }

}