package ru.rogozhinda.Lab1;

import static ru.rogozhinda.Lab1.Lab1.*;

public class Lights implements Runnable {
    private final int[] table;

    public Lights(int[] table) {
        this.table = table;
    }

    private boolean checkLights() {
        return table[0] == TOBACCO && table[1] == PAPER || table[1] == TOBACCO && table[0] == PAPER;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (checkLights()) {
                    updateTable(new int[2]);
                    System.out.println("Lights Взял табак");
                    System.out.println("Lights Взял бумагу");
                    System.out.println("Lights Курит");
                    Thread.sleep(5000);
                    System.out.println("Lights Покурил");
                } else {
                    System.out.println("Lights Ждёт ресурсы");
                    Thread.sleep(500);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
