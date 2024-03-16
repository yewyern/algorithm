package search;

/**
 * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array-ii">81. 搜索旋转排序数组 II</a>
 * @author xuzhou
 * @since 2023/10/9 17:18
 */
public class SearchInRotatedSortedArray2Test {

    public boolean search(int[] nums, int target) {
        return process(nums, target, 0, nums.length - 1);
    }

    private boolean process(int[] nums, int target, int l, int r) {
        if (nums[l] == target || nums[r] == target) {
            return true;
        }
        if (r - l < 2) {
            return false;
        }
        if (nums[l] < nums[r] && (nums[l] > target || nums[r] < target)) {
            return false;
        }
        int m = (l + r) / 2;
        return process(nums, target, l, m - 1) || process(nums, target, m, r);
    }
}
