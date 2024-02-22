package math;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/max-points-on-a-line/">149. 直线上最多的点数</a>
 *
 * @author xuzhou
 * @since 2024/2/22 10:17
 */
public class MaxPointsTest {

    @Test
    public void testMaxPoints() {
        // [[2,3],[3,3],[-5,3]]
        int[][] points = {{2, 3}, {3, 3}, {-5, 3}};
        System.out.println(maxPoints(points));
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3) {
            // 至少三个点，任意2点都可以构成直线
            return n;
        }
        int ans = 2;
        for (int i = 0; i < n; i++) {
            ans = getMax(points[i][0], points[i][1], points, i, n, ans);
        }
        return ans;
    }

    private static int getMax(int x, int y, int[][] points, int i, int n, int ans) {
        Map<Double/*j对i斜率*/, int[]/*相同斜率的个数*/> map = new HashMap<>();
        for (int j = i + 1; j < n; j++) {
            double k;
            if (x == points[j][0]) {
                k = 0;
            } else if (y == points[j][1]) {
                k = Double.POSITIVE_INFINITY;
            } else {
                k = (double) (points[j][1] - y) / (points[j][0] - x);
            }
            int[] a = map.get(k);
            if (a != null) {
                ans = Math.max(ans, ++a[0]);
            } else {
                map.put(k, new int[]{2});
            }
        }
        return ans;
    }

    public int maxPoints2(int[][] points) {
        int n = points.length;
        if (n < 3) {
            // 至少三个点，任意2点都可以构成直线
            return n;
        }
        int ans = 2;
        for (int i = 0; i < n; i++) {
            Map<Double/*j对i斜率*/, int[]/*相同斜率的个数*/> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                double k;
                if (points[i][0] == points[j][0]) {
                    k = 0;
                } else if (points[i][1] == points[j][1]) {
                    k = Double.POSITIVE_INFINITY;
                } else {
                    k = (double) (points[j][1] - points[i][1]) / (points[j][0] - points[i][0]);
                }
                int[] a = map.get(k);
                if (a != null) {
                    ans = Math.max(ans, ++a[0]);
                } else {
                    map.put(k, new int[]{2});
                }
            }
        }
        return ans;
    }

    public int maxPoints1(int[][] points) {
        int n = points.length;
        if (n < 3) {
            // 至少三个点，任意2点都可以构成直线
            return n;
        }
        // 暴力解法
        int ans = 2;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                int cnt = 2;
                for (int k = j + 1; k < n; k++) {
                    if (isOnLine(p1, p2, points[k])) {
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    private boolean isOnLine(int[] p1, int[] p2, int[] p3) {
        if (p1[0] == p2[0]) {
            return p1[0] == p3[0];
        }
        if (p1[0] == p3[0]) {
            return false;
        }
        if (p1[1] == p2[1]) {
            return p1[1] == p3[1];
        }
        if (p1[1] == p3[1]) {
            return false;
        }
        return (p3[0] - p1[0]) * (p2[1] - p1[1]) == (p3[1] - p1[1]) * (p2[0] - p1[0]);
    }

}
