package normal;

import org.junit.Test;

/**
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
 * @date 2020/11/12 19:11
 */
public class MyPow {

    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        if (x == 2) {
            return 1 << n;
        }
        int m = 1;
        int ans = 0;

        return 0;
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
    }
}
