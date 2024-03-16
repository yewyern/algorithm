package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons">452. 用最少数量的箭引爆气球</a>
 *
 * @author xuzhou
 * @since 2024/2/21 9:54
 */
public class FindMinArrowShotsTest {

    @Test
    public void test() {
        // [[-2147483646,-2147483645],[2147483646,2147483647]]
        int[][] points = new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}};
        System.out.println(findMinArrowShots(points));
    }

    public int findMinArrowShots(int[][] points) {
        if (points.length <= 1) {
            return points.length;
        }
        quickSort(points, 0, points.length - 1);
        int result = 1;
        int preEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > preEnd) {
                result++;
                preEnd = points[i][1];
            } else if (points[i][1] < preEnd) {
                preEnd = points[i][1];
            }
        }
        return result;
    }

    private static void quickSort(int[][] points, int left, int right) {
        if (left < right) {
            int l = left - 1, r = right + 1, base = points[left + right >> 1][0];
            while (l < r) {
                while (points[++l][0] < base) ;
                while (points[--r][0] > base) ;
                if (l < r) {
                    int[] temp = points[l];
                    points[l] = points[r];
                    points[r] = temp;
                }
            }
            quickSort(points, left, r);
            quickSort(points, r + 1, right);
        }
    }

    public int findMinArrowShots2(int[][] points) {
        int n = points.length;
        if (n < 2) {
            return n;
        }
        Arrays.sort(points, Comparator.comparing(i -> i[0]));
        int end = points[0][1];
        int count = 1;
        for (int[] point : points) {
            if (point[0] > end) {
                end = point[1];
                count++;
            } else {
                end = Math.min(end, point[1]);
            }
        }
        return count;
    }

    public int findMinArrowShots1(int[][] points) {
        int n = points.length;
        boolean[] shots = new boolean[n]; // 标记是否被箭引爆
        Integer[] leftBoundIndexes = new Integer[n]; // 排序左边界
        Integer[] rightBoundIndexes = new Integer[n]; // 排序右边界
        for (int i = 0; i < n; i++) {
            leftBoundIndexes[i] = i;
            rightBoundIndexes[i] = i;
        }
        Arrays.sort(leftBoundIndexes, Comparator.comparingInt(i -> points[i][0]));
        Arrays.sort(rightBoundIndexes, Comparator.comparingInt(i -> points[i][1]));
        int count = 0;
        // 根据右边界向右尝试
        for (int i = 0, j = 0; i < n; i++) {
            Integer rightBoundIndex = rightBoundIndexes[i];
            if (shots[rightBoundIndex]) {
                continue;
            }
            count++;
            int rightBound = points[rightBoundIndex][1];
            while (j < n && points[leftBoundIndexes[j]][0] <= rightBound) {
                if (!shots[leftBoundIndexes[j]]) {
                    shots[leftBoundIndexes[j]] = true;
                }
                j++;
            }
        }
        return count;
    }
}
