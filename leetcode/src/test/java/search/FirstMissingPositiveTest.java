package search;

import org.junit.Test;
import utils.RandomArray;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/first-missing-positive/">41. 缺失的第一个正数</a>
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * <p>
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * @author xuzhou
 * @since 2022/11/9 18:23
 */
public class FirstMissingPositiveTest {

    public int firstMissingPositive(int[] nums) {
        // 额外空间复杂度O1的算法
        int N = nums.length;
        // 1、所有非正数改成N + 1
        for (int i = 0; i < N; i++) {
            if (nums[i] < 1) {
                nums[i] = N + 1;
            }
        }
        // 2、<=N的数i,将nums[i - 1]改成负数
        for (int i = 0; i < N; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (index < N) {
                // nums[i] 有可能在循环过程中变成负数，所以在计算索引时需要取绝对值
                nums[index] = -Math.abs(nums[index]);
            }
        }
        // 3、返回第一个非负数所在的索引i+1
        for (int i = 0; i < N; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return N + 1;
    }

    public int firstMissingPositiveSwap(int[] nums) {
        // 额外空间复杂度O1的算法2
        // 交换算法：nums[i]如果小于等于N,和nums[nums[i]-1]交换
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            while (nums[i] > 0 && nums[i] <= N && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < N; i++) {
            if (i != nums[i] - 1) {
                return i + 1;
            }
        }
        return N + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int firstMissingPositiveComparison(int[] nums) {
        // 计数排序算法
        // 数组中如果全部是正数，最大的结果是nums.length + 1
        int N = nums.length + 1;
        int[] pos = new int[N];
        for (int x : nums) {
            if (x > 0 && x < N) {
                pos[x] = 1;
            }
        }
        for (int i = 1; i < N; i++) {
            if (pos[i] == 0) {
                return i;
            }
        }
        return N;
    }

    public int firstMissingPositiveComparison2(int[] nums) {
        // 暴力方法，先排序
        Arrays.sort(nums);
        int ans = 1;
        for (int x : nums) {
            if (x >= ans) {
                if (ans == x) {
                    ans++;
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int tests = 100000;
        int maxLen = 5000;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < tests; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(1, maxLen, min, max);
            int missingPositive = firstMissingPositiveSwap(Arrays.copyOf(nums, nums.length));
            int missingPositiveComparison = firstMissingPositiveComparison(Arrays.copyOf(nums, nums.length));
            if (missingPositive != missingPositiveComparison) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("missingPositive = " + missingPositive);
                System.out.println("missingPositiveComparison = " + missingPositiveComparison);
                break;
            }
        }
    }
}
