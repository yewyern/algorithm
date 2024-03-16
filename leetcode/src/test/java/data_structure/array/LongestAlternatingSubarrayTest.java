package data_structure.array;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/longest-even-odd-subarray-with-threshold/">2760. 最长奇偶子数组</a>
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 threshold 。
 * <p>
 * 请你从 nums 的子数组中找出以下标 l 开头、下标 r 结尾 (0 <= l <= r < nums.length) 且满足以下条件的 最长子数组 ：
 * <p>
 * nums[l] % 2 == 0
 * 对于范围 [l, r - 1] 内的所有下标 i ，nums[i] % 2 != nums[i + 1] % 2
 * 对于范围 [l, r] 内的所有下标 i ，nums[i] <= threshold
 * 以整数形式返回满足题目要求的最长子数组的长度。
 * <p>
 * 注意：子数组 是数组中的一个连续非空元素序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,2,5,4], threshold = 5
 * 输出：3
 * 解释：在这个示例中，我们选择从 l = 1 开始、到 r = 3 结束的子数组 => [2,5,4] ，满足上述条件。
 * 因此，答案就是这个子数组的长度 3 。可以证明 3 是满足题目要求的最大长度。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2], threshold = 2
 * 输出：1
 * 解释：
 * 在这个示例中，我们选择从 l = 1 开始、到 r = 1 结束的子数组 => [2] 。
 * 该子数组满足上述全部条件。可以证明 1 是满足题目要求的最大长度。
 * 示例 3：
 * <p>
 * 输入：nums = [2,3,4,5], threshold = 4
 * 输出：3
 * 解释：
 * 在这个示例中，我们选择从 l = 0 开始、到 r = 2 结束的子数组 => [2,3,4] 。
 * 该子数组满足上述全部条件。
 * 因此，答案就是这个子数组的长度 3 。可以证明 3 是满足题目要求的最大长度。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 1 <= threshold <= 100
 *
 * @author xuzhou
 * @since 2023/7/5 10:19
 */
public class LongestAlternatingSubarrayTest {

    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int l = 0, r = 0, N = nums.length;
        int max = 0;
        while (l < N) {
            // 3个条件
            // 1、nums[l] % 2 == 0
            // 2、对于范围 [l, r - 1] 内的所有下标 i ，nums[i] % 2 != nums[i + 1] % 2
            // 3、对于范围 [l, r] 内的所有下标 i ，nums[i] <= threshold
            while (l < N && (nums[l] > threshold || (nums[l] & 1) == 1)) {
                l++;
            }
            if (l >= N) {
                break;
            }
            r = l + 1;
            while (r < N && nums[r] <= threshold && nums[r] % 2 != nums[r - 1] % 2) {
                r++;
            }
            max = Math.max(max, r - l);
            l = r;
        }
        return max;
    }

    public int longestAlternatingSubarray2(int[] nums, int threshold) {
        int max = 0;
        int N = nums.length;
        for (int l = 0; l < N; l++) {
            if (nums[l] > threshold || nums[l] % 2 != 0) {
                continue;
            }
            for (int r = l; r < N; r++) {
                boolean flag = true;
                for (int i = l + 1; i <= r; i++) {
                    if (nums[i] > threshold || nums[i] % 2 == nums[i - 1] % 2) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    max = Math.max(max, r - l + 1);
                }
            }
        }
        return max;
    }



    @Test
    public void test() {
        int tests = 100000;
        for (int t = 0; t < tests; t++) {
            int[] nums = RandomArray.generateRandomLengthArray(1, 100, 1, 101);
            int threshold = RandomUtils.nextInt(1, 101);
            int res = longestAlternatingSubarray(nums, threshold);
            int res2 = longestAlternatingSubarray2(nums, threshold);
            if (res != res2) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("threshold = " + threshold);
                System.out.println("res = " + res);
                System.out.println("res2 = " + res2);
                longestAlternatingSubarray2(nums, threshold);
                break;
            }
        }
    }
}
