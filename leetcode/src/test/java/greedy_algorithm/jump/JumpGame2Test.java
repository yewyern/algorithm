package greedy_algorithm.jump;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/jump-game-ii/">45. 跳跃游戏 II</a>
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 * <p>
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 10000
 * 0 <= nums[i] <= 1000
 *
 * @author zhou.xu
 * @since 2022/11/11 13:47
 */
public class JumpGame2Test {

    public int jump(int[] nums) {
        // dp
        int N = nums.length;
        if (N == 1) {
            return 0;
        }
        int[] steps = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (i + nums[i] <= max) {
                continue;
            }
            int temp = i + nums[i];
            if (temp >= N - 1) {
                return steps[i] + 1;
            }
            for (int j = max + 1; j <= temp; j++) {
                steps[j] = steps[i] + 1;
            }
            max = temp;
        }
        return 1;
    }

    public int jumpGreedy(int[] nums) {
        // 贪心算法
        int N = nums.length;
        int max = 0; // 这一次我能跳到的最远距离
        int lastMax = 0; // 上一次我能跳到的最远距离
        int step = 0; // 这一次是第几次
        for (int i = 0; i < N - 1; i++) {
            max = Math.max(max, nums[i] + i);
            if (i == lastMax) {
                // 已经到上一步的极限了
                lastMax = max;
                step++;
            }
        }
        return step;
    }

    @Test
    public void test() {
        System.out.println(jumpGreedy(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jumpGreedy(new int[]{2, 3, 0, 1, 4}));
        System.out.println(jumpGreedy(new int[]{2, 3, 1}));
        System.out.println(jumpGreedy(new int[]{7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3}));
    }
}
