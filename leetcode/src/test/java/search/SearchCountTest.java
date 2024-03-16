package search;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/description/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 53 - I. 在排序数组中查找数字 I</a>
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums 是一个非递减数组
 * -10^9 <= target <= 10^9
 * <p>
 * <p>
 * 注意：本题与主站 34 题相同（仅返回值不同）：<a href="https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/">34. 在排序数组中查找元素的第一个和最后一个位置</a>
 *
 * @author xuzhou
 * @since 2023/6/16 17:18
 */
public class SearchCountTest {

    @Test
    public void test() {
        int count = 0;
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateRandomLengthSortedArray(100000, 1000000);
            int target = RandomUtils.nextInt(1000000);
            int search = search(nums, target);
            int searchComparison = searchComparison(nums, target);
            if (searchComparison != search) {
                System.out.println(Arrays.toString(nums) + ", target = " + target + ", search = " + search + ", searchComparison = " + searchComparison);
                break;
            }
            count++;
        }
        System.out.println("count = " + count);
    }

    public int search(int[] nums, int target) {
        // 二分法查找左右边界，再用右边界-左边界
        int N = nums.length;
        int l = 0, r = N - 1;
        int mid = 0;
        while (l < r) {
            mid = (l + r) >> 1;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                break;
            }
        }
        if (l == r && nums[l] != target) {
            return 0;
        }
        int l1 = mid;
        while (l < l1) {
            int m = (l + l1) >> 1;
        }
        return r - l + 1;
    }

    public int searchComparison(int[] nums, int target) {
        // 最简单解法，遍历，时间复杂度O(n)
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
            } else if (nums[i] > target) {
                break;
            }
        }
        return count;
    }
}
