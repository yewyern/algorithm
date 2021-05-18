package hard;

import java.util.Arrays;
import org.junit.Test;

/**
 * <p>给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * <p>进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * <p>示例 1：
 * <p>
 * <p>输入：nums1 = [1,3], nums2 = [2]
 * <p>输出：2.00000
 * <p>解释：合并数组 = [1,2,3] ，中位数 2
 * <p>示例 2：
 * <p>
 * <p>输入：nums1 = [1,2], nums2 = [3,4]
 * <p>输出：2.50000
 * <p>解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>示例 3：
 * <p>
 * <p>输入：nums1 = [0,0], nums2 = [0,0]
 * <p>输出：0.00000
 * <p>示例 4：
 * <p>
 * <p>输入：nums1 = [], nums2 = [1]
 * <p>输出：1.00000
 * <p>示例 5：
 * <p>
 * <p>输入：nums1 = [2], nums2 = []
 * <p>输出：2.00000
 *
 * @author zhou.xu
 * @date 2020/10/9 16:18
 */
public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int center = len / 2;
        if (m == 0 || n == 0) {
            int[] temp = m == 0 ? nums2 : nums1;
            if (len % 2 == 1) {
                return temp[center];
            } else {
                return (temp[center] + temp[center - 1]) / 2.0;
            }
        }
        int i = 0, j = 0, k = 0;
        int last = 0, curr = 0;
        while (j < m && k < n && i <= center) {
            last = curr;
            if (nums1[j] <= nums2[k]) {
                curr = nums1[j++];
            } else {
                curr = nums2[k++];
            }
            i++;
        }
        while (j < m && i <= center) {
            last = curr;
            curr = nums1[j++];
            i++;
        }
        while (k < n && i <= center) {
            last = curr;
            curr = nums2[k++];
            i++;
        }
        return len % 2 == 1 ? curr : (last + curr) / 2.0;
    }


    public void test(int[] nums1, int[] nums2) {
        System.out.println("nums1 = " + Arrays.toString(nums1));
        System.out.println("nums2 = " + Arrays.toString(nums2));
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void test() {
        test(new int[]{1, 2}, new int[]{3, 4});
        test(new int[]{1, 2}, new int[]{3});
    }
}
