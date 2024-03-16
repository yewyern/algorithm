package math;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/number-of-ways-to-buy-pens-and-pencils">2240. 买钢笔和铅笔的方案数</a>
 * @author xuzhou
 * @since 2023/9/21 17:53
 */
public class WaysToBuyPensPencilsTest {

    @Test
    public void test() {
        System.out.println(waysToBuyPensPencils(100, 19, 13));
    }

    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        if (total < cost1 && total < cost2) {
            return 1;
        }
        if (cost1 < cost2) {
            // 交换cost1和cost2，保持cost1 >= cost2
            cost2 = cost1 ^ cost2;
            cost1 = cost1 ^ cost2;
            cost2 = cost1 ^ cost2;
        }
//        long count = 0;
//        for (long i = 0; i <= total / cost1; i++) {
//            count += (total - i * cost1) / cost2 + 1;
//        }
//      上面的代码，通过一系列分解变换可以得到
//        long i = total / cost1 + 1;
//        return i * total / cost2 - (i - 1) * i * cost1 / cost2 / 2 + i;
//      但是由于(total - x * cost1)/cost2 存在余数，且余数不确定，在合并时出现误差
//      可以考虑对于有相同余数的公式进行合并
//      哪些 (total - i * cost1) / cost2 存在相同余数呢？
//      当(total - x1 * cost1)-(total - x2 * cost1)的值为cost1和cost2的最小公倍数时，余数相同
        long lcm = lcm(cost1, cost2); // 最小公倍数
        long n = lcm / cost1; // 总共n个不同余数
        long count = 0;
        for (long i = 0; i < n; i++) {
            long t = total - i * cost1;
            if (t < 0) {
                break;
            }
            long m = t % cost2; // 当前余数
            long k = t / lcm + 1; // 总共多少个
            count += (k * (t - m) - k * (k - 1) * lcm / 2) / cost2 + k;
        }
        return count;
    }

    private long gcd(long a, long b) {
        // 求最小公因数
        if (a < b) {
            // 交换a和b，保持a >= b
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        // gcd(被除数，除数) = gcd(除数，余数)
        long m;
        while (b != 0) {
            m = a % b;
            a = b;
            b = m;
        }
        return a;
    }

    private long lcm(long a, long b) {
        // 求最小公倍数
        return a / gcd(a, b) * b;
    }

    public long waysToBuyPensPencils1(int total, int cost1, int cost2) {
        if (total < cost1 && total < cost2) {
            return 1;
        }
        if (cost1 < cost2) {
            cost2 = cost1 ^ cost2;
            cost1 = cost1 ^ cost2;
            cost2 = cost1 ^ cost2;
        }
        if (total % cost2 == 0 && cost1 % cost2 == 0) {
            long i = total / cost1 + 1;
            return i * total / cost2 - (i - 1) * i * cost1 / cost2 / 2 + i;
        }
        long count = 0;
        for (long i = 0; i <= total / cost1; i++) {
            count += (total - i * cost1) / cost2 + 1;
        }
        return count;
    }

    public long waysToBuyPensPencils2(int total, int cost1, int cost2) {
        if (total < cost1 && total < cost2) {
            return 1;
        }
        if (cost1 < cost2) {
            cost2 = cost1 ^ cost2;
            cost1 = cost1 ^ cost2;
            cost2 = cost1 ^ cost2;
        }
        long count = 0;
        for (long i = 0; i <= total / cost1; i++) {
            count += (total - i * cost1) / cost2 + 1;
        }
        return count;
    }
}
