package search.binary;


/**
 * <a href="https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix/">1351. 统计有序矩阵中的负数</a>
 *
 * @author zhou.xu
 * @since 2024/1/20 14:27
 */
public class CountNegativesInSortMatrixTest {

    public int countNegatives(int[][] grid) {
        // 二分优化，由下往上，O(M+log(N))
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        int l = 0;
        for (int i = m - 1; i >= 0; i--) {
            int r = n - 1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (grid[i][mid] >= 0) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (l == n) {
                break;
            }
            count += n - l;
        }
        return count;
    }

    public int countNegatives2(int[][] grid) {
        // 普通遍历，由下往上，O(M+N)
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        int l = 0;
        for (int i = m - 1; i >= 0; i--) {
            while (l < n && grid[i][l] >= 0) {
                l++;
            }
            if (l == n) {
                break;
            }
            count += n - l;
        }
        return count;
    }

}
