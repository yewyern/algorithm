package greedy_algorithm;


/**
 * <a href="https://leetcode.cn/problems/maximum-sum-with-exactly-k-elements">2656. K 个元素的最大和</a>
 * @author xuzhou
 * @since 2023/11/15 17:29
 */
public class MaximizeSumWithExactlyKElements {

    public int maximizeSum(int[] nums, int k) {
        int max = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max * k + k * (k - 1) / 2;
    }
}
