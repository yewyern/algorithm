package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

/**
 * <a href="https://leetcode.cn/problems/contains-duplicate-ii">219. 存在重复元素 II</a>
 * <p>给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 *
 * <p>示例 1:
 * <p>输入: nums = [1,2,3,1], k = 3
 * <p>输出: true
 *
 * <p>示例 2:
 * <p>输入: nums = [1,0,1,1], k = 1
 * <p>输出: true
 *
 * <p>示例 3:
 * <p>输入: nums = [1,2,3,1,2,3], k = 2
 * <p>输出: false
 *
 * @author zhou.xu
 * @since 2020/10/9 9:26
 */
public class ContainsNearbyDuplicateTest {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (i >= k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 暴力做法对照
     * 往前看k位
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean normalComparison(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = Math.max(i - k, 0); j < i; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int[] array = RandomArray.generateRandomLengthArray(500, 1000);
            int k = array.length > 0 ? RandomUtils.nextInt(array.length) : 0;
            boolean comparison = normalComparison(array, k);
            if (comparison == containsNearbyDuplicate(array, k)) {
                continue;
            }
            System.out.println("array = " + Arrays.toString(array));
            System.out.println("k = " + k);
            System.out.println("comparison = " + comparison);
            System.out.println("----------------------------");
        }
    }

    @Test
    public void test2() {
        int[] array = {158, 158, 949, 392, 858, 253, 79, 770, 208, 410, 604, 251, 156, 762, 385, 497, 274, 346, 554, 358, 361, 173, 137};
        int k = 8;
        containsNearbyDuplicate(array, k);
        normalComparison(array, k);
    }
}
