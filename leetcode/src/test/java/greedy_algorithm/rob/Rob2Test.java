package greedy_algorithm.rob;

import org.junit.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/house-robber-ii/">213. 打家劫舍 II</a>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：3
 *
 * @author xuzhou
 * @since 2023/6/2 10:14
 */
public class Rob2Test {

    public int rob(int[] nums) {
        int N = nums.length;
        if (N == 1) {
            return nums[0];
        }
        if (N == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 包含第0家的
        int[] containFirst = new int[N];
        containFirst[0] = nums[0];
        containFirst[1] = nums[0];
        // 不包含第0家的
        int[] notContainFirst = new int[N];
        notContainFirst[1] = nums[1];
        for (int i = 2; i < N; i++) {
            if (i < N - 1) {
                containFirst[i] = Math.max(containFirst[i - 1], nums[i] + containFirst[i - 2]);
            }
            notContainFirst[i] = Math.max(notContainFirst[i - 1], nums[i] + notContainFirst[i - 2]);
        }
        return Math.max(containFirst[N - 2], notContainFirst[N - 1]);
    }

    private void check(int[] nums, int expected) {
        int rob = rob(nums);
        if (rob != expected) {
            System.out.println("nums = " + Arrays.toString(nums) + ", expected = " + expected + ", rob = " + rob);
        }
    }

    @Test
    public void test() {
        check(new int[]{2, 3, 2}, 3);
        check(new int[]{1, 2, 3, 1}, 4);
        check(new int[]{1, 2, 3}, 3);
    }
}
