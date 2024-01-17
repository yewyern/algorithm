package dynamic_programing;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/interleaving-string/">97. 交错字符串</a>
 * @author xuzhou
 * @since 2024/1/15 16:06
 */
public class IsInterleaveStrTest {

    @Test
    public void test() {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        // 动态规划
        return dp(s1.toCharArray(), s2.toCharArray(), s3.toCharArray());
    }

    private boolean dp(char[] s1, char[] s2, char[] s3) {
        int m = s1.length;
        int n = s2.length;
        int s = s3.length;
        if (s != m + n) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int i = n - 1; i >= 0 && s2[i] == s3[i + m]; i--) {
            dp[i] = true;
        }
        for (int i = m - 1; i >= 0; i--) {
            boolean flag = s1[i] == s3[i + n];
            dp[n] = flag;
            for (int j = n - 1; j >= 0; j--) {
                int k = i + j;
                dp[j] = s1[i] == s3[k] && dp[j];
                if (!dp[j]) {
                    dp[j] = s2[j] == s3[k] && dp[j + 1];
                }
                flag = flag || dp[j];
            }
            if (!flag) {
                return false;
            }
        }
        return dp[0];
    }

    private boolean dp1(char[] s1, char[] s2, char[] s3) {
        int m = s1.length;
        int n = s2.length;
        int s = s3.length;
        if (s != m + n) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[m][n] = true;
        for (int i = n - 1; i >= 0 && s2[i] == s3[i + m]; i--) {
            dp[m][i] = true;
        }
        for (int i = m - 1; i >= 0; i--) {
            boolean flag = s1[i] == s3[i + n];
            dp[i][n] = flag;
            for (int j = n - 1; j >= 0; j--) {
                int k = i + j;
                dp[i][j] = s1[i] == s3[k] && dp[i + 1][j];
                if (!dp[i][j]) {
                    dp[i][j] = s2[j] == s3[k] && dp[i][j + 1];
                }
                flag = flag || dp[i][j];
            }
            if (!flag) {
                return false;
            }
        }
        return dp[0][0];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        // 记忆化搜索
        int m = s1.length();
        int n = s2.length();
        int k = s3.length();
        if (k != m + n) {
            return false;
        }
        byte[][] cache = new byte[m + 1][n + 1];
        return dfs(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, cache);
    }

    private boolean dfs(char[] s1, char[] s2, char[] s3, int i, int j, byte[][] cache) {
        if (cache[i][j] != 0) {
            return cache[i][j] == 1;
        }
        boolean ans;
        if (i == s1.length && j == s2.length) {
            ans = true;
        } else {
            int k = i + j;
            ans = i < s1.length && s1[i] == s3[k] && dfs(s1, s2, s3, i + 1, j, cache);
            if (!ans) {
                ans = j < s2.length && s2[j] == s3[k] && dfs(s1, s2, s3, i, j + 1, cache);
            }
        }
        cache[i][j] = (byte) (ans ? 1 : -1);
        return ans;
    }

    public boolean isInterleave3(String s1, String s2, String s3) {
        // 暴力递归
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        return dfs3(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0);
    }

    private boolean dfs3(char[] s1, char[] s2, char[] s3, int i, int j) {
        if (i == s1.length && j == s2.length) {
            return true;
        }
        int k = i + j;
        boolean ans = i < s1.length && s1[i] == s3[k] && dfs3(s1, s2, s3, i + 1, j);
        if (ans) {
            return true;
        }
        return j < s2.length && s2[j] == s3[k] && dfs3(s1, s2, s3, i, j + 1);
    }

}
