package search;

/**
 * <a href="https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 13. 机器人的运动范围</a>
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 * <p>
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 *
 * @author xuzhou
 * @since 2023/7/10 14:35
 */
public class MovingCountTest {

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return process(visited, m, n, k, 0, 0);
    }

    private int process(boolean[][] visited, int m, int n, int k, int row, int col) {
        if (row < 0 || col < 0 || row >= m || col >= n || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        int sum = sumDigits(row) + sumDigits(col);
        if (sum > k) {
            return 0;
        }
        return 1 + process(visited, m, n, k, row + 1, col)
                + process(visited, m, n, k, row - 1, col)
                + process(visited, m, n, k, row, col + 1)
                + process(visited, m, n, k, row, col - 1);
    }

    private int sumDigits(int a) {
        int sum = 0;
        while (a > 0) {
            sum += a % 10;
            a /= 10;
        }
        return sum;
    }
}
