package ru.rogozhinda.Lab2;

public class MultiThreadLogic implements ThreadLogic {
    public long[][] multiplicationMatrix(long[][] a, long[][] b) {
        int size = a.length;
        long[][] matrix = new long[size][size];
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(new MatrixThread(a[i], b, matrix, i));
            threads[i].start();
        }

        try {
            for (int i = 0; i < size; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return matrix;
    }
}
