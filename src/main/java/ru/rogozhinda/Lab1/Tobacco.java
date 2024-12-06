package ru.rogozhinda.Lab1;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static ru.rogozhinda.asinclab1.HelloApplication.TOBACCO;

public class Tobacco implements Runnable {
    private final Table table;
    private final Circle circle;
    private Random random = new Random();

    public Tobacco(Table table, Circle circle) {
        this.table = table;
        this.circle = circle;
    }

    @Override
    public void run() {
        try {
            while (true) {
                table.check(TOBACCO);
                circle.setFill(Color.GREEN);
                System.out.println("Tobacco Взял спички");
                System.out.println("Tobacco Взял бумагу");
                System.out.println("Tobacco Курит");
                Thread.sleep(1000 + random.nextInt(1000));
                System.out.println("Tobacco Покурил");
                circle.setFill(Color.RED);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}