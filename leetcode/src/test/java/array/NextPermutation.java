package array;

import java.util.Arrays;
import org.junit.Test;

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 * @author zhou.xu
 * @date 2020/12/3 10:46
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int N = nums.length, i = N - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = N - 1;
            while (j > i && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        for (int j = start, k = nums.length - 1; j < k; j++, k--) {
            swap(nums, j, k);
        }
    }

    public void test(int... nums) {
        System.out.println("nums = " + Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println("next = " + Arrays.toString(nums));
        System.out.println("----------------------------");
        //2431
        //3124
    }

    @Test
    public void test() {
        test(1, 2, 3);
        test(3, 2, 1);
        test(1, 1, 5);
        test(1, 2, 4, 3, 1);
        test(1);
    }
}
