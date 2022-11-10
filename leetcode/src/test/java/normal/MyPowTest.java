package normal;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/powx-n/">50. Pow(x, n)</a>
 * <p>实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: 2.00000, 10
 * <p>输出: 1024.00000
 * <p>示例 2:
 * <p>
 * <p>输入: 2.10000, 3
 * <p>输出: 9.26100
 * <p>示例 3:
 * <p>
 * <p>输入: 2.00000, -2
 * <p>输出: 0.25000
 * <p>解释: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 *
 * @author zhou.xu
 * @since 2020/11/12 19:11
 */
public class MyPowTest {

    public double myPow(double x, int n) {
        return Pow(x, n);
    }

    public double Pow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n < 0) {
            return 1 / Pow(x, -n);
        }
        double d = Pow(x, n / 2);
        if (n % 2 == 1) {
            return d * d * x;
        } else {
            return d * d;
        }
    }

    public void test(double x, int n) {
        double a = Math.pow(x, n);
        double b = myPow(x, n);
        if (a != b) {
            System.out.println("Noooooooooo!");
            System.out.println("Math.pow(" + x + ", " + n + ") = " + a);
            System.out.println("myPow(" + x + ", " + n + ") = " + b);
        }
    }

    @Test
    public void test() {
        test(2, -2);
        test(2, 2);
        test(2.1, 3);
        test(2, 10);
        test(3, Integer.MIN_VALUE);
        test(2, -2147483648);
        test(2, -64);
    }
}
