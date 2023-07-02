package dynamic_programing;

import org.junit.Test;
import utils.RandomArray;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/">1186. 删除一次得到子数组最大和</a>
 * 给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
 * <p>
 * 注意，删除一个元素后，子数组 不能为空。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,-2,0,3]
 * 输出：4
 * 解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。
 * 示例 2：
 * <p>
 * 输入：arr = [1,-2,-2,3]
 * 输出：3
 * 解释：我们直接选出 [3]，这就是最大和。
 * 示例 3：
 * <p>
 * 输入：arr = [-1,-1,-1,-1]
 * 输出：-1
 * 解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。
 * 我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i] <= 10^4
 *
 * @author zhou.xu
 * @since 2023/6/27 22:34
 */
public class MaxSumSubArrayWithOneDeletionTest {

    public int maximumSum(int[] arr) {
        int N = arr.length;
        int max = arr[0];
        int pre = arr[0]; // 不删的和值
        int deleteOnce = 0; // 删一次的和值
        for (int i = 1; i < N; i++) {
            deleteOnce = Math.max(deleteOnce + arr[i], pre);
            pre = Math.max(pre + arr[i], arr[i]);
            max = Math.max(max, Math.max(deleteOnce, pre));
        }
        return max;
    }

    public int maximumSumComparison(int[] arr) {
        // 分解为不删除和必删除一个数来求解
        int N = arr.length;
        int max = arr[0];
        int[] left = new int[N];
        int[] right = new int[N];
        // 1、不删除数的最大值
        left[0] = arr[0];
        for (int i = 1; i < N; i++) {
            // 左边最大值
            left[i] = Math.max(left[i - 1] + arr[i], arr[i]);
            max = Math.max(max, left[i]);
        }
        right[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            // 右边最大值
            right[i] = Math.max(right[i + 1] + arr[i], arr[i]);
        }
        // 2、删除一个数的最大值
        for (int i = 1; i < N - 1; i++) {
            max = Math.max(max, left[i - 1] + right[i + 1]);
        }
        return max;
    }

    private int max(int a, int b, int c) {
        if (a > b) {
            return Math.max(a, c);
        }
        return Math.max(b, c);
    }

    public int maximumSumComparison1(int[] arr) {
        // 暴力方法优化1：前缀和数组, 最小队列
        int N = arr.length;
        int max = arr[0];
        int[] preSum = new int[N];
        preSum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            max = Math.max(max, arr[i]);// 子数组长度为1的最大值
            preSum[i] = preSum[i - 1] + arr[i];
        }
        for (int l = 0; l < N; l++) {
            LinkedList<Integer> minQueue = new LinkedList<>();
            minQueue.add(l);
            for (int r = l + 1; r < N; r++) {
                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[r]) {
                    minQueue.pollLast();
                }
                minQueue.addLast(r);
                int pre = preSum[r] - (l > 0 ? preSum[l - 1] : 0);
                max = max(max, pre, pre - arr[minQueue.peekFirst()]);
            }
            minQueue.clear();
        }
        return max;
    }

    public int maximumSumComparison2(int[] arr) {
        // 暴力方法
        int N = arr.length;
        int max = arr[0];
        for (int i = 0; i < N; i++) {
            max = Math.max(arr[i], max);
            for (int j = i + 2; j <= N; j++) {
                int sum = arr[i];
                int min = arr[i];
                for (int k = i + 1; k < j; k++) {
                    sum += arr[k];
                    min = Math.min(arr[k], min);
                }
                max = max(max, sum, sum - min);
            }
        }
        return max;
    }

    @Test
    public void test() {
        int min = -10000;
        int max = 10000;
        int tests = 10000;
        int len = 10;
        for (int i = 0; i < tests; i++) {
            int[] arr = RandomArray.generateRandomLengthArray(1, len, min, max);
            int res1 = maximumSum(arr);
            int res2 = maximumSumComparison(arr);
            if (res1 != res2) {
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                maximumSum(arr);
                break;
            }
        }
    }

    @Test
    public void test2() {
        check(new int[]{358, -5143, 7422, -5699, 4252, -4014, 953, -6431, -1448}, 11674);
        check(new int[]{144, -1829, 8192, 7628, -6562, 9309, -3779}, 25129);
        check(new int[]{11, -10, -11, 8, 7, -6, 9, 4, 11, 6, 5, 0}, 50);
        check(new int[]{-1, -1, -1, -1}, -1);
        check(new int[]{1, -2, -2, 3}, 3);
        check(new int[]{1, -2, 0, 3}, 4);
    }

    private void check(int[] arr, int expected) {
        int res = maximumSum(arr);
        if (res != expected) {
            System.out.println("arr = " + Arrays.toString(arr));
            System.out.println("res = " + res);
            System.out.println("expected = " + expected);
        }
    }
}
