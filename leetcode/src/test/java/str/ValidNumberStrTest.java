package str;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof">剑指 Offer 20. 表示数值的字符串</a>
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
        check("32.e-80123", true);
    }

    private void check(String s, boolean expected) {
        boolean actual = isNumber(s);
        if (actual ^ expected) {
            System.out.println("str = " + s + ", expected = " + expected + ", actual = " + actual);
        }
    }

    public boolean isNumber(String s) {
        char[] cs = s.toCharArray();
        int start = 0, end = cs.length - 1;
        // 去除尾部空格
        while (end >= 0 && cs[end] == ' ') {
            end--;
        }
        if (end < 0) {
            return false;
        }
        end++;
        // 去除首部空格
        while (start < end && cs[start] == ' ') {
            start++;
        }
        boolean afterE = false;
        int digitCount = 0;
        int digitStart = Integer.MAX_VALUE;
        int dotCount = 0;
        int dotStart = Integer.MAX_VALUE;
        int signCount = 0;
        // 先判断e前面的部分
        while (start < end) {
            char c = cs[start];
            if (c >= '0' && c <= '9') {
                digitCount++;
                digitStart = Math.min(digitStart, start);
            } else if (c == '.') {
                if (afterE) {
                    return false;
                }
                dotCount++;
                if (dotCount > 1) {
                    return false;
                }
                dotStart = Math.min(dotStart, start);
            } else if (c == 'e' || c == 'E') {
                if (digitCount == 0 || afterE) {
                    return false;
                }
                afterE = true;
                digitCount = 0;
                digitStart = Integer.MAX_VALUE;
                dotStart = Integer.MAX_VALUE;
                signCount = 0;
            } else if (c == '+' || c == '-') {
                signCount++;
                if (signCount > 1) {
                    return false;
                }
                if (start > digitStart || start > dotStart) {
                    return false;
                }
            } else {
                return false;
            }
            start++;
        }
        return digitCount > 0;
    }

    public boolean isNumber2(String s) {
        char[] cs = s.toCharArray();
        int start = 0, end = cs.length - 1;
        // 去除尾部空格
        while (end >= 0 && cs[end] == ' ') {
            end--;
        }
        if (end < 0) {
            return false;
        }
        end++;
        // 去除首部空格
        while (start < end && cs[start] == ' ') {
            start++;
        }
        int digitCount = 0;
        int digitStart = Integer.MAX_VALUE;
        int dotCount = 0;
        int dotStart = Integer.MAX_VALUE;
        int signCount = 0;
        // 先判断e前面的部分
        while (start < end && cs[start] != 'e' && cs[start] != 'E') {
            char c = cs[start];
            if (c >= '0' && c <= '9') {
                digitCount++;
                digitStart = Math.min(digitStart, start);
            } else if (c == '.') {
                dotCount++;
                if (dotCount > 1) {
                    return false;
                }
                dotStart = Math.min(dotStart, start);
            } else if (c == '+' || c == '-') {
                signCount++;
                if (signCount > 1) {
                    return false;
                }
                if (start > digitStart || start > dotStart) {
                    return false;
                }
            } else {
                return false;
            }
            start++;
        }
        if (digitCount == 0) {
            return false;
        }
        if (start == end) {
            return true;
        }
        start++;
        digitCount = 0;
        digitStart = Integer.MAX_VALUE;
        signCount = 0;
        while (start < end) {
            char c = cs[start];
            if (c >= '0' && c <= '9') {
                digitCount++;
                digitStart = Math.min(digitStart, start);
            } else if (c == '+' || c == '-') {
                signCount++;
                if (signCount > 1) {
                    return false;
                }
                if (start > digitStart) {
                    return false;
                }
            } else {
                return false;
            }
            start++;
        }
        return digitCount > 0;
    }

    private boolean isValid(char c) {
        return c >= '0' && c <= '9'
                || c == 'e' || c == 'E'
                || c == '.'
                || c == '+' || c == '-';
    }
}
