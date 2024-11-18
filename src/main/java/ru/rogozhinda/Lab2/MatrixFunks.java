package ru.rogozhinda.Lab2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MatrixFunks {
    public static long[][] readMatrixFile(String path, int size) throws FileNotFoundException {
        try (FileReader reader = new FileReader(path)) {
            long[][] matrix = new long[size][size];
            int i = 0;
            int j = 0;
            int c;
            StringBuilder bs = new StringBuilder();
            while ((c = reader.read()) != -1) {
                if (Character.valueOf((char) c).equals('\t')) {
                    matrix[i][j] = Long.parseLong(bs.toString());
                    j++;
                    bs.delete(0, bs.length());
                } else if (Character.valueOf((char) c).equals('\n')) {
                    matrix[i][j] = Long.parseLong(bs.toString());
                    i++;
                    j = 0;
                    bs.delete(0, bs.length());
                } else {
                    if (Character.isDigit(c)) {
                        bs.append((char) c);
                    }
                }
            }

            return matrix;
        } catch (IOException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    public static void printMatrix(long[][] matrix) {
        for (int i1 = 0; i1 < matrix.length; i1++) {
            for (int j1 = 0; j1 < matrix[0].length; j1++) {
                System.out.print(matrix[i1][j1] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static long[][] getUnitMatrix(int size) {
        long[][] matrix = new long[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
        }
        return matrix;
    }

    public static boolean isEquals(long[][] aMatrix, long[][] bMatrix) {
        int size = aMatrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (aMatrix[i][j] != bMatrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
