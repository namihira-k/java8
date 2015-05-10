/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import java.util.prefs.Preferences;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PropertyDialog {

    private static final Stage stage = new Stage();
    private static final GridPane pane = new GridPane();

    private final static Label fontLabel = new Label("フォント");
    private final static Label fontSizeLabel = new Label("フォントサイズ");
    private final static Label fontColorLabel = new Label("文字色");
    private final static Label backgroundColorLabel = new Label("背景色");

    private final static ComboBox<String> fontNameBox = new ComboBox<>(FXCollections.observableArrayList(
            Font.getFontNames()));

    private final static ComboBox<Double> fontSizeBox = new ComboBox<>(FXCollections.observableArrayList(
            10.0, 50.0, 100.0, 150.0));

    private final static ComboBox<String> fontColorBox = new ComboBox<>(FXCollections.observableArrayList(
            "Red", "Black", "Blue", "Green"));

    private final static ComboBox<String> backgroundColorBox = new ComboBox<>(FXCollections.observableArrayList(
            "Red", "Black", "Blue", "Green"));

    private final static Property<Font> font =  new SimpleObjectProperty<>(Font.getDefault());
    private final static Property<Color> fontColor =  new SimpleObjectProperty<>(Color.BLACK);
    private final static Property<Background> background =  new SimpleObjectProperty<>(new Background(new BackgroundFill(Color.GRAY, null, null)));

    private final static Button okButton = new Button(" OK ");
    private final static Button cancelButton = new Button("Cancel");
    private final static Button exitButton = new Button("アプリ終了");

    private static Font originFont = null;
    private static String originFontColor = null;
    private static String originBackground = null;

    private final static Preferences cache = Preferences.userNodeForPackage(PropertyDialog.class);
    private final static String KEY_FONT_NAME = "fontName";
    private final static String KEY_FONT_SIZE = "fontSize";
    private final static String KEY_FONT_COLOR = "fontColor";
    private final static String KEY_BACKGROUND = "backgroundColor";

    private static Runnable exitActionLinstener;

    static {
        buildScreen();
        buildEventHandlers();
        loadCache();
    }

    private PropertyDialog(){
    }

    private static void buildScreen(){
        final double rem = new Text("").getLayoutBounds().getHeight();
        pane.setHgap(0.8 * rem);
        pane.setVgap(0.8 * rem);
        pane.setPadding(new Insets(0.8 * rem));

        HBox buttons = new HBox(0.8 * rem);
        buttons.getChildren().addAll(okButton, cancelButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        pane.add(fontLabel, 0, 0);
        pane.add(fontNameBox, 1, 0);
        pane.add(fontSizeLabel, 0, 1);
        pane.add(fontSizeBox, 1, 1);
        pane.add(fontColorLabel, 0, 2);
        pane.add(fontColorBox, 1, 2);
        pane.add(backgroundColorLabel, 0, 3);
        pane.add(backgroundColorBox, 1, 3);
        pane.add(buttons, 0, 4, 2, 1);
        pane.add(exitButton, 0, 5, 2, 1);

        GridPane.setHalignment(fontLabel, HPos.RIGHT);
        GridPane.setHalignment(fontSizeLabel, HPos.RIGHT);
        GridPane.setHalignment(fontColorLabel, HPos.RIGHT);
        GridPane.setHalignment(backgroundColorLabel, HPos.RIGHT);
        GridPane.setHalignment(exitButton, HPos.RIGHT);

        stage.setScene(new Scene(pane));
        stage.initStyle(StageStyle.UNDECORATED);
    }

    private static void buildEventHandlers(){
        okButton.setOnAction(event -> close());

        cancelButton.setOnAction(event -> {
            font.setValue(originFont);
            fontNameBox.getSelectionModel().select(originFont.getName());
            fontSizeBox.getSelectionModel().select(originFont.getSize());

            fontColor.setValue(Color.web(originFontColor));
            fontColorBox.getSelectionModel().select(originFontColor);

            background.setValue(new Background(new BackgroundFill(Color.web(originBackground), null, null)));
            backgroundColorBox.getSelectionModel().select(originBackground);

            close();
        });

        exitButton.setOnAction(event -> {
            exitActionLinstener.run();
            cache.put(KEY_FONT_NAME, originFont.getName());
            cache.putDouble(KEY_FONT_SIZE, originFont.getSize());
            cache.put(KEY_FONT_COLOR, originFontColor);
            cache.put(KEY_BACKGROUND, originBackground);
            System.exit(0);
        });

        fontNameBox.setOnAction((event) -> {
            font.setValue(new Font(fontNameBox.getValue(), fontSizeBox.getValue()));
        });

        fontSizeBox.setOnAction((event) -> {
            font.setValue(new Font(fontNameBox.getValue(), fontSizeBox.getValue()));
        });

        fontColorBox.setOnAction((event) -> {
            fontColor.setValue(Color.web(fontColorBox.getValue()));
        });
        fontColorBox.setCellFactory((box) -> new ColorRectCell());
        fontColorBox.setButtonCell(new ColorRectCell());

        backgroundColorBox.setOnAction((event) -> {
            background.setValue(new Background(new BackgroundFill(Color.web(backgroundColorBox.getValue()), null, null)));
        });
        backgroundColorBox.setCellFactory((box) -> new ColorRectCell());
        backgroundColorBox.setButtonCell(new ColorRectCell());
    }

    private static void loadCache(){
        final String cachedFontName = cache.get(KEY_FONT_NAME, Font.getDefault().getName());
        final Double cachedFontSize = cache.getDouble(KEY_FONT_SIZE, Font.getDefault().getSize());
        font.setValue(new Font(cachedFontName, cachedFontSize));
        fontNameBox.getSelectionModel().select(cachedFontName);
        fontSizeBox.getSelectionModel().select(cachedFontSize);

        final String cachedFontColor = cache.get(KEY_FONT_COLOR, "Black");
        fontColor.setValue(Color.web(cachedFontColor));
        fontColorBox.getSelectionModel().select(cachedFontColor);

        final String cachedBackground = cache.get(KEY_BACKGROUND, "Black");
        background.setValue(new Background(new BackgroundFill(Color.web(cachedBackground), null, null)));
        backgroundColorBox.getSelectionModel().select(cachedBackground);;
    }

    static void show(final double x, final double y){
        stage.setX(x);
        stage.setY(y);
        if (!stage.isShowing()) {
            stage.show();
        }
        stage.toFront();

        originFont = font.getValue();

        originFontColor = fontColorBox.getValue();
        originBackground = backgroundColorBox.getValue();
    }

    static void close(){
        if (stage.isShowing()) {
            stage.close();
        }
    }

    static void bindFont(final Property<Font> propety){
        propety.bind(font);
    }

    static void bindFontColor(final Property<Paint> propety){
        propety.bind(fontColor);
    }

    static void bindBackground(final Property<Background> propety){
        propety.bind(background);
    }

    static void setExitActionLinstener(final Runnable action){
        exitActionLinstener = action;
    }

    private static class ColorRectCell extends ComboBoxListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(20, 20);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            } else {
                setGraphic(null);
            }
        }
    }
}