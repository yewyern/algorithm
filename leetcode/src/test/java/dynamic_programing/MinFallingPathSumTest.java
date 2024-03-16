package dynamic_programing;

import org.junit.Test;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/minimum-falling-path-sum/description/">931. 下降路径最小和</a>
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * <p>
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 *
 * @author zhou.xu
 * @since 2023/7/13 23:13
 */
public class MinFallingPathSumTest {

    @Test
    public void test() {
        int sum = minFallingPathSum(new int[][]{
                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9},
        });
        System.out.println("sum = " + sum);
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        // 用滑动窗口，窗口长度为3
        LinkedList<Integer> queue = new LinkedList<>();
        int[] last = matrix[0];
        int min = 0;
        for (int i = 1; i < n; i++) {
            queue.add(0);
            min = matrix[i][0] + last[0];
            for (int j = 0; j < n; j++) {
                while (j < n - 1 && !queue.isEmpty() && last[queue.peekLast()] > last[j + 1]) {
                    queue.pollLast();
                }
                if (j < n - 1) {
                    queue.add(j + 1);
                }
                matrix[i][j] += last[queue.peekFirst()];
                min = Math.min(min, matrix[i][j]);
                if (queue.peekFirst() == j - 1) {
                    queue.pollFirst();
                }
            }
            queue.clear();
            last = matrix[i];
        }
        return min;
    }

    public int minFallingPathSum2(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        int[] last = matrix[0];
        int min = 0;
        for (int i = 1; i < n; i++) {
            matrix[i][0] += Math.min(last[0], last[1]);
            min = matrix[i][0];
            for (int j = 1; j < n - 1; j++) {
                matrix[i][j] += Math.min(Math.min(last[j - 1], last[j]), last[j + 1]);
                min = Math.min(min, matrix[i][j]);
            }
            matrix[i][n - 1] += Math.min(last[n - 1], last[n - 2]);
            min = Math.min(min, matrix[i][n - 1]);
            last = matrix[i];
        }
        return min;
    }
}
