package ru.rogozhinda.Lab1;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static ru.rogozhinda.asinclab1.HelloApplication.*;


public class Lights implements Runnable {
    private final Table table;
    private final Circle circle;
    private Random random = new Random();

    public Lights(Table table, Circle circle) {
        this.table = table;
        this.circle = circle;
    }

    @Override
    public void run() {
        try {
            while (true) {
                table.check(LIGHTS);
                circle.setFill(Color.GREEN);
                System.out.println("Lights Взял табак");
                System.out.println("Lights Взял бумагу");
                System.out.println("Lights Курит");
                Thread.sleep(1000 + random.nextInt(1000));
                System.out.println("Lights Покурил");
                circle.setFill(Color.RED);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
