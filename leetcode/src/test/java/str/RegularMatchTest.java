package str;

import org.junit.Assert;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/">剑指 Offer 19. 正则表达式匹配</a>
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * 注意：本题与主站 10 题相同：<a href="https://leetcode-cn.com/problems/regular-expression-matching/">10. 正则表达式匹配</a>
 *
 * @author xuzhou
 * @since 2023/8/17 11:09
 */
public class RegularMatchTest {

    public boolean isMatch(String s, String p) {
        return process(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean process(char[] s, char[] p, int si, int pi) {
        if (si == s.length) {
            if ((p.length - pi) % 2 == 1) {
                return false;
            }
            int count = (p.length - pi) >> 1;
            int i = pi + 1;
            while (i < p.length && p[i] == '*') {
                count--;
                i = i + 2;
            }
            return count == 0;
        }
        if (pi == p.length) {
            return false;
        }
        if (pi < p.length - 1 && p[pi + 1] == '*') {
            for (int i = si; i < s.length && (s[i] == p[pi] || p[pi] == '.'); i++) {
                if (process(s, p, i + 1, pi + 2)) {
                    return true;
                }
            }
            return process(s, p, si, pi + 2);
        }
        return (s[si] == p[pi] || p[pi] == '.') && process(s, p, si + 1, pi + 1);
    }

    @Test
    public void test() {
        Assert.assertTrue(isMatch("", ""));
        Assert.assertTrue(isMatch("aa", "a*"));
        Assert.assertFalse(isMatch("aa", "a"));
        Assert.assertTrue(isMatch("ab", ".*"));
        Assert.assertTrue(isMatch("aab", "c*a*b"));
        Assert.assertFalse(isMatch("mississippi", "mis*is*p*."));
    }
}
