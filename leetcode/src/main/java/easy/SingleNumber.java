package easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>只出现一次的数字
 *
 * <p>给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>说明：
 * <p>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * <p>示例 1:
 * <p>输入: [2,2,1]
 * <p>输出: 1
 *
 * <p>示例 2:
 * <p>输入: [4,1,2,1,2]
 * <p>输出: 4
 */
class SingleNumber {


    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Map<Integer, Integer> map = new HashMap<>(nums.length / 2);
        for (int a : nums) {
            if (map.containsKey(a)) {
                map.put(a, map.get(a) + 1);
            } else {
                map.put(a, 1);
            }
        }
        int res = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                res = entry.getKey();
                break;
            }
        }
        return res;
    }

    /**
     * <p> a ^ 0 = a
     * <p> a ^ a = 0
     * <p> a ^ b ^ a = a ^ a ^ b = 0 ^ b = b
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int a = 0;
        for (int b : nums) {
            a ^= b;
        }
        return a;
    }

    public int test(int... nums) {
        return singleNumber(nums);
    }

    public static void main(String[] args) {
        SingleNumber a = new SingleNumber();
        System.out.println(a.test(2, 2, 1));
        System.out.println(a.test(4, 1, 2, 1, 2));
    }
}