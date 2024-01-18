package dynamic_programing;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href='https://leetcode.cn/problems/maximal-square/'>221. 最大正方形</a>
 * @author xuzhou
 * @since 2024/1/17 22:19
 */
public class MaximalSquareTest {

    @Test
    public void test() {
        System.out.println(maximalSquare(new char[][]{
                {'1','0','1','0','0','1','1','1','0'},
                {'1','1','1','0','0','0','0','0','1'},
                {'0','0','1','1','0','0','0','1','1'},
                {'0','1','1','0','0','1','0','0','1'},
                {'1','1','0','1','1','0','0','1','0'},
                {'0','1','1','1','1','1','1','0','1'},
                {'1','0','1','1','1','0','0','1','0'},
                {'1','1','1','0','1','0','0','0','1'},
                {'0','1','1','1','1','0','0','1','0'},
                {'1','0','0','1','1','1','0','0','0'}
        }));
    }


    public int maximalSquare(char[][] matrix) {
        int n = matrix[0].length;
        int[] height = new int[n];
        int[] min = new int[n];
        int max = 0;
        for (char[] row : matrix) {
            int width = 1;
            int l = 0, r = 0;
            for (int j = 0; j < n; j++) {
                if (row[j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
                // 最小队列
                while (r > l && height[min[r - 1]] > height[j]) {
                    r--;
                }
                min[r++] = j;
                if (height[min[l]] <= width) {
                    int i = min[l++];
                    max = Math.max(max, height[i]);
                    width = j - i;
                } else {
                    max = Math.max(max, width);
                }
                width++;
            }
        }
        return max * max;
    }

    private void print(int[] height, int j, int width, int max) {
        System.out.println("height = " + Arrays.toString(height) + ", j = " + j + ", width = " + width + ", max = " + max);
    }
}
