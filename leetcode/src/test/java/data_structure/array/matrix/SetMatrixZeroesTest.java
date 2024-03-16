package data_structure.array.matrix;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/set-matrix-zeroes">73. 矩阵置零</a>
 * @author xuzhou
 * @since 2023/12/21 16:34
 */
public class SetMatrixZeroesTest {

    public void setZeroes(int[][] matrix) {
        // O(1)空间，递归方式，存在重复操作
        process(matrix, 0, 0);
    }

    private void process(int[][] matrix, int row, int col) {
        if (row == matrix.length) {
            return;
        }
        if (col == matrix[row].length) {
            process(matrix, row + 1, 0);
            return;
        }
        boolean isZero = matrix[row][col] == 0;
        process(matrix, row, col + 1);
        if (isZero) {
            fillRow(matrix, row);
            fillCol(matrix, col);
        }
    }

    public void setZeroes2(int[][] matrix) {
        // O（m + n)
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] zeroRows = new boolean[m];
        boolean[] zeroCols = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (zeroRows[i]) {
                fillRow(matrix, i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (zeroCols[i]) {
                fillCol(matrix, i);
            }
        }
    }

    private void fillRow(int[][] matrix, int row) {
        Arrays.fill(matrix[row], 0);
    }

    private void fillCol(int[][] matrix, int col) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            matrix[i][col] = 0;
        }
    }
}
