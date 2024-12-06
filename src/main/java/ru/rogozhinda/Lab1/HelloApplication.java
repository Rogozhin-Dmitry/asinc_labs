package ru.rogozhinda.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import ru.rogozhinda.demo.Lab1.Servant;
import ru.rogozhinda.demo.Lab1.Smoker;
import ru.rogozhinda.demo.Lab1.Table;

import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {
    public static final int TOBACCO = 1;
    public static final int LIGHTS = 2;
    public static final int PAPER = 3;
    public static final List<String> smokersTitles = List.of("Tobacco", "Lights", "Paper");
    public static final List<Color> smokersColors = List.of(Color.WHITE, Color.BURLYWOOD, Color.GOLD, Color.PALETURQUOISE);
    private static final Circle[] smokersCircles = new Circle[3];
    private static final Circle[] exampleCircles = new Circle[3];
    private static final Label[] exampleLabels = new Label[3];
    private static final Label[] smokersLabels = new Label[3];
    private static final Circle[] tableCircles = new Circle[2];
    private static final Label[] tableLabels = new Label[2];
    private static final Circle[] exampleSmokersCircles = new Circle[6];


    public static void main(String[] args) {

        int c = 0;
        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle();
            circle.setRadius(50);
            circle.setCenterX(100 + (i * 200));
            circle.setCenterY(100);
            circle.setFill(Color.RED);
            smokersCircles[i] = circle;

            for (int j = 1; j < 4; j++) {
                if (j == i + 1) {
                    continue;
                }
                circle = new Circle();
                circle.setRadius(20);
                circle.setCenterX(100 + (i * 200) +  35 * Math.pow(-1, c % 2));
                circle.setCenterY(165);
                circle.setFill(smokersColors.get(j));
                exampleSmokersCircles[c] = circle;
                c++;
                System.out.println(c + " " + i);
            }

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
        new Thread(new Smoker(table, smokersCircles[0], TOBACCO, "TOBACCO")).start();
        new Thread(new Smoker(table, smokersCircles[1], LIGHTS, "LIGHTS")).start();
        new Thread(new Smoker(table, smokersCircles[2], PAPER, "PAPER")).start();
        new Thread(new Servant(table, tableCircles)).start();

        Application.launch();
    }


    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.getChildren().addAll(Arrays.asList(smokersCircles));
        pane.getChildren().addAll(Arrays.asList(smokersLabels));
        pane.getChildren().addAll(Arrays.asList(exampleSmokersCircles));

        pane.getChildren().addAll(Arrays.asList(tableCircles));
        pane.getChildren().addAll(Arrays.asList(tableLabels));

        pane.getChildren().addAll(Arrays.asList(exampleCircles));
        pane.getChildren().addAll(Arrays.asList(exampleLabels));

        Scene scene = new Scene(pane, 640, 580);
        stage.setScene(scene);
        stage.show();
    }
}
