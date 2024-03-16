package easy;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/spiral-matrix">54. 螺旋矩阵</a>
 * @author zhou.xu
 * @since 2020/9/14 16:13
 */
public class SpiralOrderTest {

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> ans = new ArrayList<>(m * n);
        int r1 = 0, c1 = 0, r2 = m - 1, c2 = n - 1;
        while (r1 < r2 && c1 < c2) {
            for (int i = c1; i < c2; i++) {
                ans.add(matrix[r1][i]);
            }
            for (int i = r1; i < r2; i++) {
                ans.add(matrix[i][c2]);
            }
            for (int i = c2; i > c1; i--) {
                ans.add(matrix[r2][i]);
            }
            for (int i = r2; i > r1; i--) {
                ans.add(matrix[i][c1]);
            }
            r1++;
            c1++;
            r2--;
            c2--;
        }
        if (r1 == r2) {
            for (int i = c1; i <= c2; i++) {
                ans.add(matrix[r1][i]);
            }
        } else if (c1 == c2) {
            for (int i = r1; i <= r2; i++) {
                ans.add(matrix[i][c1]);
            }
        }
        return ans;
    }

    public int[] spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return new int[0];
        }
        int width = matrix[0].length;
        int height = matrix.length;
        int[] res = new int[height * width];
        int x = 0, y = 0;
        int dirX = 1, dirY = 0, level = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[y][x];
            int nextX = x + dirX;
            int nextY = y + dirY;
            if (dirX == 1 && nextX == width - level) {
                // 到达右边界向下转向
                dirX = 0;
                dirY = 1;
            } else if (dirX == -1 && nextX == level - 1) {
                // 到达左边界向上转向
                dirX = 0;
                dirY = -1;
            } else if (dirY == 1 && nextY == height - level) {
                // 到达下边界向左转向
                dirX = -1;
                dirY = 0;
            } else if (dirY == -1 && nextY == level) {
                // 到达上边界向右转向
                dirX = 1;
                dirY = 0;
                level++;
            }

            x += dirX;
            y += dirY;
        }
        return res;
    }

    @Test
    public void test() {
        int[][] matrix = {{3}, {2}};
        test(matrix);
    }

    public void test(int[][] matrix) {
        List<Integer> list = spiralOrder(matrix);
        System.out.println("order = " + list);
    }
}
