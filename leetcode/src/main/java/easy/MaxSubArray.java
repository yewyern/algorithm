package easy;

import utils.ArrayUtils;
import utils.TestUtils;

class MaxSubArray {

    /**
     * <p> 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * <p> 示例:
     * <p> 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * <p> 输出: 6
     * <p> 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * <p> 进阶:
     * <p> 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     */

    /**
     * <p> 贪心算法：
     * <p> 从第二个数开始计算，把当前数字+前面数字的最大和，与当前数字比较，如果小于当前数字，则当前数字为目前的最大和
     * <p> 把最大和与当前的最大值比较，如果大于最大值，则最大值为当前的最大和
     * <p> 当前的最大和一定是包含当前值的，最大值则不一定
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
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

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        // -2, 1, 1,
        maxSubArray.test(-2, 1, -3, 4, -1, 2, 1, -5, 4);
        maxSubArray.test(-2);
        maxSubArray.test(-2, 1);
        testRandom(1000);
    }

    public static void testRandom(int times) {
        MaxSubArray maxSubArray = new MaxSubArray();
        for (int i = 3; i <= times; i++) {
            int[] nums = ArrayUtils.newRandomArray(i, -100, 100);
            TestUtils.test(() -> maxSubArray.maxSubArray(nums), i);
        }
    }

    public void test(int... nums) {
        TestUtils.test(() -> maxSubArray(nums), -1);
    }
}