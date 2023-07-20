package dynamic_programing;

import org.junit.Test;
import utils.RandomUtils;

/**
 * <a href="https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 46. 把数字翻译成字符串</a>
 * <p>
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= num < 2^31
 *
 * @author xuzhou
 * @since 2023/7/20 14:33
 */
public class TranslateNumTest {

    @Test
    public void test() {
        for (int i = 0; i < 100000; i++) {
            int num = RandomUtils.nextInt(Integer.MAX_VALUE);
            int res1 = translateNum(num);
            int res2 = translateNum2(num);
            if (res1 != res2) {
                System.out.println("num = " + num);
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                break;
            }
        }
    }

    public int translateNum(int num) {
        int a0 = 1, a1 = (num % 100 > 9 && num % 100 < 26) ? 2 : 1, res = a1;
        while (num > 0) {
            num /= 10;
            if (num % 100 > 9 && num % 100 < 26) {
                res = a1 + a0;
            }
            a0 = a1;
            a1 = res;
        }
        return res;
    }

    public int translateNum2(int num) {
        return dfs(num);
    }

    public int dfs(int num) {
        if (num > 9 && num < 26) {
            return 2;
        }
        if (num < 100) {
            return 1;
        }
        if (num % 100 > 9 && num % 100 < 26) {
            return dfs(num / 10) + dfs(num / 100);
        }
        return dfs(num / 10);
    }

}
