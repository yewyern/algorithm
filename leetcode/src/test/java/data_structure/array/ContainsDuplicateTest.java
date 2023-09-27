package data_structure.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/contains-duplicate/">217. 存在重复元素</a>
 * <p>给定一个整数数组，判断是否存在重复元素。
 * <p>
 * <p>如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: [1,2,3,1]
 * <p>输出: true
 * <p>示例 2:
 * <p>
 * <p>输入: [1,2,3,4]
 * <p>输出: false
 * <p>示例 3:
 * <p>
 * <p>输入: [1,1,1,3,3,4,3,2,4,2]
 * <p>输出: true
 *
 * @author zhou.xu
 * @since 2020/10/9 9:06
 */
public class ContainsDuplicateTest {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
