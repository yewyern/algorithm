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
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        return dfs(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0);
    }

    private boolean dfs(char[] s1, char[] s2, char[] s3, int i, int j, int k) {
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
            return dfs(s1, s2, s3, i + 1, j, k + 1) || dfs(s1, s2, s3, i, j + 1, k + 1);
        }
        if (startWithS1) {
            return dfs(s1, s2, s3, i + 1, j, k + 1);
        }
        if (startWithS2) {
            return dfs(s1, s2, s3, i, j + 1, k + 1);
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
