/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 次のメソッドを書きなさい。
 *
 *  public static <T, R> ObservableValue<R> observe(
 *      Function<T, R> f, ObservableValue<T> t)
 *
 *  public static <T, U, R> ObservableValue<R> observe(
 *      BiFunction<T, U, R> f, ObservableValue<T> t,
 *      ObservableValue<U> u)
 *
 * このメソッドは、ObservableValueを返し、そのObservableValueのgetValueメソッドは、
 * ラムダ式の値を返します。
 * そして、そのObservableValueのInvalidationListenerとChangeListenerが、
 * 入力のどれかが無効あるいは変更になったときに呼び出されるようにしなさい。
 * たとえば、次の通りです。
 *
 *  larger.disableProperty().bind(observe(
 *      t -> t >= 100, gauge.widthProperty()));
 */

package jp.co.namihira.java8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button smaller = new Button("Smaller");
        Button larger = new Button("Larger");
        Rectangle gauge = new Rectangle(0, 5, 50, 15);
        Rectangle outline = new Rectangle(0, 5, 100, 15);
        outline.setFill(null);
        outline.setStroke(Color.BLACK);
        Pane pane = new Pane();
        pane.getChildren().addAll(gauge, outline);
        smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
        larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));
//        smaller.disableProperty().bind(lessThanOrEqual(gauge.widthProperty(), 0));
//        larger.disableProperty().bind(greaterThanOrEqual(gauge.widthProperty(), 100));

        smaller.disableProperty().bind(
                ObservableUtils.observe(t -> t.intValue() <= 0, gauge.widthProperty()));
        larger.disableProperty().bind(
                ObservableUtils.observe(t -> t.intValue() >= 100, gauge.widthProperty()));

        outline.strokeProperty().bind(
                ObservableUtils.observe(
                        (g, o) -> {
                            if (g.intValue() >= 100 && o.booleanValue()) {
                                return Color.RED;
                            } else {
                                return Color.BLACK;
                            }
                        },
                        gauge.widthProperty(), larger.disableProperty()));

        HBox box = new HBox(10);
        box.getChildren().addAll(smaller, pane, larger);
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }

}