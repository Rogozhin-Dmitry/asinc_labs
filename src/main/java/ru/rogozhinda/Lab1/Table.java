package ru.rogozhinda.Lab1;

import javafx.scene.shape.Circle;

import static ru.rogozhinda.demo.HelloApplication.*;


public class Table {
    private final Circle[] tableCircles;

    private int table1 = 0;
    private int table2 = 0;

    public Table(Circle[] tableCircles) {
        this.tableCircles = tableCircles;
    }

    public synchronized void updateTable(int[] newTable) throws InterruptedException {
        table1 = newTable[0];
        table2 = newTable[1];
        tableCircles[0].setFill(smokersColors.get(newTable[0]));
        tableCircles[1].setFill(smokersColors.get(newTable[1]));
        notifyAll();
    }

    public synchronized void check(int need) throws InterruptedException {
        if (need == PAPER) {
            while (!((table1 == TOBACCO && table2 == LIGHTS) || (table2 == TOBACCO && table1 == LIGHTS))) {
                wait();
            }
            table1 = 0;
            table2 = 0;
            notifyAll();
        } else if (need == LIGHTS) {
            while (!((table1 == TOBACCO && table2 == PAPER) || (table2 == TOBACCO && table1 == PAPER))) {
                wait();
            }
            table1 = 0;
            table2 = 0;
            notifyAll();
        } else if (need == TOBACCO) {
            while (!((table1 == PAPER && table2 == LIGHTS) || (table2 == PAPER && table1 == LIGHTS))) {
                wait();
            }
            table1 = 0;
            table2 = 0;
            notifyAll();
        } else if (need == 0) {
            while (!(table1 == 0 && table2 == 0)) {
                wait();
            }
        }
    }
}
