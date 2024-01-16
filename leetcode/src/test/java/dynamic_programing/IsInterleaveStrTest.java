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
        // 记忆化搜索
        int m = s1.length();
        int n = s2.length();
        int k = s3.length();
        if (k != m + n) {
            return false;
        }
        byte[][][] cache = new byte[m + 1][n + 1][k + 1];
        return dfs(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0, cache);
    }

    private boolean dfs(char[] s1, char[] s2, char[] s3, int i, int j, int k, byte[][][] cache) {
        if (cache[i][j][k] != 0) {
            return cache[i][j][k] == 1;
        }
        boolean ans;
        if (k == s3.length) {
            ans = true;
        } else if (i == s1.length) {
            ans = equals(s2, s3, j, k);
        } else if (j == s2.length) {
            ans = equals(s1, s3, i, k);
        } else {
            boolean startWithS1 = s1[i] == s3[k];
            boolean startWithS2 = s2[j] == s3[k];
            if (startWithS1 && startWithS2) {
                ans = dfs(s1, s2, s3, i + 1, j, k + 1, cache) || dfs(s1, s2, s3, i, j + 1, k + 1, cache);
            } else if (startWithS1) {
                ans = dfs(s1, s2, s3, i + 1, j, k + 1, cache);
            } else if (startWithS2) {
                ans = dfs(s1, s2, s3, i, j + 1, k + 1, cache);
            } else {
                ans = false;
            }
        }
        cache[i][j][k] = (byte) (ans ? 1 : -1);
        return ans;
    }

    public boolean isInterleave3(String s1, String s2, String s3) {
        // 暴力递归
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        return dfs3(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0);
    }

    private boolean dfs3(char[] s1, char[] s2, char[] s3, int i, int j, int k) {
        if (k == s3.length) {
            return true;
        }
        if (i == s1.length) {
            return equals(s2, s3, j, k);
        }
        if (j == s2.length) {
            return equals(s1, s3, i, k);
        }
        boolean startWithS1 = s1[i] == s3[k];
        boolean startWithS2 = s2[j] == s3[k];
        if (startWithS1 && startWithS2) {
            return dfs3(s1, s2, s3, i + 1, j, k + 1) || dfs3(s1, s2, s3, i, j + 1, k + 1);
        }
        if (startWithS1) {
            return dfs3(s1, s2, s3, i + 1, j, k + 1);
        }
        if (startWithS2) {
            return dfs3(s1, s2, s3, i, j + 1, k + 1);
        }
        return false;
    }

    private boolean equals(char[] a, char[] b, int i, int j) {
        while (i < a.length && j < b.length && a[i] == b[j]) {
            i++;
            j++;
        }
        return j == b.length;
    }
}
