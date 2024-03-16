package search;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/search-a-2d-matrix/">74. 搜索二维矩阵</a>
 *
 * @author xuzhou
 * @since 2024/1/8 11:20
 */
public class SearchMatrixTest {

    @Test
    public void test() {
        System.out.println(searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        return processRow(matrix, target, 0, matrix.length - 1);
    }

    private boolean processRow(int[][] matrix, int target, int r0, int r1) {
        int n = matrix[0].length;
        if (matrix[r0][0] > target || matrix[r1][n - 1] < target) {
            return false;
        }
        if (r0 == r1) {
            return binarySearch(matrix[r0], target);
        }
        int mid = (r0 + r1) >> 1;
        return processRow(matrix, target, r0, mid) || processRow(matrix, target, mid + 1, r1);
    }

    private boolean binarySearch(int[] arr, int target) {
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (arr[m] == target) {
                return true;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }
}
