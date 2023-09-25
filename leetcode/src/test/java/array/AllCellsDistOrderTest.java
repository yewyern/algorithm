package array;

import java.util.Arrays;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/matrix-cells-in-distance-order/">1030. 距离顺序排列矩阵单元格</a>
 * <p>给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * <p>
 * <p>另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * <p>
 * <p>返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
 * <p>其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - * c2|。
 * <p>（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 * <p>示例 1：
 * <p>
 * <p>输入：R = 1, C = 2, r0 = 0, c0 = 0
 * <p>输出：[[0,0],[0,1]]
 * <p>解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * <p>示例 2：
 * <p>
 * <p>输入：R = 2, C = 2, r0 = 0, c0 = 1
 * <p>输出：[[0,1],[0,0],[1,1],[1,0]]
 * <p>解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * <p>[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * <p>示例 3：
 * <p>
 * <p>输入：R = 2, C = 3, r0 = 1, c0 = 2
 * <p>输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * <p>解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * <p>其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 * <p>
 *
 * @author zhou.xu
 * @since 2020/11/17 10:43
 */
public class AllCellsDistOrderTest {

    int[] dr = {1, 1, -1, -1};
    int[] dc = {1, -1, 1, -1};

    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int n = rows * cols;
        int[][] orders = new int[n][];
        orders[0] = new int[]{rCenter, cCenter};
        int p = 1;
        int[][] startPoints = new int[][]{{rCenter, cCenter}, {rCenter, cCenter}, {rCenter, cCenter}, {rCenter, cCenter}};
        int[][] directions = new int[][]{{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        int[][] moves = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (p < n) {
            for (int i = 0; i < 4; i++) {
                int[] startPoint = startPoints[i];
                int[] move = moves[i];
                startPoint[0] += move[0];
                startPoint[1] += move[1];
            }
            for (int i = 0; i < 4; i++) {
                int[] startPoint = startPoints[i];
                int[] endPoint = startPoints[i == 3 ? 0 : i + 1];
                int[] direction = directions[i];
                int[] current = new int[] {startPoint[0], startPoint[1]};
                while (current[0] != endPoint[0] && current[1] != endPoint[1]) {
                    if (current[0] >= 0 && current[0] < rows && current[1] >= 0 && current[1] < cols) {
                        orders[p++] = current;
                    }
                    current = new int[] {current[0] + direction[0], current[1] + direction[1]};
                    if (p >= n) {
                        return orders;
                    }
                }
            }
        }
        return orders;
    }

    private int[][] allCellsDistOrder2(int rows, int cols, int rCenter, int cCenter) {
        // 先生成所有的位置，然后排序
        int[][] orders = new int[rows * cols][];
        int p = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++, p++) {
                orders[p] = new int[]{i, j};
            }
        }
        Arrays.sort(orders, (a, b) -> Math.abs(a[0] - rCenter) + Math.abs(a[1] - cCenter) - Math.abs(b[0] - rCenter) - Math.abs(b[1] - cCenter));
        return orders;
    }

    public void test(int R, int C, int r0, int c0) {
        System.out.printf("R: %d, C: %d, r0: %d, c0: %d%n", R, C, r0, c0);
        int[][] orders = allCellsDistOrder(R, C, r0, c0);
        for (int[] order : orders) {
            System.out.println(Arrays.toString(order));
        }
        System.out.println("-------------------------");
    }

    @Test
    public void test() {
        test(1, 2, 0, 0);
        test(2, 2, 0, 1);
        test(2, 3, 1, 2);
    }
}
