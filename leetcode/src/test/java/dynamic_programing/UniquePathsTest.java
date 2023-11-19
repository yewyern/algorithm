package dynamic_programing;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/unique-paths/">62. 不同路径</a>
 *
 * @author zhou.xu
 * @since 2023/11/19 21:54
 */
public class UniquePathsTest {

    public int uniquePaths(int m, int n) {
        // 动态规划
        int[][] dp = new int[m + 1][n + 1];
        Arrays.fill(dp[m], 1);
        for (int i = 1; i < m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 1; i--) {
            for (int j = n - 1; j >= 1; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[1][1];
    }


    public int uniquePaths1(int m, int n) {
        // 暴力递归
        return process(m, n, 1, 1);
    }

    public int process(int m, int n, int i, int j) {
        if (i == m || j == n) {
            return 1;
        }
        return process(m, n, i + 1, j) + process(m, n, i, j + 1);
    }

}
