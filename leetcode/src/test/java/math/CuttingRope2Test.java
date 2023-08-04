package math;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/jian-sheng-zi-ii-lcof">剑指 Offer 14- II. 剪绳子 II</a>
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 * <p>
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 1000
 * 注意：本题与主站 343 题相同：<a href="https://leetcode-cn.com/problems/integer-break/">343. 整数拆分</a>
 *
 * @author xuzhou
 * @since 2023/8/4 18:16
 */
public class CuttingRope2Test {

    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        // 推论1：从4以后，数字本身一定小于分段乘积
        // 推论2：任何大于3的数字可以分成2或3开头的段，组成最大值
        // 推论3：任何大于3的数字都可以分成只有2和3组成的段，并组成最大值
        // 推论4：6的拆分方法中，2^3<3^2
        // 结论：把n优先按3分解，结果值最大，证明看leetcode官方
        // 本题比较特殊，1000/3=333
        if (n % 3 == 0) {
            return pow(3, n / 3);
        }
        if (n % 3 == 2) {
            return _double(pow(3, (n - 2) / 3), 1);
        }
        return _double(pow(3, (n - 4) / 3), 2);
    }

    public int pow(int a, int b) {
        int ans = 1;
        for (int i = 0; i < b; i++) {
            ans = ans * a % 1000000007;
        }
        return ans;
    }

    public int _double(int a, int b) {
        for (int i = 0; i < b; i++) {
            a = a << 1 % 1000000007;
        }
        return a;
    }


    @Test
    public void test() {
        int a = (int) Math.pow(3, 19);//3的20次方会溢出
        System.out.println("a = " + a);
        System.out.println("a % 1000000007 = " + a % 1000000007);
        int b = (int) Math.pow(a % 1000000007, 2);
        System.out.println("b = " + b);
        System.out.println(pow(3, 332));
    }
}
