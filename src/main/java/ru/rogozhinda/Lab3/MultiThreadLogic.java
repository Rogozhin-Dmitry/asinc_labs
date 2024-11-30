package ru.rogozhinda.Lab3;

import java.math.BigInteger;

public class MultiThreadLogic implements ThreadLogic {
    public int[] getNulls(int[][] n) {
        int[] result_i = new int[n.length];
        int[] result_j = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                if (n[i][j] == 0) {
                    result_i[i] += 1;
                    result_j[j] += 1;
                }
            }
        }
        int ma_i = 0;
        int i = 0;
        for (int k = 0; k < n.length; k++) {
            if (result_i[k] > ma_i) {
                ma_i = result_i[k];
                i = k;
            }
        }

        int ma_j = 0;
        int j = 0;
        for (int k = 0; k < n.length; k++) {
            if (result_j[k] > ma_j) {
                ma_j = result_j[k];
                j = k;
            }
        }

        int[] result = new int[2];
        if (ma_i >= ma_j) {
            result[0] = i;
            result[1] = 1;
        } else {
            result[0] = j;
        }
        return result;
    }

    public int[][] makeMatrix(int[][] n, int i_skip, int j_skip) {
        int[][] result = new int[n.length - 1][n.length - 1];
        for (int i = 0; i < n.length; i++) {
            if (i == i_skip) {
                continue;
            }
            for (int j = 0; j < n.length; j++) {
                if (j == j_skip) {
                    continue;
                }
                if (i < i_skip && j < j_skip) {
                    result[i][j] = n[i][j];
                } else if (i < i_skip) {
                    result[i][j - 1] = n[i][j];
                } else if (j < j_skip) {
                    result[i - 1][j] = n[i][j];
                } else {
                    result[i - 1][j - 1] = n[i][j];
                }
            }
        }
        return result;
    }


    @Override
    public BigInteger determinant(int[][] n) {
        if (n.length == 3) {
            return BigInteger.valueOf((long) n[0][0] * n[1][1] * n[2][2] +
                    (long) n[2][0] * n[0][1] * n[1][2] +
                    (long) n[1][0] * n[2][1] * n[0][2] -
                    (long) n[2][0] * n[1][1] * n[0][2] -
                    (long) n[1][0] * n[0][1] * n[2][2] -
                    (long) n[0][0] * n[1][2] * n[2][1]);
        } else if (n.length == 2) {
            return BigInteger.valueOf((long) n[0][0] * n[1][1] + (long) n[0][1] * n[1][0]);
        } else if (n.length == 1) {
            return BigInteger.valueOf(n[0][0]);
        }
        BigInteger s = BigInteger.valueOf(0);
        int[] i_j = getNulls(n);
        int size = n.length;
        MatrixThread[] threads = new MatrixThread[size];
        if (i_j[1] == 1) {
            int i = i_j[0];
            for (int j = 0; j < n.length; j++) {
                threads[j] = new MatrixThread(n, i, j);
                threads[j].fork();
            }
        } else {
            int j = i_j[0];
            for (int i = 0; i < n.length; i++) {
                threads[i] = new MatrixThread(n, i, j);
                threads[i].fork();
            }
        }

        for (int i = 0; i < size; i++) {
            s = s.add(threads[i].join());
        }
        return s;
    }
}
