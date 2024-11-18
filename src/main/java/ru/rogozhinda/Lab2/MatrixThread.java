package ru.rogozhinda.Lab2;

public class MatrixThread implements Runnable {
    private final long[] col;
    private final long[][] bMatrix;
    private final long[][] result;
    private final int rowNumber;


    public MatrixThread(long[] col, long[][] bMatrix, long[][] result, int rowNumber) {
        this.col = col;
        this.bMatrix = bMatrix;
        this.result = result;
        this.rowNumber = rowNumber;
    }

    @Override
    public void run() {
        long c;
        for (int j = 0; j < col.length; j++) {
            c = 0;
            for (int i = 0; i < col.length; i++) {
                c += col[i] * bMatrix[i][j];
            }
            result[rowNumber][j] = c;
        }
    }
}
