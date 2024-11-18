package ru.rogozhinda.Lab2;

public class SingleThreadLogic implements ThreadLogic {
    public long[][] multiplicationMatrix(long[][] a, long[][] b) {
        int size = a.length;
        long[][] matrix = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    matrix[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return matrix;
    }
}
