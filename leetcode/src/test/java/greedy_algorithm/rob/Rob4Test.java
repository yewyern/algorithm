package greedy_algorithm.rob;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/house-robber-iv">2560. 打家劫舍 IV</a>
 *
 * @author xuzhou
 * @since 2023/9/19 14:03
 */
public class Rob4Test {

    public int minCapability(int[] nums, int k) {
        // TODO 会超时
        if (k == 1) {
            return min(nums);
        }
        int min = Integer.MAX_VALUE;
        int[] inclusive = new int[1];
        int[] exclusive = new int[1];
        for (int num : nums) {
            int[] currExclusive = mergeMin(inclusive, exclusive);
            int n = Math.min(k + 1, exclusive.length + 1);
            int[] currInclusive = new int[n];
            for (int i = 0; i < n - 1; i++) {
                currInclusive[i + 1] = Math.max(exclusive[i], num);
            }
            if (n == k + 1) {
                min = Math.min(currInclusive[k], min);
            }
            inclusive = currInclusive;
            exclusive = currExclusive;
        }
        return min;
    }

    private int min(int[] nums) {
        int min = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }

    private int[] mergeMin(int[] a, int[] b) {
        if (a.length < b.length) {
            return mergeMin(b, a);
        }
        int[] res = Arrays.copyOf(a, a.length);
        for (int i = 0; i < b.length; i++) {
            res[i] = Math.min(res[i], b[i]);
        }
        return res;
    }
}
