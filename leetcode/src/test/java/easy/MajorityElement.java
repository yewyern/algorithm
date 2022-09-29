package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

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
 * @since : 2020/6/3 17:01
 */
public class MajorityElement {

    private static final MajorityElement INSTANCE = new MajorityElement();

    /**
     * 投票法O(n)：
     * 从头到尾遍历，如果当前候选数等于自身，则投票+1，反之，则投票-1
     * 如果，数值为0，当前候选数设置为自身
     * 因为存在过半数，最终的候选数即为过半数
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
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

    /**
     * 使用map存储所有数的计数值
     * 找到value大于一半的key
     * 时间复杂度O(n)+O(n)=O(n)
     * 空间复杂度O(n)/2=O(n)
     *
     * @param nums
     * @return
     */
    public int majorityElementComparison(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int half = nums.length >> 1;
        List<Integer> list = map.entrySet().stream()
            .filter(e -> e.getValue() > half)
            .map(Entry::getKey)
            .collect(Collectors.toList());
        return list.size() > 0 ? list.get(0) : 0;
    }

    /**
     * 只要存在过半数，中点位置一定是过半数
     * 时间复杂度O(nlogn)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int majorityElementComparison2(int[] nums) {
        Arrays.sort(nums);// 必须存在过半数的数，反之则不正确
        return nums[nums.length >> 1];
    }

    public int[] generateMajorElementArray(int maxLen, int max) {
        int len = RandomUtils.nextInt(1, maxLen);
        int[] res = new int[len];
        int k = RandomUtils.nextInt(max);
        Arrays.fill(res, k);
        if (len / 2 == 0) {
            return res;
        }
        int nonKCount = RandomUtils.nextInt(len / 4, len / 2);
        int[] nonKIndexes = RandomArray.generateNoRepeatSortedArray(nonKCount, 0, len);
        for (int i = 0; i < nonKCount; i++) {
            int r;
            do {
                r = RandomUtils.nextInt(max);
            } while (r == k);
            res[nonKIndexes[i]] = r;
        }
        return res;
    }

    @Test
    public void test() {
//        test(4747, 4747, 4747, 3377, 7156, 4747, 4747, 1719, 4747, 4747, 4454, 4747, 4747);
        for (int i = 0; i < 10000; i++) {
            int[] nums = generateMajorElementArray(100, 10000);
            test(nums);
        }
    }

    private void test(int... nums) {
        int majorityElement = majorityElement(nums);
        int majorityElementComparison = majorityElementComparison(nums);
        if (majorityElement != majorityElementComparison) {
            System.out.println("majorityElement = " + majorityElement);
            System.out.println("majorityElementComparison = " + majorityElementComparison);
            System.out.println("nums = " + Arrays.toString(nums));
        }
        int majorityElementComparison2 = majorityElementComparison2(nums);
        if (majorityElement != majorityElementComparison2) {
            System.out.println("majorityElement = " + majorityElement);
            System.out.println("majorityElementComparison = " + majorityElementComparison);
            System.out.println("nums = " + Arrays.toString(nums));
        }
    }
}