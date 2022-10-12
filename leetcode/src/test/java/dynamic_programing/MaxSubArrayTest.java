package dynamic_programing;

import java.util.Arrays;
import org.junit.Test;
import utils.RandomArray;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 *
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 10^4
 * -10^5 <= nums[i] <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode-cn.com/problems/maximum-subarray">...</a>
 */
public class MaxSubArrayTest {

    public int maxSubArray(int[] nums) {
        // 贪心算法，如果之前的和值是大于0就要，不是就不要
        // 时间复杂度：O(n)
        // 空间复杂度：O(1)
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int sum = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public int maxSubArrayDp(int[] nums) {
        // 动态规划
        // 时间复杂度：O(n)
        // 空间复杂度：O(1)
        int pre = 0, max = nums[0];
        for (int x : nums) {
            pre = pre > 0 ? pre + x : x;
            max = Math.max(max, pre);
        }
        return max;
    }

    public int maxSubArrayComparison(int[] nums) {
        // 暴力方法
        int N = nums.length;
        if (N == 0) {
            return 0;
        }
        int max = nums[0];
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += nums[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    @Test
    public void testByComparison() {
        // -2, 1, 1,
        testByComparison(-2, 1, -3, 4, -1, 2, 1, -5, 4);
        testByComparison(-2);
        testByComparison(-2, 1);
        int test = 10000;
        for (int i = 3; i <= test; i++) {
            int[] nums = RandomArray.generateRandomLengthSortedArray(0, 100, -100, 100);
            testByComparison(nums);
        }
    }

    public void testByComparison(int... nums) {
        int maxSubArray = maxSubArray(nums);
        int comparison = maxSubArrayComparison(nums);
        if (maxSubArray != comparison) {
            System.out.println("nums = " + Arrays.toString(nums));
            System.out.println("maxSubArray = " + maxSubArray);
            System.out.println("comparison = " + comparison);
            System.out.println("--------------------------");
        }
    }
}