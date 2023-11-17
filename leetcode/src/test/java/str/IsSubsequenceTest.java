package str;


/**
 * <a href="https://leetcode.cn/problems/is-subsequence/">392. 判断子序列</a>
 * @author xuzhou
 * @since 2023/11/17 11:19
 */
public class IsSubsequenceTest {

    public boolean isSubsequence(String s, String t) {
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int m = str.length, n = target.length;
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (str[i] == target[j]) {
                i++;
            }
            j++;
        }
        return i == m;
    }

    public boolean isSubsequence2(String s, String t) {
        return process(s.toCharArray(), t.toCharArray(), 0, 0);
    }

    private boolean process(char[] s, char[] t, int i, int j) {
        if (i == s.length) {
            return true;
        }
        if (j == t.length) {
            return false;
        }
        return s[i] == t[j] ? process(s, t, i + 1, j + 1) : process(s, t, i, j + 1);
    }

}
