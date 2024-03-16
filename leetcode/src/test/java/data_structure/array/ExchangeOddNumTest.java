package data_structure.array;

import org.junit.Test;
import utils.RandomArray;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 21. 调整数组顺序使奇数位于偶数前面</a>
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 50000
 * 0 <= nums[i] <= 10000
 *
 * @author zhou.xu
 * @since 2023/6/14 22:39
 */
public class ExchangeOddNumTest {

    public int[] exchange(int[] nums) {
        if (nums == null) {
            return null;
        }
        int N = nums.length;
        int l = 0, r = N - 1;
        while (l < r) {
            if ((nums[l] & 1) == 0 && (nums[r] & 1) == 1) {
                int temp = nums[l];
                nums[l++] = nums[r];
                nums[r--] = temp;
            } else {
                if ((nums[l] & 1) == 1) {
                    l++;
                }
                if ((nums[r] & 1) == 0) {
                    r--;
                }
            }
        }
        return nums;
    }

    private void check(int[] nums) {
        int[] exchange = exchange(nums);
        int i = 0;
        while (i < nums.length && (nums[i] & 1) == 1) {
            i++;
        }
        while (i < nums.length && (nums[i] & 1) == 0) {
            i++;
        }
        if (i < nums.length) {
            System.out.println("exchange = " + Arrays.toString(exchange));
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            int[] generate = RandomArray.generate(1000, 0, 10000);
            check(generate);
        }
    }
}
