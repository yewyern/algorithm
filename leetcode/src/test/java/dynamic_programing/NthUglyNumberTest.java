package dynamic_programing;

import java.util.TreeSet;

/**
 * <a href="https://leetcode.cn/problems/ugly-number-ii/description/">264. 丑数 II</a>
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * <p>
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1690
 *
 * @author xuzhou
 * @since 2023/7/31 18:05
 */
public class NthUglyNumberTest {

    private static int[] arr = new int[1691];
    static {
        arr[1] = 1;
    }
    private static int len = 2;
    private static int p2 = 1, p3 = 1, p5 = 1;//分别表示不同乘数目前走到了哪个丑数

    public int nthUglyNumber(int n) {
        // 缓存结果，总时间优化
        if (n < len) {
            return arr[n];
        }
        for (; len <= n; len++) {
            int m2 = arr[p2] * 2;
            int m3 = arr[p3] * 3;
            int m5 = arr[p5] * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            arr[len] = min;
            if (min == m2) {
                p2++;
            }
            if (min == m3) {
                p3++;
            }
            if (min == m5) {
                p5++;
            }
        }
        return arr[n];
    }

    public int nthUglyNumber1(int n) {
        // 任意丑数x2,x3,x5的结果也是丑数
        // 如：[1,2,3,4,5,6,8,9,10,12]
        // 可以看成：[1,1*2,1*3,2*2,1*5,3*2,4*2,3*3,5*2,6*2]
        // 可以分组：[1*2,2*2,3*2,4*2,5*2,6*2]
        // [1*3,2*3,3*3]
        // [1*5,2*5]
        // 可以看出，以2为乘数，3为乘数，5为乘数的步长不一样，但是都是所有序列上的丑数乘出来的
        int[] arr = new int[n];
        arr[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;//分别表示不同乘数目前走到了哪个丑数
        for (int i = 1; i < n; i++) {
            int m2 = arr[p2] * 2;
            int m3 = arr[p3] * 3;
            int m5 = arr[p5] * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            arr[i] = min;
            if (min == m2) {
                p2++;
            }
            if (min == m3) {
                p3++;
            }
            if (min == m5) {
                p5++;
            }
        }
        return arr[n - 1];
    }

    public int nthUglyNumber2(int n) {
        if (n == 1) {
            return 1;
        }
        int maxTowMultiplier = Integer.MAX_VALUE >> 1;
        int maxThreeMultiplier = Integer.MAX_VALUE / 3;
        int maxFiveMultiplier = Integer.MAX_VALUE / 5;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        for (int i = 1; i < n; i++) {
            // 用当前最小值*2，*3，*5,之后放到TreeSet中
            // 1、会有大量的数是浪费的
            // 2，TreeSet排序会耗时
            // 耗时，O(nlogn)
            Integer min = set.pollFirst();
            if (min < maxTowMultiplier) {
                set.add(min * 2);
            }
            if (min < maxThreeMultiplier) {
                set.add(min * 3);
            }
            if (min < maxFiveMultiplier) {
                set.add(min * 5);
            }
        }
        return set.pollFirst();
    }
}
