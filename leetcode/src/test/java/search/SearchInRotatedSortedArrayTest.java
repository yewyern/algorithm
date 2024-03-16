package search;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/">33. 搜索旋转排序数组</a>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3
 * 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -10000 <= nums[i] <= 10000
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10000 <= target <= 10000
 *
 * @author zhou.xu
 * @since 2022/11/2 23:07
 */
public class SearchInRotatedSortedArrayTest {

    public int search(int[] nums, int target) {
        // 不管旋转点，直接二分，肯定有一边有序，另一边可能有序，也可能是包含旋转点
        // 有序的那边如果能包含target，另一边不用管了，如果不包含，继续二分
        int N = nums.length;
        int l = 0, r = N - 1;
        while (l < r) {
            if (nums[l] == target) {
                return l;
            }
            if (nums[r] == target) {
                return r;
            }
            int mid = (l + r) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] < nums[mid]) {
                // 左边有序
                if (nums[l] < target && target < nums[mid]) {
                    l++;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                    r--;
                }
            } else {
                // 右边有序
                if (nums[mid] < target && target < nums[r]) {
                    l = mid + 1;
                    r--;
                } else {
                    l++;
                    r = mid - 1;
                }
            }
        }
        if (l == r && nums[l] == target) {
            return l;
        }
        return -1;
    }

    public int searchComparison(int[] nums, int target) {
        // 先找旋转点，再二分查找
        int N = nums.length;
        if (nums[0] == target) {
            return 0;
        }
        if (nums[N - 1] == target) {
            return N - 1;
        }
        int l = 0, r = N - 1;
        int rotateIndex = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[r]) {
                l = mid;
            } else {
                r = mid;
            }
            if (l == r - 1) {
                rotateIndex = l;
                break;
            }
        }
        int res = biSearch(nums, target, 0, rotateIndex);
        if (res != -1) {
            return res;
        }
        return biSearch(nums, target, rotateIndex + 1, N - 1);
    }

    public int biSearch(int[] nums, int target, int l, int r) {
        if (l < r && nums[l] > target || nums[r] < target) {
            return -1;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l == r) {
            return nums[l] == target ? l : -1;
        }
        return -1;
    }

    public void searchTest(int target, int... nums) {
        int search = search(nums, target);
        System.out.println("search = " + search);
    }

    @Test
    public void test() {
        searchTest(0, 4, 5, 6, 7, 0, 1, 2);//4
        searchTest(3, 4, 5, 6, 7, 0, 1, 2);//-1
        searchTest(0, 1);//-1
        searchTest(1, 1);//0
    }
}
