package easy;

import org.junit.Test;

/**
 * <p>给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: 1
 * <p>输出: true
 * <p>解释: 20 = 1
 * <p>示例 2:
 * <p>
 * <p>输入: 16
 * <p>输出: true
 * <p>解释: 24 = 16
 * <p>示例 3:
 * <p>
 * <p>输入: 218
 * <p>输出: false
 * <p>
 *
 * @author zhou.xu
 * @since 2020/10/9 9:59
 */
public class IsPowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (-n)) == n;
    }

    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    @Test
    public void test() {
        for (int i = 0; i < 100000; i++) {
            if (isPowerOfTwo(i) != isPowerOfTwo2(i)) {
                System.out.println("i = " + (i) + " : " + isPowerOfTwo2(i));
            }
        }
    }
}
