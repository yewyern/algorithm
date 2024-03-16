package dynamic_programing;

import java.util.Arrays;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/largest-plus-sign/">764. 最大加号标志</a>
 *
 * @author xuzhou
 * @since 2022/11/9 11:32
 */
public class LargestPlusSignTest {

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int size = n * n;
        if (size == mines.length) {
            return 0;
        }
        if (size == mines.length + 1) {
            return 1;
        }
        int[][] res = initSquareAndFill(n, n);
        int[][] square = initSquare(n, mines);
        int max = 1;
//        printSquare(square);
//        System.out.println("----------");
        // 从上往下
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (square[i][j] > 0) {
                    square[i][j] += square[i - 1][j];
                    res[i][j] = min(res[i][j], square[i][j]);
                }
            }
        }
        square = initSquare(n, mines);
        // 从下往上
        for (int i = n - 2; i > 0; i--) {
            for (int j = 1; j < n - 1; j++) {
                if (square[i][j] > 0) {
                    square[i][j] += square[i + 1][j];
                    res[i][j] = min(res[i][j], square[i][j]);
                }
            }
        }
        square = initSquare(n, mines);
        // 从左往右
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (square[i][j] > 0) {
                    square[i][j] += square[i][j - 1];
                    res[i][j] = min(res[i][j], square[i][j]);
                }
            }
        }
        square = initSquare(n, mines);
        // 从右往左
        for (int i = 1; i < n - 1; i++) {
            for (int j = n - 2; j > 0; j--) {
                if (square[i][j] > 0) {
                    square[i][j] += square[i][j + 1];
                    res[i][j] = min(res[i][j], square[i][j]);
                    max = Math.max(res[i][j], max);
                }
            }
        }
//        printSquare(res);
//        System.out.println("----------");
        return max;
    }

    private static int[][] initSquare(int n, int[][] mines) {
        int[][] square = initSquareAndFill(n, 1);
        for (int[] mine : mines) {
            square[mine[0]][mine[1]] = 0;
        }
        return square;
    }

    private static int[][] initSquareAndFill(int n, int fill) {
        int[][] square = new int[n][];
        for (int i = 0; i < n; i++) {
            square[i] = new int[n];
            Arrays.fill(square[i], fill);
        }
        return square;
    }


    private static int min(int... a) {
        int min = Integer.MAX_VALUE;
        for (int i : a) {
            min = Math.min(min, i);
        }
        return min;
    }

    private static void printSquare(int[][] square) {
        for (int[] nums : square) {
            for (int j = 0; j < nums.length; j++) {
                System.out.print(nums[j] + " ");
            }
            System.out.println();
        }

    }

    @Test
    public void test() {
        System.out.println(orderOfLargestPlusSign(5, new int[][]{{3, 0}, {3, 3}}));
        System.out.println(orderOfLargestPlusSign(5, new int[][]{{4, 2}}));
        System.out.println(orderOfLargestPlusSign(1, new int[][]{{0, 0}}));
        System.out.println(orderOfLargestPlusSign(5, new int[][]{{0, 0}}));
        System.out.println(orderOfLargestPlusSign(5,
            new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {2, 0}, {2, 1}, {2, 3}, {2, 4}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {4, 0}, {4, 1},
                {4, 2}, {4, 3}, {4, 4}}));
        System.out.println(orderOfLargestPlusSign(3, new int[][]{{0, 0}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 2}}));
    }
}
