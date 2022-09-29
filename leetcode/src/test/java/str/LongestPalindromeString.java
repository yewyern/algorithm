package str;

import java.util.Arrays;
import org.junit.Test;

/**
 * <p>给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * <p>示例 1：
 * <p>
 * <p>输入: "babad"
 * <p>输出: "bab"
 * <p>注意: "aba" 也是一个有效答案。
 * <p>示例 2：
 * <p>
 * <p>输入: "cbbd"
 * <p>输出: "bb"
 *
 * @author zhou.xu
 * @since 2020/8/5 11:02
 */
public class LongestPalindromeString {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        char[] cs = s.toCharArray();
        int start = 0, end = 0;
        for (int i = 0; i < cs.length - (end - start) / 2; i++) {
            int len1 = expandAroundCenter(cs, i, i);
            int len2 = expandAroundCenter(cs, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(char[] cs, int l, int r) {
        while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    public void test(String s, String... expected) {
        String r = longestPalindrome(s);
        System.out.println("s = " + s);
        System.out.println("r = " + r);
        System.out.println("e = " + Arrays.toString(expected));
        System.out.println(Arrays.asList(expected).contains(r));
        System.out.println("----------------------");
    }

    @Test
    public void test() {
        test("babad", "bab", "aba");
        test("cbbd", "bb");
        test("bb", "bb");
    }
}
