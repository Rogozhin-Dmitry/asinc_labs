package ru.rogozhinda.Lab1;

import static ru.rogozhinda.Lab1.Lab1.*;

public class Paper implements Runnable {
    private final int[] table;

    public Paper(int[] table) {
        this.table = table;
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
                    System.out.println("Paper Взял спички");
                    System.out.println("Paper Взял табак");
                    System.out.println("Paper Курит");
                    Thread.sleep(5000);
                    System.out.println("Paper Покурил");
                } else {
                    System.out.println("Paper Ждёт ресурсы");
                    Thread.sleep(500);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}