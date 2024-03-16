package binary;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/binary-number-with-alternating-bits">693. 交替位二进制数</a>
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 *
 * 示例 1：
 *
 * 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 * 示例 3：
 *
 * 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 * 示例 4：
 *
 * 输入：n = 10
 * 输出：true
 * 解释：10 的二进制表示是：1010.
 * 示例 5：
 *
 * 输入：n = 3
 * 输出：false
 *
 * @author zhou.xu
 * @since 2020/12/2 15:23
 */
public class HasAlternatingBits {

    public boolean hasAlternatingBits(int n) {
        while (n > 0) {
            int lastBit = n & 1;
            n >>= 1;
            if (((n & 1) ^ lastBit) != 1) {
                return false;
            }
        }
        return true;
    }

    public void test(int n, boolean expected) {
        System.out.println("n = " + Integer.toBinaryString(n));
        boolean res = hasAlternatingBits(n);
        System.out.println("res = " + res);
        System.out.println("expected = " + expected);
        System.out.println("-------------------------------------");
    }

    @Test
    public void test() {
        test(4, false);
        test(5, true);
        test(7, false);
        test(11, false);
        test(10, true);
        test(3, false);
    }
}
