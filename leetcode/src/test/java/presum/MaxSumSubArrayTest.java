package presum;

import org.junit.Test;
import utils.RandomArray;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 42. 连续子数组的最大和</a>
 * <p>
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * <p>
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * 注意：本题与主站 53 题相同：<a href="https://leetcode-cn.com/problems/maximum-subarray/">53. 最大子数组和</a>
 *
 * @author xuzhou
 * @since 2023/6/19 17:50
 */
public class MaxSumSubArrayTest {

    public int maxSubArray(int[] nums) {
        int pre = 0, max = Integer.MIN_VALUE;
        for (int n : nums) {
            pre = Math.max(pre + n, n);
            max = Math.max(pre, max);
        }
        return max;
    }

    public int maxSubArrayComparison(int[] nums) {
        // 暴力方法
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < nums.length; l++) {
            for (int r = l; r < nums.length; r++) {
                int sum = nums[l];
                for (int k = l + 1; k <= r; k++) {
                    sum += nums[k];
                }
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(1, 1000, -1000, 1000);
            int sum1 = maxSubArray(nums);
            int sum2 = maxSubArrayComparison(nums);
            if (sum1 != sum2) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("sum1 = " + sum1);
                System.out.println("sum2 = " + sum2);
                break;
            }
        }
    }
}
