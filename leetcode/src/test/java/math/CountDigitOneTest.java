package math;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof">剑指 Offer 43. 1～n 整数中 1 出现的次数</a>
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * <p>
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：6
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n < 2^31
 * 注意：本题与主站 233 题相同：<a href="https://leetcode-cn.com/problems/number-of-digit-one/">233. 数字 1 的个数</a>
 *
 * @author xuzhou
 * @since 2023/8/11 10:53
 */
public class CountDigitOneTest {

    public int countDigitOne(int n) {
        // 对于每一个位置，该位置1的个数公式如下：
        // 1、该位置是0,1, 加上 左边的数字 * 当前数字的级数
        // 2、该位置是1, 加上 右边数字+1
        // 3、该位置>1, 加上 （左边的数字+1）* 当前数字的级数
        // 当前数字的级数：个位：1，十位数：10，以此类推
        int level = 1, left = n / 10, cur = n % 10, right = 0;
        int count = 0;
        while (n > 0) {
            n /= 10;
            count += (cur < 2 ? left : left + 1) * level;
            count += cur == 1 ? right + 1 : 0;
            right = cur * level + right;
            left = n / 10;
            cur = n % 10;
            level *= 10;
        }
        return count;
    }

    @Test
    public void test() {
        System.out.println(countDigitOne(2001));
    }

}
