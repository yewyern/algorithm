package dfs;

import org.junit.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/description/">剑指 Offer 38. 字符串的排列</a>
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * <p>
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 *
 * @author xuzhou
 * @since 2023/8/16 16:33
 */
public class PermutationStrTest {

    public String[] permutation(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        List<String> res = new ArrayList<>();
        boolean[] mark = new boolean[cs.length];
        Arrays.fill(mark, true);
        process(res, cs, new char[cs.length], 0, mark);
        return res.toArray(new String[0]);
    }

    private void process(List<String> res, char[] s, char[] ans, int index, boolean[] mark) {
        if (index == s.length) {
            res.add(String.valueOf(ans));
            return;
        }
        for (int i = 0; i < s.length; i++) {
            if (i > 0 && s[i - 1] == s[i] && mark[i - 1]) {
                continue;
            }
            if (mark[i]) {
                ans[index] = s[i];
                mark[i] = false;
                process(res, s, ans, index + 1, mark);
                mark[i] = true;
            }
        }
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(permutation("aba")));
    }
}
