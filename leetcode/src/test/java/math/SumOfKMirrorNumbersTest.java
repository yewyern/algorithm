package math;

import org.junit.Test;


/**
 * <a href="https://leetcode.cn/problems/sum-of-k-mirror-numbers/">2081. k 镜像数字的和</a>
 * 一个 k 镜像数字 指的是一个在十进制和 k 进制下从前往后读和从后往前读都一样的 没有前导 0 的 正 整数。
 * <p>
 * 比方说，9 是一个 2 镜像数字。9 在十进制下为 9 ，二进制下为 1001 ，两者从前往后读和从后往前读都一样。
 * 相反地，4 不是一个 2 镜像数字。4 在二进制下为 100 ，从前往后和从后往前读不相同。
 * 给你进制 k 和一个数字 n ，请你返回 k 镜像数字中 最小 的 n 个数 之和 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 2, n = 5
 * 输出：25
 * 解释：
 * 最小的 5 个 2 镜像数字和它们的二进制表示如下：
 * 十进制       二进制
 * 1          1
 * 3          11
 * 5          101
 * 7          111
 * 9          1001
 * 它们的和为 1 + 3 + 5 + 7 + 9 = 25 。
 * 示例 2：
 * <p>
 * 输入：k = 3, n = 7
 * 输出：499
 * 解释：
 * 7 个最小的 3 镜像数字和它们的三进制表示如下：
 * 十进制       三进制
 * 1          1
 * 2          2
 * 4          11
 * 8          22
 * 121        11111
 * 151        12121
 * 212        21212
 * 它们的和为 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499 。
 * 示例 3：
 * <p>
 * 输入：k = 7, n = 17
 * 输出：20379000
 * 解释：17 个最小的 7 镜像数字分别为：
 * 1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= k <= 9
 * 1 <= n <= 30
 *
 * @author xuzhou
 * @since 2023/9/1 17:39
 */
public class SumOfKMirrorNumbersTest {

    private static final KMirrors[] kMirrors = new KMirrors[10];
    static {
        for (int k = 2; k < 10; k++) {
            kMirrors[k] = new KMirrors(k, 31);
            kMirrors[k].kMirror(30);
        }
    }

    public long kMirror(int k, int n) {
        if (kMirrors[k] == null) {
            kMirrors[k] = new KMirrors(k, 31);
        }
        return kMirrors[k].kMirror(n);
    }

    private static class KMirrors {
        final int k;
        int len;// 当前数字总长度
        final int base;// 基数：取10或者k
        long half; // 左半部分，奇数长度时包括中间数字
        long halfStart; // 当前长度时左半部分初始值
        long halfEnd; // 当前长度时左半部分结束值
        long level; // 当前长度时左半部分需要乘的值
        final long[] kMirrorSum; // 已计算的结果
        int size;

        public KMirrors(int k, int maxSize) {
            this.k = k;
            len = 1;
            base = k;
            half = 1;
            halfStart = 1;
            halfEnd = k;
            level = 1;
            size = 0;
            kMirrorSum = new long[maxSize];
        }

        public long kMirror(int n) {
            while (size < n) {
                long next;
                do {
                    next = next();
                } while (!isKMirror(next, 10));
                kMirrorSum[size] = next + (size > 0 ? kMirrorSum[size - 1] : 0);
                size++;
            }
            return kMirrorSum[n - 1];
        }

        private long next() {
            if ((len & 1) == 1) {
                // 奇数长度
                long ans = half * level + reverse(half / base, base);
                half++;
                if (half == halfEnd) {
                    half = halfStart;
                    len++;
                    level *= base;
                }
                return ans;
            } else {
                // 偶数长度
                long ans = half * level + reverse(half, base);
                half++;
                if (half == halfEnd) {
                    halfStart *= base;
                    halfEnd *= base;
                    half = halfStart;
                    len++;
                }
                return ans;
            }
        }

        public boolean isKMirror(long a, int k) {
            return a < k || reverse(a, k) == a;
        }

        public long reverse(long a, int k) {
            long ans = 0;
            while (a > 0) {
                ans = ans * k + a % k;
                a /= k;
            }
            return ans;
        }

    }

    @Test
    public void test() {
        System.out.println(kMirror(2, 5));
        System.out.println(kMirror(3, 7));
        System.out.println(kMirror(7, 17));
    }
}
