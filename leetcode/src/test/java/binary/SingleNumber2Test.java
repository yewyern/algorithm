package binary;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 56 - II. 数组中数字出现的次数 II</a>
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 *
 * @author xuzhou
 * @since 2023/8/2 14:34
 */
public class SingleNumber2Test {

    public int singleNumber(int[] nums) {
        // 参考大佬的写法，有限状态机，使用2个数表示同一个数的状态，对于任意一位数的变化如：00->01->10->00，同样的数经过3次后变成0
        int t0 = 0, t1 = 0;
        for (int num : nums) {
            t0 = (~t1) & (t0 ^ num);
            // t1是t0的下一个状态
            t1 = (~t0) & (t1 ^ num);
        }
        return t0;
    }

    public int singleNumberSort(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < N - 1; i += 3) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[N - 1];
    }

    public int singleNumber02(int[] nums) {
        // 分组方法2：根据中值分组
        int N = nums.length;
        int mid = 0;
        while (N > 1) {
            int[] part1 = new int[N];
            int[] part2 = new int[N];
            int m = 0, n = 0;
            int min = nums[0], max = nums[0];
            for (int i = 0; i < N; i++) {
                int a = nums[i];
                if (a <= mid) {
                    part1[m++] = a;
                } else {
                    part2[n++] = a;
                }
                min = Math.min(min, a);
                max = Math.max(max, a);
            }
            if (m % 3 == 1) {
                nums = part1;
                N = m;
                max = mid;
            } else {
                nums = part2;
                N = n;
                min = mid + 1;
            }
            mid = min + ((max - min) >> 1);
        }
        return nums[0];
    }

    public int singleNumber01(int[] nums) {
        // 分组方法
        int N = nums.length;
        while (N > 1) {
            int or = 0;//所有可能的位
            int and = nums[0];// 所有数都有的位
            for (int i = 0; i < N; i++) {
                int a = nums[i];
                or |= a;
                and &= a;
            }
            int xor = and ^ or; // 具备区分度的位
            int[] part1 = new int[N];
            int[] part2 = new int[N];
            int m = 0, n = 0;
            int flag = xor & (-xor); // 取最后一位进行分组
            for (int i = 0; i < N; i++) {
                int a = nums[i];
                if ((a & flag) == 0) {
                    part1[m++] = a;
                } else {
                    part2[n++] = a;
                }
            }
            if (m % 3 == 1) {
                nums = part1;
                N = m;
            } else {
                nums = part2;
                N = n;
            }
        }
        return nums[0];
    }


    public int singleNumber0(int[] nums) {
        // 对每一位进行计数，但是方法是每个数上的bit操作，比下面的方法肯定少一些时间
        int ans = 0;
        int[] bitCounts = new int[32];
        for (int a : nums) {
            while (a != 0) {
                int lastOne = a & (-a);//最后一位1
                bitCounts[Integer.bitCount(lastOne - 1)]++;
                a ^= lastOne;
            }
        }
        for (int i = 0; i < 32; i++) {
            if (bitCounts[i] % 3 == 1) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    public int singleNumber1(int[] nums) {
        // 对于int的32位，每一位过一遍数组，如果该位有值，则进行计数+1，如果计数%3==1，则表示结果的那一位是1
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int bit = 1 << i;
            int count = 0;
            for (int num : nums) {
                if ((num & bit) != 0) {
                    count++;
                }
            }
            if (count % 3 == 1) {
                ans |= bit;
            }
        }
        return ans;
    }


    public int singleNumber2(int[] nums) {
        // hash表
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num : nums) {
            if (map.getOrDefault(num, 0) == 2) {
                map.remove(num);
            } else {
                map.compute(num, (k, v) -> v != null ? v + 1 : 1);
            }
        }
        return map.keySet().stream().findFirst().get();
    }

    @Test
    public void test() {
        System.out.println(singleNumber(new int[] {3,4,3,3}));
        System.out.println(singleNumber(new int[] {9,0,7,9,7,9,7}));
    }
}
