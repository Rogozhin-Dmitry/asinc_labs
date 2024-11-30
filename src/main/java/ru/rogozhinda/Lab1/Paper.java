package ru.rogozhinda.Lab1;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static ru.rogozhinda.demo.HelloApplication.*;

public class Paper implements Runnable {
    private final int[] table;
    private final Circle circle;

    public Paper(int[] table, Circle circle) {
        this.table = table;
        this.circle = circle;
    }

    private boolean checkPaper() {
        return table[0] == TOBACCO && table[1] == LIGHTS || table[1] == TOBACCO && table[0] == LIGHTS;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (checkPaper()) {
                    updateTable(new int[2]);
                    circle.setFill(Color.GREEN);
                    System.out.println("Paper Взял спички");
                    System.out.println("Paper Взял табак");
                    System.out.println("Paper Курит");
                    Thread.sleep(5000);
                    System.out.println("Paper Покурил");
                } else {
                    circle.setFill(Color.RED);
                    System.out.println("Paper Ждёт ресурсы");
                    Thread.sleep(500);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}