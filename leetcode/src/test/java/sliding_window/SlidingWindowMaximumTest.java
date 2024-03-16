package sliding_window;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 59 - I. 滑动窗口的最大值</a>
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组 不为空 的情况下，1 ≤ k ≤ nums.length。
 * <p>
 * 注意：本题与主站 239 题相同：<a href="https://leetcode-cn.com/problems/sliding-window-maximum/">239. 滑动窗口最大值</a>
 *
 * @author zhou.xu
 * @since 2023/6/17 16:49
 */
public class SlidingWindowMaximumTest {

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(1, 10000, -10000, 10000);
            int k = RandomUtils.nextInt(1, nums.length);
            try {
                int[] maxed = maxSlidingWindow(nums, k);
                int[] maxed2 = maxSlidingWindowComparison(nums, k);
                boolean equals = Arrays.equals(maxed, maxed2);
                if (!equals) {
                    System.out.println("nums = " + Arrays.toString(nums));
                    System.out.println("k = " + k);
                    System.out.println("maxed = " + Arrays.toString(maxed));
                    System.out.println("maxed2 = " + Arrays.toString(maxed2));
                    break;
                }
            } catch (Exception e) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("k = " + k);
                throw e;
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        int[] ans = new int[N - k + 1];
        // 滑动窗口法
        // 最大值索引单调递减队列
        LinkedList<Integer> maxQueue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            // 入队规则，如果当前值>=队尾的值，移除队尾，直到找到合适的位置
            // 为什么可以这样做，因为左边的值一旦小于当前值，且左边的值一定比当前值先出队，所以可以抛弃前面的值
            while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] <= nums[i]) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(i);
            // 窗口淘汰值
            // 判断左边界的位置，如果k=1,当i=1时，淘汰掉0位置，i=2,淘汰掉1位置
            if (maxQueue.peekFirst() == i - k) {
                maxQueue.pollFirst();
            }
            if (i >= k - 1) {
                ans[i] = nums[maxQueue.peekFirst()];
            }
        }
        return ans;
    }

    public int[] maxSlidingWindowComparison(int[] nums, int k) {
        // 对数器
        int N = nums.length;
        int[] ans = new int[N - k + 1];
        for (int i = 0; i < ans.length; i++) {
            int max = nums[i];
            for (int j = i + 1; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            ans[i] = max;
        }
        return ans;
    }
}
