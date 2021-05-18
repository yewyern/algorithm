package array;

import java.util.Arrays;
import org.junit.Test;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * @author zhou.xu
 * @date 2020/12/1 15:14
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = binarySearch(nums, target, true, 0, nums.length - 1);
        if (left < 0) {
            return new int[]{-1, -1};
        }
        int right = binarySearch(nums, target, false, left, nums.length - 1);
        return new int[]{left, right};
    }

    private int binarySearch(int[] nums, int target, boolean leftBound, int l, int r) {
        int ans = -1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] > target || (leftBound && nums[mid] >= target)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            if (nums[mid] == target) {
                ans = mid;
            }
        }
        return ans;
    }

    public void test(int target, int... nums) {
        int[] range = searchRange(nums, target);
        System.out.println("nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("range = " + Arrays.toString(range));
        System.out.println("--------------------------------");
    }

    @Test
    public void test() {
        test(8, 5, 7, 7, 8, 8, 10);
        test(6, 5, 7, 7, 8, 8, 10);
        test(0);
    }

}
