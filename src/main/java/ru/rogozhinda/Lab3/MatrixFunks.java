package ru.rogozhinda.Lab3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MatrixFunks {
    public static long readResultFile(String path) throws FileNotFoundException {
        try (FileReader reader = new FileReader(path)) {
            long result;
            int c;
            StringBuilder bs = new StringBuilder();
            boolean q = false;
            while ((c = reader.read()) != -1) {
                if (Character.isDigit(c)) {
                    bs.append((char) c);
                } else if (Character.valueOf((char) c).equals('-')) {
                    q = true;
                }
            }
            result = Long.parseLong(bs.toString());
            if (q) {
                result = -result;
            }

            return result;

        } catch (IOException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    public static int[][] readMatrixFile(String path, int size) throws FileNotFoundException {
        try (FileReader reader = new FileReader(path)) {
            int[][] matrix = new int[size][size];
            int i = 0;
            int j = 0;
            int c;
            StringBuilder bs = new StringBuilder();
            boolean q = false;
            while ((c = reader.read()) != -1) {
                if (Character.valueOf((char) c).equals('\t')) {
                    matrix[i][j] = Integer.parseInt(bs.toString());
                    if (q) {
                        matrix[i][j] = -matrix[i][j];
                        q = false;
                    }
                    j++;
                    bs.delete(0, bs.length());
                } else if (Character.valueOf((char) c).equals('\n')) {
                    matrix[i][j] = Integer.parseInt(bs.toString());
                    if (q) {
                        matrix[i][j] = -matrix[i][j];
                        q = false;
                    }
                    i++;
                    j = 0;
                    bs.delete(0, bs.length());
                } else {
                    if (Character.isDigit(c)) {
                        bs.append((char) c);
                    } else if (Character.valueOf((char) c).equals('-')) {
                        q = true;
                    }
                }
            }

            return matrix;
        } catch (IOException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i1 = 0; i1 < matrix.length; i1++) {
            for (int j1 = 0; j1 < matrix[0].length; j1++) {
                System.out.print(matrix[i1][j1] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] getUnitMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
        }
        return matrix;
    }

    public static boolean isEquals(int[][] aMatrix, int[][] bMatrix) {
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
