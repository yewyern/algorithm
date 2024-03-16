package math;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/jian-sheng-zi-lcof/">剑指 Offer 14- I. 剪绳子</a>
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 * 注意：本题与主站 343 题相同：<a href="https://leetcode-cn.com/problems/integer-break/">343. 整数拆分</a>
 * @author xuzhou
 * @since 2023/8/4 17:11
 */
public class CuttingRopeTest {

    private static final int[] ans = new int[59];
    private static int len = 5;
    static {
        ans[1] = 1;
        ans[2] = 2;
        ans[3] = 3;
        ans[4] = 4;
    }

    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        // 推论1：从4以后，数字本身一定小于分段乘积
        // 推论2：任何大于3的数字可以分成2或3开头的段，组成最大值
        // 推论3：任何大于3的数字都可以分成只有2和3组成的段，并组成最大值
        // 推论4：6的拆分方法中，2^3<3^2
        // 结论：把n优先按3分解，结果值最大，证明看leetcode官方
        if (n % 3 == 0) {
            return (int) Math.pow(3, (double) n / 3);
        }
        if (n % 3 == 2) {
            return (int) Math.pow(3, (double) (n - 2) / 3) << 1;
        }
        return (int) Math.pow(3, (double) (n - 4) / 3) << 2;
    }

    public int cuttingRope1(int n) {
        if (n <= 3) {
            return n - 1;
        }
        if (n < len) {
            return ans[n];
        }
        while (len <= n) {
            // 动态规划：第一段不需要考虑长度4以上，因为5的最大值是分成2*3，之后的都是大于分段长度的
            ans[len] = Math.max(ans[len - 2] << 1, 3 * ans[len - 3]);
            len++;
        }
        return ans[n];
    }

    public int cuttingRope2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        if (n < len) {
            return ans[n];
        }
        while (len <= n) {
            // 动态规划：当前段*另一段最大值
            int m = len >> 1;
            for (int i = 2; i <= m; i++) {
                ans[len] = Math.max(ans[len], i * ans[len - i]);
            }
            len++;
        }
        return ans[n];
    }

    @Test
    public void test() {
        System.out.println(cuttingRope(10));
    }
}
