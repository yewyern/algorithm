package search;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/find-peak-element/">162. 寻找峰值</a>
 * @author xuzhou
 * @since 2024/1/8 14:42
 */
public class FindPeakElementTest {

    @Test
    public void test() {
        System.out.println(findPeakElement(new int[] {1, 2, 3, 1}));
    }

    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1 || nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        int l = 1, r = n - 2;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (nums[m] > nums[m - 1] && nums[m] > nums[m + 1]) {
                return m;
            }
            if (nums[m] > nums[m - 1]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }
}
