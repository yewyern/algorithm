package dynamic_programing;

import org.junit.Test;

import java.util.Arrays;

/**
 * <a href='https://leetcode.cn/problems/maximal-square/'>221. 最大正方形</a>
 * @author xuzhou
 * @since 2024/1/17 22:19
 */
public class MaximalSquareTest {

    @Test
    public void test() {
        System.out.println(maximalSquare(new char[][]{
                {'0','1','1','0','1'},
                {'1','1','0','1','0'},
                {'0','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'0','0','0','0','0'}
        }));
    }


    public int maximalSquare(char[][] matrix) {
        // todo
        int n = matrix[0].length;
        int[] height = new int[n];
        int max = 0;
        for (char[] row : matrix) {
            int width = 1;
            int min = 1000;
            for (int j = 0; j < n; j++) {
                if (row[j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
                if (height[j] <= min && height[j] <= width) {
                    min = height[j];
                    max = Math.max(max, min);
                    width = 1;
                    min = 1000;
                } else {
                    max = Math.max(max, width);
                    print(height, j, width, min, max);
                    width++;
                }
            }
        }
        return max * max;
    }

    private void print(int[] height, int j, int width, int min, int max) {
        System.out.println("height = " + Arrays.toString(height) + ", j = " + j + ", width = " + width + ", min = " + min + ", max = " + max);
    }
}
