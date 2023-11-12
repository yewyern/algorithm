package dynamic_programing;

import org.junit.Test;
import utils.RandomArray;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/maximum-sum-circular-subarray/">918. 环形子数组的最大和</a>
 * <p>
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * <p>
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * <p>
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 * <p>
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 * <p>
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 *
 * @author xuzhou
 * @since 2023/7/20 17:44
 */
public class MaxSubarraySumCircularTest {

    @Test
    public void test() {
        for (int i = 0; i < 100000; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(1, 10, -30000, 30000);
            int res1 = maxSubarraySumCircular1(nums);
            int res2 = maxSubarraySumCircular2(nums);
            if (res1 != res2) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                maxSubarraySumCircular1(nums);
                break;
            }
        }
    }

    public int maxSubarraySumCircular(int[] nums) {
        // 滑动窗口+单调队列
        int N = nums.length;
        int len = 2 * N;
        LinkedList<Integer> minQueue = new LinkedList<>();
        minQueue.addLast(0);
        int[] sum = new int[len];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + (i < N ? nums[i] : nums[i - N]);
            max = Math.max(sum[i] - sum[minQueue.peekFirst()], max);
            while (!minQueue.isEmpty() && sum[minQueue.peekLast()] >= sum[i]) {
                minQueue.pollLast();
            }
            minQueue.addLast(i);
            if (minQueue.peekFirst() == i - N) {
                minQueue.pollFirst();
            }
        }
        return max;
    }

    public int maxSubarraySumCircular1(int[] nums) {
        // 暴力方法，取每个起点，每个终点的最大值
        int n = nums.length;
        int max = nums[0];
        int total = 0;
        for (int num : nums) {
            total += num;
            max = Math.max(max, num);
        }
        max = Math.max(max, total);
        for (int i = 0; i < n - 1; i++) {
            int sum = nums[i];
            max = Math.max(max, total - sum);
            for (int j = i + 1; j < n - 1; j++) {
                sum += nums[j];
                max = Math.max(max, Math.max(total - sum, sum));
            }
        }
        return max;
    }

    public int maxSubarraySumCircular2(int[] nums) {
        // 暴力方法，取每个起点，每个终点的最大值
        int N = nums.length;
        int max = nums[0];
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                int p = i + j;
                sum += p >= N ? nums[p - N] : nums[p];
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}
