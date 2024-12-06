package ru.rogozhinda.Lab1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {
    public static final int PAPER = 1;
    public static final int TOBACCO = 2;
    public static final int LIGHTS = 3;
    private static final Circle[] smokersCircles = new Circle[3];
    private static final Circle[] exampleCircles = new Circle[3];
    private static final Label[] exampleLabels = new Label[3];
    private static final Label[] smokersLabels = new Label[3];
    private static final List<String> smokersTitles = List.of("Tobacco", "Lights", "Paper");
    public static final List<Color> smokersColors = List.of(Color.WHITE, Color.BURLYWOOD, Color.GOLD, Color.PALETURQUOISE);
    private static final Circle[] tableCircles = new Circle[2];
    private static final Label[] tableLabels = new Label[2];

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle();
            circle.setRadius(50);
            circle.setCenterX(100 + (i * 200));
            circle.setCenterY(100);
            circle.setFill(Color.RED);
            smokersCircles[i] = circle;

            Label label = new Label(smokersTitles.get(i));
            label.setStyle("-fx-font-size:  30;");
            label.setLayoutX(60 + (i * 200));
            label.setLayoutY(8);
            smokersLabels[i] = label;
        }

        for (int i = 0; i < 2; i++) {
            Circle circle = new Circle();
            circle.setRadius(50);
            circle.setCenterX(200 + (i * 200));
            circle.setCenterY(300);
            tableCircles[i] = circle;

            Label label = new Label("Table " + (i + 1));
            label.setStyle("-fx-font-size:  30;");
            label.setLayoutX(160 + (i * 200));
            label.setLayoutY(208);
            tableLabels[i] = label;
        }

        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle();
            circle.setRadius(50);
            circle.setCenterX(100 + (i * 200));
            circle.setCenterY(100);
            smokersCircles[i] = circle;

            Label label = new Label(smokersTitles.get(i));
            label.setStyle("-fx-font-size:  30;");
            label.setLayoutX(60 + (i * 200));
            label.setLayoutY(8);
            smokersLabels[i] = label;
        }

        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle();
            circle.setRadius(20);
            circle.setCenterX(100 + (i * 200));
            circle.setCenterY(500);
            circle.setFill(smokersColors.get(i + 1));
            exampleCircles[i] = circle;

            Label label = new Label(smokersTitles.get(i));
            label.setStyle("-fx-font-size:  25;");
            label.setLayoutX(60 + (i * 200));
            label.setLayoutY(428);
            exampleLabels[i] = label;
        }

        Table table = new Table(tableCircles);
        new Thread(new Tobacco(table, smokersCircles[0])).start();
        new Thread(new Lights(table, smokersCircles[1])).start();
        new Thread(new Paper(table, smokersCircles[2])).start();
        new Thread(new Servant(table, tableCircles)).start();

        Application.launch();
    }


    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.getChildren().addAll(Arrays.asList(smokersCircles));
        pane.getChildren().addAll(Arrays.asList(smokersLabels));

        pane.getChildren().addAll(Arrays.asList(tableCircles));
        pane.getChildren().addAll(Arrays.asList(tableLabels));

        pane.getChildren().addAll(Arrays.asList(exampleCircles));
        pane.getChildren().addAll(Arrays.asList(exampleLabels));

        Scene scene = new Scene(pane, 640, 580);
        stage.setScene(scene);
        stage.show();
    }
}
