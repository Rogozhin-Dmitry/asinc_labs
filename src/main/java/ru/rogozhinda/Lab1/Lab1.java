package ru.rogozhinda.Lab1;

public class Lab1 {
    public static final int PAPER = 1;
    public static final int TOBACCO = 2;
    public static final int LIGHTS = 3;
    static int[] table = new int[2];

    public static void main(String[] args) {
        new Thread(new Tobacco(table)).start();
        new Thread(new Lights(table)).start();
        new Thread(new Paper(table)).start();
        new Thread(new Servant(table)).start();
    }

    public static synchronized void updateTable(int[] newTable) {
        table[0] = newTable[0];
        table[1] = newTable[1];
        System.out.println("Сейчас на столе " + newTable[0] + " " + newTable[1]);
    }
}
