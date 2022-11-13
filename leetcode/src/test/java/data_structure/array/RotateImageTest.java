package data_structure.array;

import java.util.Arrays;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/rotate-image/">48. 旋转图像</a>
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *
 *
 * 示例 1：
 * <img src="https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg">...</img>
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 * <img src="https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg">...</img>
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 *
 * 提示：
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 * @author xuzhou
 * @since 2022/11/13 17:04
 */
public class RotateImageTest {

    public void rotate(int[][] matrix) {
        // 0,1  1,3, 3,2, 2,0 (i,j)(j,n-i-1)
        //3 00-01
        //4 00-11
        //5 00-12
        //6 00-22
        int n = matrix.length;
        int maxX = n >> 1;
        int maxY = (n + 1) >> 1;
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                int y1 = n - i - 1;
                int y2 = n - j - 1;
                int y3 = n - y1 - 1;
                int t = matrix[i][j];
                matrix[i][j] = matrix[y2][y3];
                matrix[y2][y3] = matrix[y1][y2];
                matrix[y1][y2] = matrix[j][y1];
                matrix[j][y1] = t;
            }
        }
    }

    public void print(int[][] matrix) {
        for (int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void test() {
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix1);
        print(matrix1);
        int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        rotate(matrix2);
        print(matrix2);
    }
}
