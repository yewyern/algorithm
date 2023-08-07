package math;

import org.junit.Test;

import java.math.BigInteger;

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

    private static final BigInteger mod = BigInteger.valueOf(1000000007);
    private static final BigInteger powMod = BigInteger.valueOf(10).pow(21);
    private static final BigInteger two = BigInteger.valueOf(2);
    private static final BigInteger three = BigInteger.valueOf(3);
    private static final BigInteger four = BigInteger.valueOf(4);

    private static final long[] ans = new long[1001];

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
        if (n < len) {
            return (int) ans[n];
        }
        while (len <= n) {
            // 公式：
            // 若：x % m = n
            // 则：a * x % m = a * n % m
            // 因为结果值已经取模，大小不再具备传递性，不能使用比大小的方式决定第一段是2还是3，必须要确定是有多少个3，多少个2
            if (len % 3 == 0) {
                ans[len] = 3 * ans[len - 3] % 1000000007;
            } else if (len % 3 == 2) {
                ans[len] = (ans[len - 2] << 1) % 1000000007;
            } else {
                ans[len] = (ans[len - 4] << 2) % 1000000007;
            }
            len++;
        }
        return (int) ans[n];
    }

    public int cuttingRope2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        if (n % 3 == 0) {
            return three.modPow(BigInteger.valueOf(n / 3), powMod).mod(mod).intValue();
        }
        if (n % 3 == 2) {
            return three.modPow(BigInteger.valueOf((n - 2) / 3), powMod).multiply(two).mod(mod).intValue();
        }
        return three.modPow(BigInteger.valueOf((n - 4) / 3), powMod).multiply(four).mod(mod).intValue();
    }

    public long pow(int a, int b) {
        long ans = 1;
        for (int i = 0; i < b; i++) {
            ans = ans * a % 10000000000L;
        }
        return ans;
    }

    public long _double(long a, int b) {
        return a << b;
    }


    @Test
    public void test() {
        long a = (long) Math.pow(3, 21);//3的20次方会溢出
        System.out.println("a = " + a);
        System.out.println("a % 1000000007 = " + a % 1000000007);
        int b = (int) Math.pow(a % 1000000007, 2);
        System.out.println("b = " + b);
        System.out.println(pow(3, 332));
        System.out.println(cuttingRope(63));
    }
}
