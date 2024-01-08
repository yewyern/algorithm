package search;

/**
 * <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array">153. 寻找旋转排序数组中的最小值</a>
 * @author xuzhou
 * @since 2024/1/8 14:56
 */
public class FindMinInRotatedSortedArrayTest {

    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            if (r <= l + 1) {
                return Math.min(nums[l], nums[r]);
            }
            if (nums[l] < nums[r]) {
                // 整体有序
                return nums[l];
            }
            int m = (l + r) >> 1;
            if (nums[l] < nums[m]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return 0;
    }
}
