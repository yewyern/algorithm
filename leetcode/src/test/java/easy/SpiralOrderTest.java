package easy;

import java.util.Arrays;
import org.junit.Test;

/**
 * @author zhou.xu
 * @since 2020/9/14 16:13
 */
public class SpiralOrderTest {

    public int[] spiralOrder(int[][] matrix) {
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
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        test(matrix);
        int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        test(matrix2);
    }

    public void test(int[][] matrix) {
        int[] order = spiralOrder(matrix);
        System.out.println("order = " + Arrays.toString(order));
    }
}
