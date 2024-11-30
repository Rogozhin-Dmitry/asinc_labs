package ru.rogozhinda.Lab3;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

import static ru.rogozhinda.Lab3.MatrixFunks.printMatrix;

public class MatrixThread extends RecursiveTask<BigInteger> {
    private final int[][] n;
    private final int i;
    private final int j;

    public MatrixThread(int[][] n, int i, int j) {
        this.n = n;
        this.i = i;
        this.j = j;
    }

    private int[] getNulls(int[][] n) {
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

    private int[][] makeMatrix(int[][] n, int i_skip, int j_skip) {
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

    private BigInteger determinant(int[][] n) {
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
        if (i_j[1] == 1) {
            int i = i_j[0];
            for (int j = 0; j < n.length; j++) {
                if (n[i][j] != 0) {
                    s = s.add(
                            determinant(makeMatrix(n, i, j)).multiply(
                                    BigInteger.valueOf(
                                            (long) (n[i][j] * Math.pow((-1), i) * Math.pow((-1), j))))
                    );
                }
            }
        } else {
            int j = i_j[0];
            for (int i = 0; i < n.length; i++) {
                if (n[i][j] != 0) {
                    s = s.add(
                            determinant(makeMatrix(n, i, j)).multiply(
                                    BigInteger.valueOf(
                                            (long) (n[i][j] * Math.pow((-1), i) * Math.pow((-1), j))))
                    );
                }
            }
        }
        if (n.length == 15) {
            printMatrix(n);
        }
        return s;
    }

    @Override
    protected BigInteger compute() {
        if (this.n[this.i][this.j] == 0) {
            return BigInteger.ZERO;
        }
        BigInteger s = determinant(makeMatrix(this.n, this.i, this.j)).multiply(
                BigInteger.valueOf(
                        (long) (this.n[this.i][this.j] * Math.pow((-1), this.i) * Math.pow((-1), this.j))));
        return s;
    }
}
