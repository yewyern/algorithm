package greedy_algorithm.jump;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/jump-game/">55. 跳跃游戏</a>
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 30000
 * 0 <= nums[i] <= 100000
 *
 * @author zhou.xu
 * @since 2022/11/11 12:07
 */
public class JumpGameTest {

    public boolean canJump(int[] nums) {
        int N = nums.length;
        if (N == 1) {
            return true;
        }
        int max = 0;
        int cur = 0;
        while (cur <= max && max < N) {
            max = Math.max(max, nums[cur] + cur);
            cur++;
        }
        return max >= N;
    }

    @Test
    public void test() {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
    }

}
