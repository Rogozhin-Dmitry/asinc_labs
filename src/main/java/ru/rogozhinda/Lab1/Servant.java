package ru.rogozhinda.Lab1;


import javafx.scene.shape.Circle;

import java.util.Random;

import static ru.rogozhinda.asinclab1.HelloApplication.*;

public class Servant implements Runnable {
    private final Table table;
    private final Circle[] tableCircles;

    public Servant(Table table, Circle[] tableCircles) {
        this.table = table;
        this.tableCircles = tableCircles;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int[] tb = newTable();
                table.check(0);
                table.updateTable(tb);
                System.out.println("Слуга наложил на стол " + tb[0] + " " + tb[1]);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static int[] newTable() {
        Random r = new Random();
        int[] mays = new int[3];
        int[] table = new int[2];
        mays[0] = PAPER;
        mays[1] = TOBACCO;
        mays[2] = LIGHTS;
        table[0] = mays[r.nextInt(3)];
        do {
            table[1] = mays[r.nextInt(3)];
        } while (table[0] == table[1]);
        return table;
    }
}
