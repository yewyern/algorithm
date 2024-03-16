package math;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof">剑指 Offer 44. 数字序列中某一位的数字</a>
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * <p>
 * 请写一个函数，求任意第n位对应的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：n = 11
 * 输出：0
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= n < 2^31
 * 注意：本题与主站 400 题相同：<a href="https://leetcode-cn.com/problems/nth-digit/">400. 第 N 位数字</a>
 *
 * @author xuzhou
 * @since 2023/8/10 17:22
 */
public class FindNthDigitTest {

    public int findNthDigit(int n) {
        int len = 1, floor = 1, ceil = 10, maxLen = 9;
        while (len < 9 && n >= maxLen) {
            n -= maxLen;
            floor = ceil;
            ceil *= 10;
            len++;
            maxLen = (ceil - floor) * len;
        }
        int num = n / len + floor;
        int index = n % len;
        return num / (int) (Math.pow(10, len - 1 - index)) % 10;
    }

    @Test
    public void test() {
        System.out.println(findNthDigit(1000000000));
    }
}
