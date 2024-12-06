package ru.rogozhinda.Lab1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Smoker implements Runnable {
    private final Table table;
    private final Circle circle;
    private final int mode;
    private final String modeStr;


    public Smoker(Table table, Circle circle, int mode, String modeStr) {
        this.table = table;
        this.circle = circle;
        this.mode = mode;
        this.modeStr = modeStr;
    }

    @Override
    public void run() {
        try {
            while (true) {
                table.check(mode);
                circle.setFill(Color.GREEN);
                System.out.println(modeStr + " Курит");
                Thread.sleep(5000);
                System.out.println(modeStr + " Покурил");
                circle.setFill(Color.RED);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
