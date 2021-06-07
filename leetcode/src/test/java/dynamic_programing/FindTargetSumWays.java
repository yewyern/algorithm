package dynamic_programing;

import org.junit.Test;

/**
 * 494. 目标和
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 *
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：<a>https://leetcode-cn.com/problems/target-sum</a>
 *
 * @author xuzhou
 * @date 2021/6/7 13:46
 */
public class FindTargetSumWays {

    public int findTargetSumWays(int[] nums, int target) {
        // 深度优先遍历
        // 时间复杂度：O(2^n)
        // 空间复杂度：O(n),递归深度n
        return findTargetSumWays(nums, target, 0, 0);
    }

    public int findTargetSumWays(int[] nums, int target, int level, int presum) {
        if (level == nums.length) {
            return presum == target ? 1 : 0;
        }
        return findTargetSumWays(nums, target, level + 1, presum + nums[level])
            + findTargetSumWays(nums, target, level + 1, presum - nums[level]);
    }

    public int findTargetSumWaysDp(int[] nums, int target) {
        // TODO 动态规划
        return 0;
    }

    @Test
    public void test() {
        int targetSumWays = findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println("targetSumWays = " + targetSumWays);
        int targetSumWays2 = findTargetSumWays(new int[]{2, 1}, 3);
        System.out.println("targetSumWays = " + targetSumWays2);
    }
}
