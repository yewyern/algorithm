package search;

import org.junit.Test;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 53 - II. 0～n-1中缺失的数字</a>
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= 数组长度 <= 10000
 *
 * @author xuzhou
 * @since 2023/6/16 18:01
 */
public class MissingNumberTest {

    @Test
    public void check() {
        for (int i = 0; i < 10000; i++) {
            int a = RandomUtils.nextInt(10000);
            int b = RandomUtils.nextInt(10000);
            int l = Math.max(a, b);
            int miss = Math.min(a, b);
            if (check(i, l, miss)) break;
        }
    }

    @Test
    public void test2() {
        check(0, 8303, 6908);
    }

    private boolean check(int i, int l, int miss) {
        int[] nums = new int[l];
        for (int j = 0; j < l; j++) {
            if (j < miss) {
                nums[j] = j;
            } else {
                nums[j] = j + 1;
            }
        }
        int missingNumber = missingNumber(nums);
        if (missingNumber != miss) {
            System.out.println(Arrays.toString(nums) + ", miss = " + miss + ", missingNumber = " + missingNumber);
            System.out.println(i);
            return true;
        }
        return false;
    }

    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (l != nums[l]) {
                return l;
            }
            int mid = (l + r) >> 1;
            if (mid == nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return nums[l] != l ? l : l + 1;
    }
}
