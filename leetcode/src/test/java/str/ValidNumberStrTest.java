package str;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 20. 表示数值的字符串</a>
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * <p>
 * 数值（按顺序）可以分成以下几个部分：
 * <p>
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分数值列举如下：
 * <p>
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 * <p>
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 *
 * @author xuzhou
 * @since 2023/6/5 13:59
 */
public class ValidNumberStrTest {

    @Test
    public void test() {
        check("+100", true);
        check("5e2", true);
        check("-123", true);
        check("3.1416", true);
        check("-1E-16", true);
        check("0123", true);
        check("12e", false);
        check("1a3.14", false);
        check("1.2.3", false);
        check("+-5", false);
        check("12e+5.4", false);
    }

    private void check(String s, boolean expected) {
        boolean actual = isNumber(s);
        if (actual ^ expected) {
            System.out.println("str = " + s + ", expected = " + expected + ", actual = " + actual);
        }
    }

    public boolean isNumber(String s) {
        // "   +12.44e-123   "
        // "   +" 0
        // "12" 1
        // ".44" 2
        // "e" 3
        // "-123" 4
        char[] cs = s.toCharArray();
        int phrase = 0;
        for (char c : cs) {
            if (c == ' ') {
                if (phrase != 0) {
                    return false;
                }
            } else if (c == '+' || c == '-') {
                if (phrase != 0 && phrase != 3) {
                    return false;
                }
                phrase++;
            } else if (c == '.') {
                if (phrase != 0 && phrase != 1) {
                    return false;
                }
                phrase = 2;
            } else if (c == 'e' || c == 'E') {
                if (phrase != 1 && phrase != 2) {
                    return false;
                }
                phrase = 3;
            } else if (c >= '0' && c <= '9') {
                if (phrase == 0 || phrase == 3) {
                    phrase++;
                }
            } else {
                return false;
            }
        }
        return phrase != 0 && phrase != 3 && phrase != 4;
    }
}
