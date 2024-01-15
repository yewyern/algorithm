package dynamic_programing;

/**
 * <a href="https://leetcode.cn/problems/unique-paths-ii">63. 不同路径 II</a>
 * @author xuzhou
 * @since 2024/1/15 15:36
 */
public class UniquePathsWithObstaclesTest {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] != 0) {
                break;
            }
            path[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            path[0] = obstacleGrid[i][0] == 0 ? path[0] : 0;
            for (int j = 1; j < n; j++) {
                path[j] = obstacleGrid[i][j] == 0 ? path[j - 1] + path[j] : 0;
            }
        }
        return path[n - 1];
    }

}
