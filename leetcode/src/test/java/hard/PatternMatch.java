package hard;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * <p>'.' 匹配任意单个字符
 * <p>'*' 匹配零个或多个前面的那一个元素
 * <p>所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * <p>说明:
 * <p>
 * <p>s 可能为空，且只包含从 a-z 的小写字母。
 * <p>p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * @author zhou.xu
 * @date 2020/10/10 9:02
 */
public class PatternMatch {

    public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }

    public boolean isMatch(String s, String p, int sIndex, int pIndex) {
        while (sIndex < s.length() && pIndex < p.length()) {
            char pc = p.charAt(pIndex);
            boolean isAll = pc == '.';
            boolean isMulti = pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*';
            if (isAll) {
                if (isMulti) {
                    while (sIndex < s.length()) {
                        if (isMatch(s, p, sIndex, pIndex + 2)) {
                            return true;
                        }
                        sIndex++;
                    }
                } else {
                    sIndex++;
                }
            } else {
                if (isMulti) {
                    while (sIndex < s.length() && pc == s.charAt(sIndex)) {
                        if (isMatch(s, p, sIndex, pIndex + 2)) {
                            return true;
                        }
                        sIndex++;
                    }
                } else if (pc != s.charAt(sIndex)) {
                    return false;
                } else {
                    sIndex++;
                }
            }
            if (isMulti) {
                pIndex += 2;
            } else {
                pIndex++;
            }
        }
        return sIndex == s.length() && pIndex == p.length();
    }

    public void test(String s, String p, boolean expected) {
        boolean match = isMatch(s, p);
        System.out.println("s = " + s + ", p = " + p + " : " + match);
        Assert.assertEquals(expected, match);
    }

    @Test
    public void test() {
        test("abc", "abcd", false);
        test("abcd", "a*", false);
        test("aaaa", "a*a", true);
        test("aaaabbb", "a*b*", true);
        test("aaaabbbcd", "a*b*c*d", true);
        test("abcd", "abcd", true);
        test("abcd", "ab*", false);
        test("abcd", "a.*", true);
        test("abcd", ".*cd", true);
    }
}
