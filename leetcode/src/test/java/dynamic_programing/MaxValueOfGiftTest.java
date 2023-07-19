package dynamic_programing;

/**
 * <a href="https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 47. 礼物的最大价值</a>
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 *
 * @author xuzhou
 * @since 2023/7/19 17:18
 */
public class MaxValueOfGiftTest {

    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int n = grid[0].length;
        int[] values = new int[n];
        for (int[] arr : grid) {
            for (int j = 0; j < n; j++) {
                values[j] = arr[j] + Math.max(j > 0 ? values[j - 1] : 0, values[j]);
            }
        }
        return values[n - 1];
    }
}
