package ru.rogozhinda.Lab1;

import java.util.Random;

import static ru.rogozhinda.Lab1.Lab1.*;

public class Servant implements Runnable {
    private final int[] table;

    public Servant(int[] table) {
        this.table = table;
    }

    private boolean checkServant() {
        return table[0] == 0 && table[1] == 0;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (checkServant()) {
                    updateTable(newTable());
                    System.out.println("Слуга наложил говна " + table[0] + " " + table[1]);
                    Thread.sleep(500);
                } else {
                    System.out.println("Слуга Ждёт пока стол будет свободен");
                    Thread.sleep(500);
                }
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
