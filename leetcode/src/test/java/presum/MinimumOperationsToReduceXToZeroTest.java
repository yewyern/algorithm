package presum;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

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
        int target = sum - x;
        // 求和=target的最大子序列
        return -1;
    }

    public int minOperationsComparison(int[] nums, int x) {
        int N = nums.length;
        int min = N + 1;
        // 左边前缀和
        Map<Integer, Integer> map = new HashMap<>(N);
        int[] leftPreSums = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                leftPreSums[i] = nums[i];
            } else {
                leftPreSums[i] = nums[i] + leftPreSums[i - 1];
            }
            if (leftPreSums[i] == x) {
                min = i + 1;
            } else if (leftPreSums[i] > x) {
                break;
            }
            map.put(leftPreSums[i], i);
        }
        // 右边前缀和
        int[] rightPreSums = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            if (i == N - 1) {
                rightPreSums[i] = nums[i];
            } else {
                rightPreSums[i] = nums[i] + rightPreSums[i + 1];
            }
            if (rightPreSums[i] == x) {
                min = Math.min(min, N - i);
                continue;
            }
            if (rightPreSums[i] > x) {
                break;
            }
            int diff = x - rightPreSums[i];
            if (map.containsKey(diff)) {
                min = Math.min(min, map.get(diff) + 1 + N - i);
            }
        }
        return min > N ? -1 : min;
    }

    private void minOperationsTest(int x, int... nums) {
        int minOperations = minOperationsComparison(nums, x);
        System.out.println("minOperations = " + minOperations);
    }

    @Test
    public void test() {
        minOperationsTest(5, 1, 1, 4, 2, 3);
        minOperationsTest(4, 5, 6, 7, 8, 9);
        minOperationsTest(10, 3, 2, 20, 1, 1, 3);
    }
}
