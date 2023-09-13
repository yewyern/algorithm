package dynamic_programing;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/partition-array-for-maximum-sum/">1043. 分隔数组以得到最大和</a>
 * 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 * <p>
 * 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：数组变为 [15,15,15,9,10,10,10]
 * 示例 2：
 * <p>
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 * <p>
 * 输入：arr = [1], k = 1
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^9
 * 1 <= k <= arr.length
 *
 * @author xuzhou
 * @since 2023/9/12 14:48
 */
public class MaxSumAfterPartitioningTest {

    @Test
    public void test() {
        System.out.println(maxSumAfterPartitioning(new int[]{1, 15, 7, 9, 2, 5, 10}, 3));
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        if (k == 1) {
            return sum(arr);
        }
        int n = arr.length;
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < k && i - j >= 0; j++) {
                max = Math.max(max, arr[i - j]);
                pre[i] = Math.max(pre[i], (i - j > 0 ? pre[i - j - 1] : 0) + (j + 1) * max);
            }
        }
        return pre[n - 1];
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        return sum;
    }
}
