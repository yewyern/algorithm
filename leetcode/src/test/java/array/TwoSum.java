package array;

import java.util.Arrays;
import org.junit.Test;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 *
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *
 * @author zhou.xu
 * @date 2020/12/1 16:50
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int diff = target - nums[l];
            if (diff == nums[r]) {
                return new int[]{nums[l], nums[r]};
            }
            if (diff > nums[r]) {
                l++;
                int mid = l + ((r - l) >> 1);
                if (nums[mid] + nums[r] < target) {
                    l = mid + 1;
                }
            } else {
                r--;
                int mid = l + ((r - l) >> 1);
                if (nums[mid] + nums[l] > target) {
                    r = mid - 1;
                }
            }
        }
        return null;
    }

    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int diff = target - nums[l];
            if (diff == nums[r]) {
                return new int[]{nums[l], nums[r]};
            }
            if (diff > nums[r]) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int N = nums.length - 1;
        for (int i = 0; i <= N; i++) {
            if (target < nums[i]) {
                return null;
            }
            int l = i + 1, r = N;
            int d = target - nums[i];
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (nums[mid] == d) {
                    return new int[]{nums[i], nums[mid]};
                }
                if (nums[mid] < d) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                    N = r;
                }
            }
        }
        return null;
    }

    public void test(int target, int... nums) {
        int[] res = twoSum(nums, target);
        System.out.println("nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("res = " + Arrays.toString(res));
        System.out.println("--------------------------------");
    }

    @Test
    public void test() {
        test(9, 2, 7, 11, 15);
        test(40, 10, 26, 30, 31, 47, 60);
    }
}
