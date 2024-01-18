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
        LinkedList<Integer> min = new LinkedList<>();
        int max = 0;
        for (char[] row : matrix) {
            int width = 1;
            for (int j = 0; j < n; j++) {
                if (row[j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
                // 最小队列
                while (!min.isEmpty() && height[min.peekLast()] > height[j]) {
                    min.pollLast();
                }
                min.addLast(j);
                if (height[min.peekFirst()] <= width) {
                    Integer i = min.pollFirst();
                    max = Math.max(max, height[i]);
                    width = j - i;
                } else {
                    max = Math.max(max, width);
                }
                width++;
            }
            min.clear();
        }
        return max * max;
    }

    private void print(int[] height, int j, int width, int max) {
        System.out.println("height = " + Arrays.toString(height) + ", j = " + j + ", width = " + width + ", max = " + max);
    }
}
