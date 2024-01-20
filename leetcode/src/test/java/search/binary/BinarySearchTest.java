package search.binary;

/**
 * <a href="https://leetcode.cn/problems/binary-search">704. 二分查找</a>
 *
 * @author zhou.xu
 * @since 2024/1/20 13:49
 */
public class BinarySearchTest {

    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
