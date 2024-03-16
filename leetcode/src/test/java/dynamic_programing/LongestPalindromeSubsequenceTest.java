package dynamic_programing;

/**
 * <a href="https://leetcode.cn/problems/longest-palindromic-subsequence/">516. 最长回文子序列</a>
 *
 * @author zhou.xu
 * @since 2023/11/26 17:33
 */
public class LongestPalindromeSubsequenceTest {

    public int longestPalindromeSubseq(String s) {
        char[] str = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                if (str[l] == str[r]) {
                    dp[l][r] = Math.max(dp[l][r], dp[l + 1][r - 1] + 2);
                }
            }
        }
        return dp[0][n - 1];
    }

    public int longestPalindromeSubseq2(String s) {
        return process(s.toCharArray(), 0, s.length() - 1);
    }

    private int process(char[] str, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return str[l] == str[r] ? 2 : 1;
        }
        int p1 = process(str, l + 1, r);
        int p2 = process(str, l, r - 1);
        int p3 = process(str, l + 1, r - 1);
        int p4 = str[l] == str[r] ? 2 + p3 : p3;
        return Math.max(Math.max(p1, p2), p4);
    }
}
