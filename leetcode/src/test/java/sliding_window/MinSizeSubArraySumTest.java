package sliding_window;

/**
 * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum">209. 长度最小的子数组</a>
 * @author xuzhou
 * @since 2023/11/22 15:36
 */
public class MinSizeSubArraySumTest {

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int len = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int sum = 0;
        while (r < n) {
            sum += nums[r];
            while (l <= r && sum >= target) {
                len = Math.min(len, r - l + 1);
                sum -= nums[l++];
            }
            r++;
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}
