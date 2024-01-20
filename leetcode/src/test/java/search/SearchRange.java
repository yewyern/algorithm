package search;


/**
 * <a href="https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array">34. 在排序数组中查找元素的第一个和最后一个位置</a>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * @author zhou.xu
 * @since 2020/12/1 15:14
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return new int[]{-1, -1};
        }
        int l = binarySearch(nums, target, 0, n - 1, true);
        if (nums[l] != target) {
            return new int[]{-1, -1};
        }
        int r = binarySearch(nums, target, l + 1, n - 1, false);
        return new int[]{l, r};
    }

    private int binarySearch(int[] nums, int target, int l, int r, boolean left) {
        while (l <= r) {
            int m = (l + r) >> 1;
            if (nums[m] < target) {
                l = m + 1;
            } else if (nums[m] > target) {
                r = m - 1;
            } else if (left) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

}
