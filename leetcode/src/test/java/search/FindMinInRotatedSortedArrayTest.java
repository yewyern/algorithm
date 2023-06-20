package search;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 11. 旋转数组的最小数字</a>
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
 * <p>
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numbers = [3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：numbers = [2,2,2,0,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == numbers.length
 * 1 <= n <= 5000
 * -5000 <= numbers[i] <= 5000
 * numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * 注意：本题与主站 154 题相同：<a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/">154. 寻找旋转排序数组中的最小值 II</a>
 *
 * @author xuzhou
 * @since 2023/6/20 9:50
 */
public class FindMinInRotatedSortedArrayTest {

    public int findMin(int[] nums) {
        // 二分法查找旋转点
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (r == l + 1) {
                return Math.min(nums[l], nums[r]);
            }
            if (nums[l] < nums[r]) {
                // 整体有序
                return nums[l];
            }
            int m = l + ((r - l) >> 1);
            if (nums[l] < nums[m]) {
                // 这边是升序的，旋转点在另外一边
                l = m + 1;
            } else if (nums[l] > nums[m]) {
                // 这边存在降序的，旋转点就这这边
                // 注意: m可能是旋转点，需要包含m
                r = m;
            } else {
                // 首尾相等，存在2种可能
                // [2,2,2,2,2,3,2,2,2]
                // 1、如果[l,m]都是相等的，则旋转点不在这边
                // 2、如果[l,m]存在不相等的起始点i，则旋转点是在[i,m]范围内
                // 如果第一个不相等的数小于该数，即旋转点是i，可以直接返回
                // 如果第一个不相等的数大于该数，即旋转点是i右边，即[i+1,m]
                boolean isAllEqual = true;
                for (int i = l + 1; i < m; i++) {
                    if (nums[i] < nums[l]) {
                        return nums[i];
                    }
                    if (nums[i] > nums[l]) {
                        isAllEqual = false;
                        l = i + 1;
                        r = m;
                        break;
                    }
                }
                if (isAllEqual) {
                    l = m + 1;
                }
            }
        }
        return nums[r];
    }

    public int findMinComparison(int[] nums) {
        // 暴力方法
        int min = Integer.MAX_VALUE;
        for (int x : nums) {
            min = Math.min(x, min);
        }
        return min;
    }

    @Test
    public void test() {
        for (int i = 0; i < 100000; i++) {
            int[] nums = RandomArray.generateRotatedSortedArray(1, 5001, -5000, 5001);
            int min = findMin(nums);
            int minComparison = findMinComparison(nums);
            if (min != minComparison) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("min = " + min);
                System.out.println("minComparison = " + minComparison);
                findMin(nums);
                break;
            }
        }
    }
}
