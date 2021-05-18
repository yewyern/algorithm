package string;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * <p>给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: "abcabcbb"
 * <p>输出: 3
 * <p>解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>示例 2:
 * <p>
 * <p>输入: "bbbbb"
 * <p>输出: 1
 * <p>解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>示例 3:
 * <p>
 * <p>输入: "pwwkew"
 * <p>输出: 3
 * <p>解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * <p>     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author zhou.xu
 * @date 2020/8/5 10:14
 */
public class LongestSubstringLength {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        Set<Character> occ = new HashSet<>();
        int end = 0, max = 1;
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (i > 0) {
                occ.remove(cs[i - 1]);
            }
            while (end < cs.length && !occ.contains(cs[end])) {
                occ.add(cs[end]);
                end++;
            }
            max = Math.max(max, end - i);
        }
        return max;
    }

    private void test(String s) {
        System.out.println("s = " + s);
        System.out.println("l = " + lengthOfLongestSubstring(s));
    }

    @Test
    public void test() {
        test("abcabcbb");
        test("bbbbb");
        test("pwwkew");
        test("abcdefg");
    }
}
