package data_structure.array;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <a href="https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows/">1072. 按列翻转得到最大值等行数</a>
 * 给定 m x n 矩阵 matrix 。
 * <p>
 * 你可以从中选出任意数量的列并翻转其上的 每个 单元格。（即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。）
 * <p>
 * 返回 经过一些翻转后，行内所有值都相等的最大行数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[0,1],[1,1]]
 * 输出：1
 * 解释：不进行翻转，有 1 行所有值都相等。
 * 示例 2：
 * <p>
 * 输入：matrix = [[0,1],[1,0]]
 * 输出：2
 * 解释：翻转第一列的值之后，这两行都由相等的值组成。
 * 示例 3：
 * <p>
 * 输入：matrix = [[0,0,0],[0,0,1],[1,1,0]]
 * 输出：2
 * 解释：翻转前两列的值之后，后两行由相等的值组成。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] == 0 或 1
 *
 * @author xuzhou
 * @since 2023/8/25 17:01
 */
public class MaxEqualRowsAfterFlipsTest {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        // todo
        int n = matrix.length;
        int m = matrix[0].length;
        if (n == 1) {
            return 1;
        }
        if (m == 1) {
            return n;
        }
        // 先给第一列分组
        int[] rows = IntStream.range(0, n).toArray();
        int[][] partition = partition(matrix, 0, rows);
        Queue<int[][]> queue = new LinkedList<>();
        queue.offer(partition);
        for (int i = 1; i < m; i++) {
            Queue<int[][]> next = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[][] poll = queue.poll();
                if (poll[0].length == 0 && poll[1].length == 0) {
                    continue;
                }
                int[][] zeroPart = partition(matrix, i, poll[0]);
                int[][] onePart = partition(matrix, i, poll[1]);
                if (i < m - 1) {
                    queue.add(new int[][]{zeroPart[0], onePart[1]});
                    queue.add(new int[][]{onePart[0], zeroPart[1]});
                } else {

                }
            }
            queue = next;
        }
        return 1;
    }

    private int[][] partition(int[][] matrix, int col, int[] rows) {
        int n = rows.length;
        if (n == 0) {
            return new int[2][0];
        }
        int[] res = new int[n];
        int zero = 0, one = n;
        for (int row : rows) {
            if (matrix[row][col] == 0) {
                res[zero++] = row;
            } else {
                res[--one] = row;
            }
        }
        return new int[][] {Arrays.copyOf(res, zero), Arrays.copyOfRange(res, one, n)};
    }

    private void reverse(int[] arr) {

    }
}
