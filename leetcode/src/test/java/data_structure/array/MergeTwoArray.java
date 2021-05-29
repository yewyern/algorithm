package data_structure.array;

import utils.ArrayUtils;

/**
 * <p>给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 num1 成为一个有序数组。
 * <p>
 * <p>说明:
 * <p>
 * <p>初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * <p>你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * <p> 
 * <p>
 * <p>示例:
 * <p>
 * <p>输入:
 * <p>nums1 = [1,2,3,0,0,0], m = 3
 * <p>nums2 = [2,5,6],       n = 3
 * <p>
 * <p>输出: [1,2,2,3,5,6]
 * <p>
 */
class MergeTwoArray {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0}, nums2 = {2, 5, 6};
        int m = 3, n = 3;
        MergeTwoArray mergeTwoArray = new MergeTwoArray();
        mergeTwoArray.merge(nums1, m, nums2, n);
        System.out.println("nums1 = " + ArrayUtils.toString(nums1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        m = m - 1;
        n = n - 1;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[index--] = nums1[m--];
            } else {
                nums1[index--] = nums2[n--];
            }
        }
        while (n >= 0) {
            nums1[index--] = nums2[n--];
        }
    }
}