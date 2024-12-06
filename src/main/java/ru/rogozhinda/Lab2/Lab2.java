package ru.rogozhinda.Lab2;

import java.io.FileNotFoundException;

public class Lab2 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.nanoTime();
//        testAlg(new SingleThreadLogic());
        testAlg(new MultiThreadLogic());
        testAlg(new MultiThreadLogic2(12));
        long endTime = System.nanoTime();
        System.out.println("All time: " + (endTime - startTime) / 1_000_000_000.0);
    }

    public static void testAlg(ThreadLogic threadLogic) throws FileNotFoundException {
        int SIZE = 977;
        int REPEAT_COUNT = 1;
        int MATRIX_COUNT = 10;
        System.out.println("starts new test");
        long[][][] matrixTestsA = new long[MATRIX_COUNT][][];

        long[][][] matrixTestsB = new long[MATRIX_COUNT][][];
        long[][][] matrixTestsResults = new long[MATRIX_COUNT][][];
        System.out.println("data reading begins");
        for (int i = 0; i < MATRIX_COUNT; i++) {
            matrixTestsA[i] = MatrixFunks.readMatrixFile("src/main/resources/lab2/test10/a_" + i + ".txt", SIZE);
            matrixTestsB[i] = MatrixFunks.readMatrixFile("src/main/resources/lab2/test10/b_" + i + ".txt", SIZE);
            matrixTestsResults[i] = MatrixFunks.readMatrixFile("src/main/resources/lab2/test10/c_" + i + ".txt", SIZE);
        }
        System.out.println("data reading ends correct");

        long SumTime = 0;
        for (int j = 0; j < REPEAT_COUNT; j++) {
            for (int i = 0; i < MATRIX_COUNT; i++) {
                long[][] matrixA = matrixTestsA[i];
                long[][] matrixB = matrixTestsB[i];
                long[][] matrixResult = matrixTestsResults[i];

                long startTime = System.nanoTime();
                long[][] singleResult = threadLogic.multiplicationMatrix(matrixA, matrixB);
                long endTime = System.nanoTime();

                if (!MatrixFunks.isEquals(singleResult, matrixResult)) {
                    System.out.println("ERROR result is incorrect");
                    throw new Error();
                }
                SumTime += endTime - startTime;
            }
            System.out.println("Repeat " + j + " ends correct");
        }
        System.out.println("All time for test: " + SumTime / 1_000_000_000.0);
        System.out.println("Average time: " + (SumTime / (MATRIX_COUNT * REPEAT_COUNT)) / 1_000_000_000.0);
    }
}
