package ru.rogozhinda.Lab2;

public class MatrixThread2 implements Runnable {
    private final long[][] aMatrix;
    private final long[][] bMatrix;
    private final long[][] result;
    private final int rowStartNumber;
    private final int rowEndNumber;


    public MatrixThread2(long[][] aMatrix, long[][] bMatrix, long[][] result, int rowStartNumber, int rowEndNumber) {
        this.aMatrix = aMatrix;
        this.bMatrix = bMatrix;
        this.result = result;
        this.rowStartNumber = rowStartNumber;
        this.rowEndNumber = rowEndNumber;
    }

    @Override
    public void run() {
        long c;
        for (int rowNumber = rowStartNumber; rowNumber < rowEndNumber; rowNumber++) {
            for (int j = 0; j < aMatrix.length; j++) {
                c = 0;
                for (int i = 0; i < aMatrix.length; i++) {
                    c += aMatrix[rowNumber][i] * bMatrix[i][j];
                }
                result[rowNumber][j] = c;
            }
        }
    }
}
