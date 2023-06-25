package search;

import data_structure.array.matrix.MatrixGenerator;
import org.junit.Test;
import utils.RandomUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/">240. 搜索二维矩阵 II</a>
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * 示例 1：
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid2.jpg"/>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * <p>
 * 示例 2：
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid.jpg"/>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * <p>m == matrix.length
 * <p>n == matrix[i].length
 * <p>1 <= n, m <= 300
 * <p>-10^9 <= matrix[i][j] <= 10^9
 * <p>每行的所有元素从左到右升序排列
 * <p>每列的所有元素从上到下升序排列
 * <p>-10^9 <= target <= 10^9
 * <a href="https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 04. 二维数组中的查找</a>与本题相同
 *
 * @author xuzhou
 * @since 2023/6/21 10:25
 */
public class SearchMatrixTest {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 因为该矩阵具有每行中的整数从左到右按升序排列。每列中的整数从上到下按升序排列的特点，所以我们从矩阵右上角节点开始判断。
        // 比较右上角的点matrix[i][j]和target，这里设右上角的点坐标为matrix[i][j]。
        // 若matrix[i][j]等于target，说明已经找到，返回true。
        // 若matrix[i][j]小于target，说明该点所在的行的值都小于target，因此可以排除该行，i++。
        // 若matrix[i][j]大于target，说明该点所在的列的值都大于target，因此可以排除该列，j–。
        // 重复步骤2-4，直到i,j的下标越界或者找到目标值。
        // 时间复杂度：O(m+n)
        // 空间复杂度：O(1)
        int M = matrix.length, N = matrix[0].length;
        int i = 0, j = N - 1;
        while (i < M && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                // 第j列必大于该数
                j--;
            } else {
                // 第i行必小于该数
                i++;
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        // 递归解法
        return searchMatrix(matrix, target, 0, 0, matrix[0].length - 1, matrix.length - 1);
    }

    public boolean searchMatrix(int[][] matrix, int target, int x1, int y1, int x2, int y2) {
        if (x1 < x2 && y1 < y2) {
            int xm = x1 + ((x2 - x1) >> 1);
            int ym = y1 + ((y2 - y1) >> 1);
            if (matrix[ym][xm] == target) {
                return true;
            } else if (matrix[ym][xm] < target) {
                return searchMatrix(matrix, target, xm + 1, y1, x2, ym)
                        || searchMatrix(matrix, target, x1, ym + 1, x2, y2);
            } else {
                return searchMatrix(matrix, target, x1, y1, x2, ym - 1)
                        || searchMatrix(matrix, target, x1, ym, xm - 1, y2);
            }
        }
        if (x1 == x2) {
            return binarySearch(matrix, target, x1, y1, y2);
        }
        if (y1 == y2) {
            return Arrays.binarySearch(matrix[y1], x1, x2 + 1, target) >= 0;
        }
        return false;
    }

    public boolean binarySearch(int[][] matrix, int target, int col, int low, int high) {
        while (low <= high) {
            int m = low + ((high - low) >> 1);
            if (matrix[m][col] == target) {
                return true;
            } else if (matrix[m][col] < target) {
                low = m + 1;
            } else {
                high = m - 1;
            }
        }
        return false;
    }


    public boolean searchMatrixComparison(int[][] matrix, int target) {
        for (int[] nums : matrix) {
            if (binarySearch(nums, target)) {
                return true;
            }
        }
        return false;
    }

    public boolean binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] == target) {
                return true;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }

    public boolean searchMatrixComparison2(int[][] matrix, int target) {
        for (int[] nums : matrix) {
            for (int x : nums) {
                if (x == target) {
                    return true;
                }
            }
        }
        return false;
    }


    @Test
    public void test() {
        int min = -100, max = 100, tests = 100000;
        for (int i = 0; i < tests; i++) {
            int[][] matrix = MatrixGenerator.generateNoRepeatSortedMatrix(RandomUtils.nextInt(1, 10), RandomUtils.nextInt(1, 10), min, max);
            int target = RandomUtils.nextInt(min, max);
            CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> searchMatrix(matrix, target));
            Boolean searched;
            try {
                searched = future.get(3, TimeUnit.SECONDS);
            } catch (Exception e) {
                searchMatrix(matrix, target);
                System.out.println("target = " + target);
                break;
            }
            boolean searched2 = searchMatrixComparison(matrix, target);
            if (searched != searched2) {
                printMatrix(matrix);
                System.out.println("target = " + target);
                System.out.println("searched = " + searched);
                searchMatrix(matrix, target);
                break;
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] nums : matrix) {
            for (int i : nums) {
                System.out.format("%10d", i);
            }
            System.out.println();
        }
    }
}
