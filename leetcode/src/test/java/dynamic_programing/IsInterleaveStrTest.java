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
