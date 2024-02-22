package math;

/**
 * <a href="https://leetcode.cn/problems/max-points-on-a-line/">149. 直线上最多的点数</a>
 *
 * @author xuzhou
 * @since 2024/2/22 10:17
 */
public class MaxPointsTest {

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3) {
            // 至少三个点，任意2点都可以构成直线
            return n;
        }
        int ans = 2;
        boolean[][] map = new boolean[n][n];
        int[] queue = new int[n];
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (map[i][j]) {
                    continue;
                }
                int[] p1 = points[i];
                int[] p2 = points[j];
                int size = 0;
                queue[size++] = i;
                queue[size++] = j;
                for (int k = j + 1; k < n; k++) {
                    if (isOnLine(p1, p2, points[k])) {
                        queue[size++] = k;
                    }
                }
                if (size > 2) {
                    for(int k = 0; k < size; k++) {
                        int index = queue[k];
                        for (int l = k + 1; l < size; l++) {
                            int index2 = queue[l];
                            map[index][index2] = true;
                        }
                    }
                }
                ans = Math.max(ans, size);
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

}
