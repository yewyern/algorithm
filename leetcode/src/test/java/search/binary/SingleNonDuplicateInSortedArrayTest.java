package search.binary;

/**
 * <a href="https://leetcode.cn/problems/single-element-in-a-sorted-array/">540. 有序数组中的单一元素</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 21:33
 */
public class SingleNonDuplicateInSortedArrayTest {

    public int singleNonDuplicate(int[] nums) {
        // 找到一个位置，这个位置的index是奇数，它左边的数和它不一样
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int l = 0, r = (n >> 1) - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            int i = (m << 1) + 1;
            if (nums[i] != nums[i - 1]) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return nums[l << 1];
    }
}
