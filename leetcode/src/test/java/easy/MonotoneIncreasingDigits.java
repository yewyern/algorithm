package easy;

import org.junit.Test;

/**
 * 单调递增的数字
 *
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 * 输入: N = 10
 * 输出: 9
 *
 * 示例 2:
 * 输入: N = 1234
 * 输出: 1234
 *
 * 示例 3:
 * 输入: N = 332
 * 输出: 299
 *
 * @author zhou.xu
 * @since 2020/12/2 16:11
 */
public class MonotoneIncreasingDigits {

    public int monotoneIncreasingDigits(int N) {
        int res = 0, multi = 1, len = 0;
        int[] stack = new int[11];
        while (N > 0) {
            int digit = N % 10;
            if (len > 0) {
                int pos = len - 1;
                int next = digit;
                boolean lower = false;
                while (pos >= 0 && stack[pos] < next) {
                    next = 9;
                    stack[pos--] = 9;
                    lower = true;
                }
                if (lower) {
                    digit--;
                }
            }
            stack[len++] = digit;
            N /= 10;
        }
        for (int i = 0; i < len; i++) {
            res += stack[i] * multi;
            multi *= 10;
        }
        return res;
    }

    public void test(int N) {
        System.out.println("N = " + N);
        int res = monotoneIncreasingDigits(N);
        System.out.println("res = " + res);
        System.out.println("------------------");
    }

    @Test
    public void test() {
        test(8);
        test(10);
        test(1234);
        test(332);
        test(1231);
        test(100);
    }

}
