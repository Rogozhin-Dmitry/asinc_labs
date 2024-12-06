package ru.rogozhinda.Lab1;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static ru.rogozhinda.asinclab1.HelloApplication.*;

public class Paper implements Runnable {
    private final Table table;
    private final Circle circle;
    private Random random = new Random();

    public Paper(Table table, Circle circle) {
        this.table = table;
        this.circle = circle;
    }


    @Override
    public void run() {
        try {
            while (true) {
                table.check(PAPER);
                circle.setFill(Color.GREEN);
                System.out.println("Paper Взял спички");
                System.out.println("Paper Взял табак");
                System.out.println("Paper Курит");
                Thread.sleep(1000 + random.nextInt(1000));
                System.out.println("Paper Покурил");
                circle.setFill(Color.RED);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}