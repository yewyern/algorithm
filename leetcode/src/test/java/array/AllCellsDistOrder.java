package array;

import java.util.Arrays;
import org.junit.Test;

/**
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
 * @date 2020/11/17 10:43
 */
public class AllCellsDistOrder {

    int[] dr = {1, 1, -1, -1};
    int[] dc = {1, -1, 1, -1};

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int N = R * C;
        int maxDist = Math.max(r0, R - r0 - 1) + Math.max(c0, C - c0 - 1);
        int[][] orders = new int[N][];
        orders[0] = new int[]{r0, c0};
        int p = 1, x = r0, y = c0;
        for (int dist = 1; dist < maxDist; dist++) {
            for (int i = 0; i < 4; i++) {
                
            }
        }
        return allCellsDistOrderViolent(R, C, r0, c0);
    }

    private int[][] allCellsDistOrderViolent(int R, int C, int r0, int c0) {
        int[][] orders = new int[R * C][];
        int p = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++, p++) {
                orders[p] = new int[]{i, j};
            }
        }
        Arrays.sort(orders,
            (a, b) -> Math.abs(a[0] - r0) + Math.abs(a[1] - c0) - Math.abs(b[0] - r0) - Math.abs(b[1] - c0));
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
