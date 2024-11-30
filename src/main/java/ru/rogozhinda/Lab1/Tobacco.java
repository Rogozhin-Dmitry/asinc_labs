package ru.rogozhinda.Lab1;

import static ru.rogozhinda.Lab1.Lab1.*;

public class Tobacco implements Runnable {
    private final int[] table;

    public Tobacco(int[] table) {
        this.table = table;
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
                    System.out.println("Tobacco Взял спички");
                    System.out.println("Tobacco Взял бумагу");
                    System.out.println("Tobacco Курит");
                    Thread.sleep(5000);
                    System.out.println("Tobacco Покурил");
                } else {
                    System.out.println("Tobacco Ждёт ресурсы");
                    Thread.sleep(500);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}