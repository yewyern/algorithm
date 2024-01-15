package dynamic_programing;

import org.junit.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/minimum-path-sum/">64. 最小路径和</a>
 * @author xuzhou
 * @since 2024/1/15 15:20
 */
public class MinPathSumTest {

    @Test
    public void test() {
        System.out.println(minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = Arrays.copyOf(grid[0], n);
        for (int i = 1; i < n; i++) {
            dp[i] += dp[i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j - 1], dp[j]);
            }
        }
        return dp[n - 1];
    }
}
