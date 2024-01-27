package search.binary;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/valid-triangle-number/">611. 有效三角形的个数</a>
 *
 * @author zhou.xu
 * @since 2024/1/27 22:05
 */
public class ValidTriangleNumberTest {

    public int triangleNumber(int[] nums) {
        // 双指针
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = n - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    count += r - l;
                    r--;
                } else {
                    l++;
                }
            }
        }
        return count;
    }

    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = n - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                l = binarySearch(nums, l, r, nums[i] - nums[r]);
                count += r - l;
                r--;
            }
        }
        return count;
    }

    private int binarySearch(int[] a, int from, int to, int key) {
        while (from < to) {
            int m = (from + to) >> 1;
            if (a[m] > key) {
                to = m;
            } else {
                from = m + 1;
            }
        }
        return from;
    }
}
