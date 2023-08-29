package data_structure.array;

import org.junit.Test;

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

    @Test
    public void test() {
        System.out.println(maxEqualRowsAfterFlips(new int[][]{{1,0,0,0,1,1,1,0,1,1,1}, {1,0,0,0,1,0,0,0,1,0,0}, {1,0,0,0,1,1,1,0,1,1,1},{1,0,0,0,1,0,0,0,1,0,0},{1,1,1,0,1,1,1,0,1,1,1}}));
    }

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (n == 1) {
            return 1;
        }
        if (m == 1) {
            return n;
        }
        int[] rows = IntStream.range(0, n).toArray();
        return process1(matrix, rows, 1, 0, n - 1);
    }

    private int process1(int[][] matrix, int[] rows, int i, int l, int r) {
        int n = r - l + 1;
        if (n < 2 || i == matrix[0].length) {
            return n;
        }
        // 只看变没变，不看0和1
        int notChange = l, change = r;
        while (notChange <= change) {
            while (notChange <= change && matrix[rows[notChange]][i] == matrix[rows[notChange]][i - 1]) {
                notChange++;
            }
            while (notChange <= change && matrix[rows[change]][i] == matrix[rows[change]][i - 1]) {
                notChange--;
            }
            if (notChange > change) {
                break;
            }
            swap(rows, notChange, change);
        }
        return Math.max(process1(matrix, rows, i + 1, l, notChange), process1(matrix, rows, i + 1, change, r));
    }

    public int maxEqualRowsAfterFlips1(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (n == 1) {
            return 1;
        }
        if (m == 1) {
            return n;
        }
        int[] rows = IntStream.range(0, n).toArray();
        return process(matrix, rows, 1, 0, n);
    }

    private int process(int[][] matrix, int[] rows, int i, int l, int r) {
        int n = r - l;
        if (n < 2 || i == matrix[0].length) {
            return n;
        }
        int[] res = new int[n];
        // 只看变没变，不看0和1
        int notChange = 0, change = n;
        for (int j = l; j < r; j++) {
            int row = rows[j];
            if (matrix[row][i] == matrix[row][i - 1]) {
                res[notChange++] = row;
            } else {
                res[--change] = row;
            }
        }
        return Math.max(process(matrix, res, i + 1, 0, change), process(matrix, res, i + 1, change, n));
    }

    public int maxEqualRowsAfterFlips2(int[][] matrix) {
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
        int max = 1;
        List<int[][]> list = new ArrayList<>(n);
        list.add(partition);
        for (int i = 1; i < m; i++) {
            List<int[][]> next = new ArrayList<>();
            for (int[][] parts : list) {
                if (parts[0].length == 0 && parts[1].length == 0 || parts[0].length == 0 && parts[1].length == 1 || parts[0].length == 1 && parts[1].length == 0) {
                    continue;
                }
                int[][] zeroPart = partition(matrix, i, parts[0]);
                int[][] onePart = partition(matrix, i, parts[1]);
                if (i < m - 1) {
                    next.add(new int[][]{zeroPart[0], onePart[1]});
                    next.add(new int[][]{onePart[0], zeroPart[1]});
                } else {
                    max = Math.max(max, zeroPart[0].length + onePart[1].length);
                    max = Math.max(max, onePart[0].length + zeroPart[1].length);
                }
            }
            list = next;
        }
        return max;
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
        return new int[][]{Arrays.copyOf(res, zero), Arrays.copyOfRange(res, one, n)};
    }

    private int[] reverse(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            swap(arr, start++, end--);
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
