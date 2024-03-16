package search;

import utils.RandomArray;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/">4. 寻找两个正序数组的中位数</a>
 * 有2个有序数组，取最中间的数
 * 如果是[1,2],[3]，总个数是奇数，返回2
 * 如果是[1,2],[3,4]，总个数是偶数，返回2.5
 *
 * @author xuzhou
 * @since 2021/4/27 15:58
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        test(new int[]{2, 7, 11, 25, 28, 31, 37, 38}, new int[]{4, 39, 41});
        for (int i = 0; i < 10000; i++) {
            int[] arr1 = RandomArray.generateRandomLengthSortedArray(20, 50);
            int[] arr2 = RandomArray.generateRandomLengthSortedArray(20, 50);
            test(arr1, arr2);
        }
    }

    private static void test(int[] arr1, int[] arr2) {
//        System.out.println("arr1 = " + Arrays.toString(arr1));
//        System.out.println("arr2 = " + Arrays.toString(arr2));
        double median = findMedianSortedArrays(arr1, arr2);
        double median2 = findMedianSortedArrays2(arr1, arr2);
        if (median != median2) {
            System.out.println("arr1 = " + Arrays.toString(arr1));
            System.out.println("arr2 = " + Arrays.toString(arr2));
            System.out.println("median = " + median);
            System.out.println("median2 = " + median2);
            System.out.println("-------------------------");
        }
    }

    // 第一种，两个数组长度相加
    // 2个指针，在2个array上走，找中间数的位置，如果数组长度和是偶数，找(m+n)/2，(m+n)/2-1
    // 如果数组长度和是奇数，找(m+n)/2
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] longs = nums1.length >= nums2.length ? nums1 : nums2;
        int[] shorts = nums1.length >= nums2.length ? nums2 : nums1;
        int len = longs.length + shorts.length;
        if (len == 0) {
            return 0;
        }
        boolean isOdd = (len & 1) == 1;// 是不是奇数
        if (shorts.length == 0) {
            if (isOdd) {
                return longs[len / 2];
            }
            return (double) (longs[len / 2] + longs[len / 2 - 1]) / 2;
        }
        if (isOdd) {
            return findKth(longs, shorts, len / 2 + 1);
        }
        return (double) (findKth(longs, shorts, len / 2) + findKth(longs, shorts, len / 2 + 1)) / 2;
    }

    // 查找第k个数
    public static int findKth(int[] arr1, int[] arr2, int k) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length >= arr2.length ? arr2 : arr1;
        int l = longs.length;
        int s = shorts.length;
        if (k <= s) {
            // k小于s时，肯定在2个数组都是前k个有可能
            return findUpMedian(longs, 0, k - 1, shorts, 0, k - 1);
        }
        if (k > l) {
            // 如果数组如下，数字代表索引位置，要找第10小的数
            // A[1, 2, 3, 4, 5]
            // B[1',2',3',4',5',6',7',8']
            // 1',2',3',4'不可能，1不可能，共排除了5个数
            // 注意：A还剩4个，B还剩4个，等长，但此时寻找的上中位数是剩下8个数的第4小，5+4，只是整体的第9小
            // 此时应该先查看A[2]有没有可能，B[5']有没有可能，如果是直接返回，不是，则排除掉7个，剩下是第3小，7+3=10
            if (longs[k - s - 1] >= shorts[s - 1]) {
                return longs[k - s - 1];
            }
            if (shorts[k - l - 1] >= longs[l - 1]) {
                return shorts[k - l - 1];
            }
            return findUpMedian(longs, k - s, l - 1, shorts, k - l, s - 1);
        }
        if (longs[k - s - 1] > shorts[s - 1]) {
            return longs[k - s - 1];
        }
        return findUpMedian(longs, k - s, k - 1, shorts, 0, s - 1);
    }

    // 查找上中位数，数组A的取值范围是l1,r1，数组B的取值范围是l2,r2，且数组A与数组B的范围必须等长
    public static int findUpMedian(int[] A, int l1, int r1, int[] B, int l2, int r2) {
        int mid1 = 0;
        int mid2 = 0;
        while (l1 < r1) {
            mid1 = (l1 + r1) / 2;
            mid2 = (l2 + r2) / 2;
            // 如果两个数组的中点值相等，则该数即是上中位数
            // 如：A[1,2,3,4],B[0,2,4,5]，上中位是第4小，是2
            // 如：B[1,2,3,4，5],B[0,2,3,4,5]，上中位是第5小，是3
            if (A[mid1] == B[mid2]) {
                return A[mid1];
            }
            // 两个中位数不等时，如果是偶数长度，较小的那个数不可能是上中位数
            // 如：A[1,2,3,4],B[1,3,4,5]，2是第3小
            // 如果是奇数长度，较大的那个数，不可能是上中位数
            // 如：A[1,3,4,5,6],B[1,2,3,4,5],4是第6小
            if (((r1 - l1 + 1) & 1) == 1) {
                // 长度是奇数
                if (A[mid1] > B[mid2]) {
                    // 如果较小的中位数能干过对方的上一个，说明自己就是第5小，上中位数
                    // 如：A[1,3,4,5,6],B[1,2,3,4,5],A[2]>B[2] && B[2]>=A[1]
                    if (B[mid2] >= A[mid1 - 1]) {
                        return B[mid2];
                    }
                    r1 = mid1 - 1;
                    l2 = mid2 + 1;
                } else {
                    // A小
                    if (A[mid1] >= B[mid2 - 1]) {
                        return A[mid1];
                    }
                    l1 = mid1 + 1;
                    r2 = mid2 - 1;
                }
            } else {
                // 长度是偶数
                if (A[mid1] < B[mid2]) {
                    l1 = mid1 + 1;
                    r2 = mid2;
                } else {
                    l2 = mid2 + 1;
                    r1 = mid1;
                }
            }
        }
        return Math.min(A[l1], B[l2]);
    }

    // 第二种，两个数组有序的，数一遍就找到中位数，时间复杂度，次数m+n/2，时间复杂度O(n)
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len == 0) {
            return 0;
        }
        boolean isOdd = (len & 1) == 1;// 是不是奇数
        double res = 0;
        int a = 0, b = 0, m = len / 2;
        while (a < nums1.length && b < nums2.length && m >= 0) {
            if (nums1[a] <= nums2[b]) {
                if (m == 0 || (!isOdd && m == 1)) {
                    res += nums1[a];
                }
                a++;
            } else {
                if (m == 0 || (!isOdd && m == 1)) {
                    res += nums2[b];
                }
                b++;
            }
            m--;
        }
        if (m >= 0) {
            int[] temp = a < nums1.length ? nums1 : nums2;
            int k = a < nums1.length ? a : b;
            while (m >= 0) {
                if (m == 0 || (!isOdd && m == 1)) {
                    res += temp[k];
                }
                k++;
                m--;
            }
        }
        return isOdd ? res : res / 2;
    }
}
