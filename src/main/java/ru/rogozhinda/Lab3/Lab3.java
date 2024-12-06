package ru.rogozhinda.Lab3;


import java.io.FileNotFoundException;
import java.math.BigInteger;

import static ru.rogozhinda.Lab3.MatrixFunks.readMatrixFile;
import static ru.rogozhinda.Lab3.MatrixFunks.readResultFile;


public class Lab3 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.nanoTime();
        testAlg(new SingleThreadLogic());
        testAlg(new MultiThreadLogic());
        long endTime = System.nanoTime();
        System.out.println("All time: " + (endTime - startTime) / 1_000_000_000.0);
    }

    public static void testAlg(ThreadLogic threadLogic) throws FileNotFoundException {
        int SIZE = 11;
        int REPEAT_COUNT = 1;
        int MATRIX_COUNT = 10;

        System.out.println("starts new test");
        int[][][] matrixTestsA = new int[MATRIX_COUNT][][];
        long[] matrixTestsResults = new long[MATRIX_COUNT];
        System.out.println("data reading begins");
        for (int i = 0; i < MATRIX_COUNT; i++) {
            matrixTestsA[i] = readMatrixFile("src/main/resources/lab3/test_" + i + ".txt", SIZE);
            matrixTestsResults[i] = readResultFile("src/main/resources/lab3/res_" + i + ".txt");
        }
        System.out.println("data reading ends correct");

        long SumTime = 0;
        for (int j = 0; j < REPEAT_COUNT; j++) {
            for (int i = 0; i < MATRIX_COUNT; i++) {
                int[][] matrixA = matrixTestsA[i];
                long matrixResult = matrixTestsResults[i];

                long startTime = System.nanoTime();
                BigInteger result = threadLogic.determinant(matrixA);
                long endTime = System.nanoTime();

                if (!BigInteger.valueOf(matrixResult).equals(result)) {
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
