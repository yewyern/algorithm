package dynamic_programing;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/knight-probability-in-chessboard/">688. 骑士在棋盘上的概率</a>
 *
 * @author zhou.xu
 * @since 2023/11/13 22:04
 */
public class KnightProbabilityInChessTest {


    public double knightProbability(int n, int k, int row, int column) {
        // 递归方法2 + 记忆化搜索
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        double[][][] dp = new double[k + 1][n][n];
        return process(n, k, row, column, dp) / Math.pow(8, k);
    }

    private double process(int n, int k, int row, int col, double[][][] dp) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0;
        }
        if (dp[k][row][col] > 0) {
            return dp[k][row][col];
        }
        if (k == 0) {
            return 1;
        }
        double ans = process(n, k - 1, row + 2, col + 1, dp)
                + process(n, k - 1, row + 1, col + 2, dp)
                + process(n, k - 1, row - 1, col + 2, dp)
                + process(n, k - 1, row - 2, col + 1, dp)
                + process(n, k - 1, row - 2, col - 1, dp)
                + process(n, k - 1, row - 1, col - 2, dp)
                + process(n, k - 1, row + 1, col - 2, dp)
                + process(n, k - 1, row + 2, col - 1, dp);
        dp[k][row][col] = ans;
        return ans;
    }

    public double knightProbability0(int n, int k, int row, int column) {
        // 动态规划2：先统计有效次数，再计算概率
        double[][][] dp = new double[k + 1][n][n];
        for (double[] a : dp[0]) {
            Arrays.fill(a, 1);
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    dp[i][j][l] = (choose(dp, n, i - 1, j + 2, l + 1)
                            + choose(dp, n, i - 1, j + 1, l + 2)
                            + choose(dp, n, i - 1, j - 1, l + 2)
                            + choose(dp, n, i - 1, j - 2, l + 1)
                            + choose(dp, n, i - 1, j - 2, l - 1)
                            + choose(dp, n, i - 1, j - 1, l - 2)
                            + choose(dp, n, i - 1, j + 1, l - 2)
                            + choose(dp, n, i - 1, j + 2, l - 1));
                }
            }
        }
        return dp[k][row][column] / Math.pow(8, k);
    }


    public double knightProbability1(int n, int k, int row, int column) {
        // 动态规划1：直接计算概率
        double[][][] dp = new double[k + 1][n][n];
        for (double[] a : dp[0]) {
            Arrays.fill(a, 1);
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    dp[i][j][l] = (choose(dp, n, i - 1, j + 2, l + 1)
                            + choose(dp, n, i - 1, j + 1, l + 2)
                            + choose(dp, n, i - 1, j - 1, l + 2)
                            + choose(dp, n, i - 1, j - 2, l + 1)
                            + choose(dp, n, i - 1, j - 2, l - 1)
                            + choose(dp, n, i - 1, j - 1, l - 2)
                            + choose(dp, n, i - 1, j + 1, l - 2)
                            + choose(dp, n, i - 1, j + 2, l - 1)) * 0.125;
                }
            }
        }
        return dp[k][row][column];
    }

    private double choose(double[][][] dp, int n, int k, int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0;
        }
        return dp[k][row][col];
    }


    public double knightProbability2(int n, int k, int row, int column) {
        // 递归方法1；递归计算概率
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        return (knightProbability2(n, k - 1, row + 2, column + 1)
                + knightProbability2(n, k - 1, row + 1, column + 2)
                + knightProbability2(n, k - 1, row - 1, column + 2)
                + knightProbability2(n, k - 1, row - 2, column + 1)
                + knightProbability2(n, k - 1, row - 2, column - 1)
                + knightProbability2(n, k - 1, row - 1, column - 2)
                + knightProbability2(n, k - 1, row + 1, column - 2)
                + knightProbability2(n, k - 1, row + 2, column - 1)) * 0.125;
    }

    public double knightProbability3(int n, int k, int row, int column) {
        // 递归方法2；递归计算有效次数，统一计算概率
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        return process3(n, k, row, column) / Math.pow(8, k);
    }

    private double process3(int n, int k, int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        return (process3(n, k - 1, row + 2, col + 1)
                + process3(n, k - 1, row + 1, col + 2)
                + process3(n, k - 1, row - 1, col + 2)
                + process3(n, k - 1, row - 2, col + 1)
                + process3(n, k - 1, row - 2, col - 1)
                + process3(n, k - 1, row - 1, col - 2)
                + process3(n, k - 1, row + 1, col - 2)
                + process3(n, k - 1, row + 2, col - 1));
    }
}
