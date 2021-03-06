package easy;

import java.util.HashMap;

/**
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
 * @date 2020/10/9 9:26
 */
public class ContainsNearbyDuplicateTest {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && k >= i - map.get(nums[i])) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
