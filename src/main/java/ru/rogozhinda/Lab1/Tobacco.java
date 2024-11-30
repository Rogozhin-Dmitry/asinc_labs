package ru.rogozhinda.Lab1;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static ru.rogozhinda.demo.HelloApplication.*;

public class Tobacco implements Runnable {
    private final int[] table;
    private final Circle circle;

    public Tobacco(int[] table, Circle circle) {
        this.table = table;
        this.circle = circle;
    }

    private boolean checkTobacco() {
        return table[0] == PAPER && table[1] == LIGHTS || table[1] == PAPER && table[0] == LIGHTS;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (checkTobacco()) {
                    updateTable(new int[2]);
                    circle.setFill(Color.GREEN);
                    System.out.println("Tobacco Взял спички");
                    System.out.println("Tobacco Взял бумагу");
                    System.out.println("Tobacco Курит");
                    Thread.sleep(5000);
                    System.out.println("Tobacco Покурил");
                } else {
                    circle.setFill(Color.RED);
                    System.out.println("Tobacco Ждёт ресурсы");
                    Thread.sleep(500);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}