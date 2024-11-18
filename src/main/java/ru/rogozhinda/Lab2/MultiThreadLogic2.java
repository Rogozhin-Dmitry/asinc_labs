package ru.rogozhinda.Lab2;

public class MultiThreadLogic2 implements ThreadLogic {
    private final int threadsCount;

    public MultiThreadLogic2(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    public long[][] multiplicationMatrix(long[][] a, long[][] b) {
        int size = a.length;
        long[][] matrix = new long[size][size];
        int c = size / threadsCount;
        Thread[] threads = new Thread[threadsCount];
        for (int i = 0; i < threadsCount - 1; i++) {
            threads[i] = new Thread(new MatrixThread2(a, b, matrix, i * c, (i + 1) * c));
            threads[i].start();
        }
        threads[threadsCount - 1] = new Thread(new MatrixThread2(a, b, matrix, (threadsCount - 1) * c, size));
        threads[threadsCount - 1].start();
        try {
            for (int i = 0; i < threadsCount; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return matrix;
    }
}
