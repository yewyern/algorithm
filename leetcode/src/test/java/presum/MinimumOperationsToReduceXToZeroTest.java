package presum;

import com.google.common.base.Stopwatch;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

/**
 * <a href="https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero">1658. 将 x 减到 0 的最小操作数</a>
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
 * <p>
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,4,2,3], x = 5
 * 输出：2
 * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 * 示例 2：
 * <p>
 * 输入：nums = [5,6,7,8,9], x = 4
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [3,2,20,1,1,3], x = 10
 * 输出：5
 * 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 * 标签:
 * 数组
 * 哈希表
 * 二分查找
 * 前缀和
 * 滑动窗口
 *
 * @author zhou.xu
 * @since 2022/10/30 21:17
 */
public class MinimumOperationsToReduceXToZeroTest {

    public int minOperations(int[] nums, int x) {
        int N = nums.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }
        if (sum < x) {
            return -1;
        }
        if (sum == x) {
            return N;
        }
        int target = sum - x;
        // 求和=target的最大子序列
        // 滑动窗口法,
        // 时间复杂度：O(n)，双指针最多都走一遍，2n
        // 空间复杂度：O(1)
        int l = 0;
        int r = 0;
        int len = 0;
        sum = nums[0];
        while (true) {
//            System.out.println("l = " + l + ", r = " + r + ", sum = " + sum + ", len = " + len);
            if (sum > target) {
                if (l == nums.length - 1) {
                    break;
                }
                sum -= nums[l++];
            } else {
                if (sum == target) {
                    len = Math.max(len, r - l + 1);
                }
                if (r == nums.length - 1) {
                    break;
                }
                sum += nums[++r];
            }
        }
        return len == 0 ? -1 : N - len;
    }

    public int minOperationsComparison(int[] nums, int x) {
        int N = nums.length;
        int min = N + 1;
        // 左边前缀和
        Map<Integer, Integer> map = new HashMap<>(N * 2);
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            if (sum == x) {
                min = i + 1;
            } else if (sum > x) {
                break;
            }
            map.put(sum, i);
        }
        if (sum < x) {
            return -1;
        }
        // 右边前缀和
        sum = 0;
        for (int i = N - 1; i >= 0; i--) {
            sum += nums[i];
            if (sum == x) {
                min = Math.min(min, N - i);
                continue;
            }
            if (sum > x) {
                break;
            }
            int diff = x - sum;
            if (map.containsKey(diff)) {
                min = Math.min(min, map.get(diff) + 1 + N - i);
            }
        }
        return min > N ? -1 : min;
    }

    private void minOperationsTest(int x, int... nums) {
        int minOperations = minOperations(nums, x);
        System.out.println("minOperations = " + minOperations);
    }

    private void minOperationsComparisonTest(int x, int... nums) {
        int minOperations = minOperationsComparison(nums, x);
        System.out.println("minOperationsComparison = " + minOperations);
    }

    @Test
    public void test() {
        minOperationsTest(5, 1, 1, 4, 2, 3);
        minOperationsTest(4, 5, 6, 7, 8, 9);
        minOperationsTest(134365, 8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309);
        minOperationsComparisonTest(134365, 8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309);

        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            // 1 <= nums.length <= 10^5
            // 1 <= nums[i] <= 10^4
            // 1 <= x <= 10^9
            int[] nums = RandomArray.generateRandomLengthArray(1, 100001, 1, 10001);
            int x = RandomUtils.nextInt(1, 1000000001);
            int minOperations = minOperations(nums, x);
            int minOperationsComparison = minOperationsComparison(nums, x);
            if (minOperations != minOperationsComparison) {
                System.out.println(
                    "nums = " + Arrays.toString(nums) + ", x = " + x + ", minOperations = " + minOperations + ", minOperationsComparison = " + minOperationsComparison);
            }
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(elapsed);
    }
}
