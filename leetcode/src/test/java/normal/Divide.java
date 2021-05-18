package normal;

import org.junit.Test;

/**
 * <p> 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p> 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p> 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * <p>
 * <p> 示例 1:
 * <p>
 * <p> 输入: dividend = 10, divisor = 3
 * <p> 输出: 3
 * <p> 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * <p> 示例 2:
 * <p>
 * <p> 输入: dividend = 7, divisor = -3
 * <p> 输出: -2
 * <p> 解释: 7/-3 = truncate(-2.33333..) = -2
 * <p>
 * <p>
 * <p> 提示：
 * <p>
 * <p> 被除数和除数均为 32 位有符号整数。
 * <p> 除数不为 0。
 * <p> 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 * @author zhou.xu
 * @date 2020/10/23 10:51
 */
public class Divide {

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == divisor) {
            return 1;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            }
            return Integer.MAX_VALUE;
        }
        boolean same = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }
        int res = 0;
        while (dividend < divisor) {
            int temp = divisor;
            int c = 1;
            while (dividend - temp <= temp) {
                temp <<= 1;
                c <<= 1;
            }
            dividend -= temp;
            res += c;
        }
        return same ? res : -res;
    }

    public void test(int dividend, int divisor) {
        System.out.println(dividend + " / " + divisor + " = " + divide(dividend, divisor));
    }

    @Test
    public void test() {
        test(10, 3);
        test(7, 3);
        test(1, 3);
        test(-1, -3);
        test(-2147483648, -1);
        test(-2147483648, 2);
    }
}
