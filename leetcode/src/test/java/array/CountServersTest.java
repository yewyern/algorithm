package array;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/count-servers-that-communicate">1267. 统计参与通信的服务器</a>
 * 这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
 * <p>
 * 如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
 * <p>
 * 请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,0],[0,1]]
 * 输出：0
 * 解释：没有一台服务器能与其他服务器进行通信。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,0],[1,1]]
 * 输出：3
 * 解释：所有这些服务器都至少可以与一台别的服务器进行通信。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * 输出：4
 * 解释：第一行的两台服务器互相通信，第三列的两台服务器互相通信，但右下角的服务器无法与其他服务器通信。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 250
 * 1 <= n <= 250
 * grid[i][j] == 0 or 1
 *
 * @author xuzhou
 * @since 2023/8/24 16:24
 */
public class CountServersTest {

    @Test
    public void test() {
        System.out.println(countServers(new int[][]{{1, 0}, {1, 1}}));
    }

    public int countServers(int[][] grid) {
        int n = grid[0].length;
        int total = 0;
        int[] counts = new int[n];
        int[] notAdd = new int[n];
        for (int[] row : grid) {
            int start = 0, end = n - 1;
            while (start < n && row[start] == 0) {
                start++;
            }
            while (end > start && row[end] == 0) {
                end--;
            }
            boolean oneInRow = start == end;
            while (start <= end) {
                if (row[start] != 0) {
                    counts[start]++;
                    if (oneInRow) {
                        notAdd[start]++;
                    } else {
                        total++;
                    }
                    if (counts[start] > 1) {
                        // 列上不止一个
                        total += notAdd[start];
                        notAdd[start] = 0;
                    }
                }
                start++;
            }
        }
        return total;
    }

    public int countServers2(int[][] grid) {
        // 最简单的做法：
        int m = grid.length;
        int n = grid[0].length;
        int total = 0;
        // 先横着查一遍
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    count++;
                }
            }
            if (count > 1) {
                total += count;
                // 已经记过的打个标记
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] != 0) {
                        grid[i][j] = -1;
                    }
                }
            }
        }
        // 再竖着查一遍
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int[] nums : grid) {
                if (nums[i] != 0) {
                    count++;
                }
            }
            if (count > 1) {
                // 已经记过的打个标记
                for (int j = 0; j < n; j++) {
                    if (grid[j][i] == 1) {
                        total++;
                    }
                }
            }
        }
        return total;
    }
}
