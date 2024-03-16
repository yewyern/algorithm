package math;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/">剑指 Offer 66. 构建乘积数组</a>
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 *
 * @author xuzhou
 * @since 2023/8/4 16:32
 */
public class ConstructMultiplyArrTest {

    public int[] constructArr(int[] a) {
        if (a == null || a.length < 2) {
            return a;
        }
        int N = a.length;
        int[] ans = new int[N];
        Arrays.fill(ans, 1);
        // 从左向右算
        for (int i = 1; i < N; i++) {
            ans[i] = ans[i - 1] * a[i - 1];
        }
        // 从右向左
        int r = 1;
        for (int i = N - 2; i >= 0; i--) {
            r *= a[i + 1];
            ans[i] *= r;
        }
        return ans;
    }

    public int[] constructArr1(int[] a) {
        if (a == null || a.length < 2) {
            return a;
        }
        int N = a.length;
        int[] ans = new int[N];
        int[] right = new int[N];
        right[N - 1] = a[N - 1];
        for (int i = N - 2; i > 0; i--) {
            right[i] = a[i] * right[i + 1];
        }
        ans[0] = right[1];
        int left = a[0];
        for (int i = 1; i < N - 1; i++) {
            ans[i] = left * right[i + 1];
            left *= a[i];
        }
        ans[N - 1] = left;
        return ans;
    }

    public int[] constructArr2(int[] a) {
        if (a == null || a.length < 2) {
            return a;
        }
        int N = a.length;
        int[] ans = new int[N];
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = a[0];
        for (int i = 1; i < N; i++) {
            left[i] = a[i] * left[i - 1];
        }
        right[N - 1] = a[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            right[i] = a[i] * right[i + 1];
        }
        ans[0] = right[1];
        ans[N - 1] = left[N - 2];
        for (int i = 1; i < N - 1; i++) {
            ans[i] = left[i - 1] * right[i + 1];
        }
        return ans;
    }
}
