/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 惑星を表す円をアニメーション化して、楕円軌道を回るようにしなさい。
 * PathTransitionを使用します。
 */

package jp.co.namihira.java8;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final int stageSize = 500;
        stage.setWidth(stageSize);
        stage.setHeight(stageSize);

        final Circle earth = new Circle();
        earth.setRadius(10);
        earth.setFill(Color.BLUE);

        final Path path = createEllipsePath(
                stage.getWidth() / 2, stage.getHeight() / 2,
                stage.getWidth() / 3, stage.getHeight() / 4,
                0);

        final PathTransition pt = new PathTransition();
        pt.setNode(earth);
        pt.setPath(path);
        pt.setDuration(Duration.millis(3_000));
        pt.setCycleCount(Animation.INDEFINITE);
        pt.setAutoReverse(false);
        pt.setInterpolator(Interpolator.LINEAR);
        pt.play();

        final Pane pane = new Pane();
        pane.getChildren().add(earth);
        pane.getChildren().add(path);

        stage.setScene(new Scene(pane));
        stage.show();
    }

    private Path createEllipsePath(double centerX, double centerY, double radiusX, double radiusY, double rotate) {
        final MoveTo moveTo = new MoveTo();
        moveTo.setX(centerX - radiusX);
        moveTo.setY(centerY);

        final ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radiusX);
        arcTo.setY(centerY - 1);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        arcTo.setXAxisRotation(rotate);
        arcTo.setSweepFlag(false);
        arcTo.setLargeArcFlag(true);

        final Path path = new Path();
        path.getElements().add(moveTo);
        path.getElements().add(arcTo);

        return path;
    }

}