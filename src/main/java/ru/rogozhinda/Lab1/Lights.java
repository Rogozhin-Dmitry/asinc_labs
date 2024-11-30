package ru.rogozhinda.Lab1;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static ru.rogozhinda.demo.HelloApplication.*;

public class Lights implements Runnable {
    private final int[] table;
    private final Circle circle;

    public Lights(int[] table, Circle circle) {
        this.table = table;
        this.circle = circle;
    }

    private boolean checkLights() {
        return table[0] == TOBACCO && table[1] == PAPER || table[0] == PAPER && table[1] == TOBACCO;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (checkLights()) {
                    updateTable(new int[2]);
                    circle.setFill(Color.GREEN);
                    System.out.println("Lights Взял табак");
                    System.out.println("Lights Взял бумагу");
                    System.out.println("Lights Курит");
                    Thread.sleep(5000);
                    System.out.println("Lights Покурил");
                } else {
                    circle.setFill(Color.RED);
                    System.out.println("Lights Ждёт ресурсы");
                    Thread.sleep(500);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
