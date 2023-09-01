package math;

import java.util.Arrays;

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

    private final long[][] kMirrors = new long[10][];

    public long kMirror(int k, int n) {
        if (kMirrors[k] == null) {
            kMirrors[k] = new long[31];
        }
        if (kMirrors[k][n] != 0) {
            return sum(kMirrors[k], n);
        }
        // todo
        return 0;
    }

    private long sum(long[] kMirrors, int n) {
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += kMirrors[i];
        }
        return ans;
    }

    public long toNumber(int[] a, int size) {
        long ans = 0;
        long level = 1;
        for (int i = 0; i < size; i++) {
            ans += level * a[i];
            level *= 10;
        }
        return ans;
    }

    public boolean isKMirror(long a, int k) {
        if (a < k) {
            return true;
        }
        int[] kthNumber = toKthNumber(a, k);
        int l = 0, r = kthNumber.length - 1;
        while (l < r) {
            if (kthNumber[l++] != kthNumber[r--]) {
                return false;
            }
        }
        return true;
    }

    private int[] toKthNumber(long a, int k) {
        int[] ans = new int[64];
        int size = 0;
        while (a > 0) {
            ans[size++] = (int) (a % k);
            a /= k;
        }
        return Arrays.copyOfRange(ans, 0, size);
    }
}
