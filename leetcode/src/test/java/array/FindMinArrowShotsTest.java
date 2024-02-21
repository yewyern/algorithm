package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons">452. 用最少数量的箭引爆气球</a>
 * @author xuzhou
 * @since 2024/2/21 9:54
 */
public class FindMinArrowShotsTest {

    @Test
    public void test() {
        // [[-2147483646,-2147483645],[2147483646,2147483647]]
        int[][] points = new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}};
        System.out.println(findMinArrowShots(points));
    }

    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if (n < 2) {
            return n;
        }
        Arrays.sort(points, this::compare);
        int start = points[0][0], end = points[0][1];
        int count = 1;
        for (int[] point : points) {
            int left = point[0];
            int right = point[1];
            if (left > end) {
                start = left;
                end = right;
                count++;
            } else {
                start = Math.max(start, left);
                end = Math.min(end, right);
            }
        }
        return count;
    }

    private int compare(int[] p1, int[] p2) {
        if (p1[1] == p2[1]) {
            return p1[0] <= p2[0] ? -1 : 1;
        }
        return p1[1] <= p2[1] ? -1 : 1;
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
