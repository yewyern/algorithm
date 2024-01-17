package dynamic_programing;

/**
 * <a href="https://leetcode.cn/problems/edit-distance">72. 编辑距离</a>
 * @author xuzhou
 * @since 2024/1/17 21:07
 */
public class EditDistanceTest {

    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        return minDistance(word1.toCharArray(), word2.toCharArray());
    }

    public int minDistance(char[] s1, char[] s2) {
        // dp优化
        int m = s1.length;
        int n = s2.length;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i] = n - i;
        }
        for (int i = m - 1; i >= 0; i--) {
            int last = dp[n];
            dp[n] = m - i;
            for (int j = n - 1; j >= 0; j--) {
                int p1 = last + (s1[i] == s2[j] ? 0 : 1);
                int p2 = dp[j] + 1;
                int p3 = dp[j + 1] + 1;
                last = dp[j];
                dp[j] = Math.min(p1, Math.min(p2, p3));
            }
        }
        return dp[0];
    }


    public int minDistance0(char[] s1, char[] s2) {
        // dp
        int m = s1.length;
        int n = s2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < n; i++) {
            dp[m][i] = n - i;
        }
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = m - i;
            for (int j = n - 1; j >= 0; j--) {
                int p1 = dp[i + 1][j + 1] + (s1[i] == s2[j] ? 0 : 1);
                int p2 = dp[i + 1][j] + 1;
                int p3 = dp[i][j + 1] + 1;
                dp[i][j] = Math.min(p1, Math.min(p2, p3));
            }
        }
        return dp[0][0];
    }


    public int minDistance1(char[] s1, char[] s2) {
        // 记忆化搜索
        int[][] cache = new int[s1.length + 1][s2.length + 1];
        return dfs(s1, s2, 0, 0, cache);
    }

    private int dfs(char[] s1, char[] s2, int i, int j, int[][] cache) {
        if (cache[i][j] > 0) {
            return cache[i][j];
        }
        int ans;
        if (j == s2.length) {
            ans = s1.length - i;
        } else if (i == s1.length) {
            ans = s2.length - j;
        } else {
            int p1 = dfs(s1, s2, i + 1, j + 1, cache) + (s1[i] == s2[j] ? 0 : 1);
            int p2 = dfs(s1, s2, i + 1, j, cache) + 1;
            int p3 = dfs(s1, s2, i, j + 1, cache) + 1;
            ans = Math.min(p1, Math.min(p2, p3));
        }
        cache[i][j] = ans;
        return ans;
    }

    public int minDistance2(char[] s1, char[] s2) {
        // 暴力递归
        return dfs2(s1, s2, 0, 0);
    }

    private int dfs2(char[] s1, char[] s2, int i, int j) {
        int ans;
        if (j == s2.length) {
            ans = s1.length - i;
        } else if (i == s1.length) {
            ans = s2.length - j;
        } else {
            int p1 = dfs2(s1, s2, i + 1, j + 1) + (s1[i] == s2[j] ? 0 : 1);
            int p2 = dfs2(s1, s2, i + 1, j) + 1;
            int p3 = dfs2(s1, s2, i, j + 1) + 1;
            ans = Math.min(p1, Math.min(p2, p3));
        }
        return ans;
    }
}
