package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * <p>给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * <p>你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: [3,2,3]
 * <p>输出: 3
 * <p>示例 2:
 * <p>
 * <p>输入: [2,2,1,1,1,2,2]
 * <p>输出: 2
 *
 * @author : zhou.xu
 * @date : 2020/6/3 17:01
 */
public class MajorityElement {

    private static final MajorityElement INSTANCE = new MajorityElement();

    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = nums[0];

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>(nums.length / 2);
        int major = nums[0];
        for (int i = 0; i < nums.length; i++) {
            Integer count = countMap.get(nums[i]);
            if (count == null) {
                count = 0;
            }
            count++;
            countMap.put(nums[i], count);
            if (nums[i] != major && count > countMap.get(major)) {
                major = nums[i];
            }
        }
        return major;
    }

    public static int test(int... nums) {
        return INSTANCE.majorityElement(nums);
    }

    public static void main(String[] args) {
        System.out.println("test(3, 2, 3) = " + test(3, 2, 3));
        System.out.println("test(2, 2, 1, 1, 1, 2, 2) = " + test(2, 2, 1, 1, 1, 2, 2));
        System.out.println("test(1, 7, 2, 7, 3, 7, 4, 7, 5, 7) = " + test(1, 7, 2, 7, 3, 7, 4, 7, 5, 7));
    }
}