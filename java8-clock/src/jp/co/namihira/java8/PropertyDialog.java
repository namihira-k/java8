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
            10.0, 50.0, 100.0));

    private final static ComboBox<String> fontColorBox = new ComboBox<>(FXCollections.observableArrayList(
            "Red", "Black", "Blue", "Green"));

    private final static ComboBox<String> backgroundColorBox = new ComboBox<>(FXCollections.observableArrayList(
            "Red", "Black", "Blue", "Green"));

    private final static String KEY_FONT_NAME = "fontName";
    private final static String KEY_FONT_SIZE = "fontSize";
    private final static String KEY_FONT_COLOR_R = "fontColorR";
    private final static String KEY_FONT_COLOR_G = "fontColorG";
    private final static String KEY_FONT_COLOR_B = "fontColorB";
    private final static String KEY_BACKGROUND_R = "backgroundColorR";
    private final static String KEY_BACKGROUND_G = "backgroundColorG";
    private final static String KEY_BACKGROUND_B = "backgroundColorB";

    private final static Property<Font> font =  new SimpleObjectProperty<>(Font.getDefault());
    private final static Property<Color> fontColor =  new SimpleObjectProperty<>(Color.BLACK);
    private final static Property<Background> background =  new SimpleObjectProperty<>(new Background(new BackgroundFill(Color.GRAY, null, null)));

    private static Font originFont = null;
    private static Color originFontColor = null;
    private static Background originBackground = null;

    private final static Button okButton = new Button(" OK ");
    private final static Button cancelButton = new Button("Cancel");
    private final static Button exitButton = new Button("アプリ終了");

    private final static Preferences cache = Preferences.userNodeForPackage(PropertyDialog.class);

    private static Runnable exitActionLinstener;

    static void bindFont(final Property<Font> propety){
        propety.bind(font);
    }

    static void bindFontColor(final Property<Paint> propety){
        propety.bind(fontColor);
    }

    static void bindBackground(final Property<Background> propety){
        propety.bind(background);
    }

    static {
        initialize();

        final double rem = new Text("").getLayoutBounds().getHeight();
        // pane.setGridLinesVisible(true);

        pane.setHgap(0.8 * rem);
        pane.setVgap(0.8 * rem);
        pane.setPadding(new Insets(0.8 * rem));

        okButton.setOnAction(event -> close());
        cancelButton.setOnAction(event -> {
            font.setValue(originFont);
            fontColor.setValue(originFontColor);
            background.setValue(originBackground);
            close();
        });
        exitButton.setOnAction(event -> {
            exitActionLinstener.run();
            cache.put(KEY_FONT_NAME, originFont.getName());
            cache.putDouble(KEY_FONT_SIZE, originFont.getSize());

            cache.putDouble(KEY_FONT_COLOR_R, originFontColor.getRed());
            cache.putDouble(KEY_FONT_COLOR_G, originFontColor.getGreen());
            cache.putDouble(KEY_FONT_COLOR_B, originFontColor.getBlue());

            cache.putDouble(KEY_BACKGROUND_R, ((Color)background.getValue().getFills().get(0).getFill()).getRed());
            cache.putDouble(KEY_BACKGROUND_G, ((Color)background.getValue().getFills().get(0).getFill()).getGreen());
            cache.putDouble(KEY_BACKGROUND_B, ((Color)background.getValue().getFills().get(0).getFill()).getBlue());
            System.exit(0);
        });

        HBox buttons = new HBox(0.8 * rem);
        buttons.getChildren().addAll(okButton, cancelButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        fontNameBox.setValue(Font.getDefault().getName());
        fontNameBox.setOnAction((event) -> {
            font.setValue(new Font(fontNameBox.getValue(), fontSizeBox.getValue()));
        });

        fontSizeBox.getSelectionModel().select(1);
        fontSizeBox.setOnAction((event) -> {
            font.setValue(new Font(fontNameBox.getValue(), fontSizeBox.getValue()));
        });

        fontColorBox.getSelectionModel().select(1);
        fontColorBox.setOnAction((event) -> {
            fontColor.setValue(Color.web(fontColorBox.getValue()));
        });
        fontColorBox.setCellFactory((box) -> new ColorRectCell());
        fontColorBox.setButtonCell(new ColorRectCell());

        backgroundColorBox.getSelectionModel().select(1);
        backgroundColorBox.setOnAction((event) -> {
            background.setValue(new Background(new BackgroundFill(Color.web(backgroundColorBox.getValue()), null, null)));
        });
        backgroundColorBox.setCellFactory((box) -> new ColorRectCell());
        backgroundColorBox.setButtonCell(new ColorRectCell());

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

    private PropertyDialog(){
    }

    private static void initialize(){
        final String fontName = cache.get(KEY_FONT_NAME, Font.getDefault().getName());
        final Double fontSize = cache.getDouble(KEY_FONT_SIZE, Font.getDefault().getSize());
        font.setValue(new Font(fontName, fontSize));

        Double r = cache.getDouble(KEY_FONT_COLOR_R, 1.0);
        Double g = cache.getDouble(KEY_FONT_COLOR_G, 1.0);
        Double b = cache.getDouble(KEY_FONT_COLOR_B, 1.0);

        fontColor.setValue(Color.color(r, g, b));

        r = cache.getDouble(KEY_BACKGROUND_R, 1.0);
        g = cache.getDouble(KEY_BACKGROUND_G, 1.0);
        b = cache.getDouble(KEY_BACKGROUND_B, 1.0);

        background.setValue(new Background(new BackgroundFill(Color.color(r, g, b), null, null)));
    }

    static void show(final double x, final double y){
        stage.setX(x);
        stage.setY(y);
        if (!stage.isShowing()) {
            stage.show();
        }
        stage.toFront();

        originFont = font.getValue();
        originFontColor = fontColor.getValue();
        originBackground = background.getValue();
    }

    static void close(){
        if (stage.isShowing()) {
            stage.close();
        }
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