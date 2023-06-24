package class01binary;

import utils.RandomUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 异或运算：
 * 1、a ^ a = 0
 * 2、a ^ 0 = a
 * 3、a ^ b = b ^ a
 * 4、a ^ b = c
 * c ^ b = a
 * c ^ a = b
 *
 * @author zhou.xu
 * @since 2023/6/24 11:04
 */
public class Binary04 {

    public static void main(String[] args) {
        int[] nums = generate(100, 100, 1);
        System.out.println(Arrays.toString(nums));
        System.out.println(findOddTimesNum(nums));


        int[] nums2 = generate(100, 100, 2);
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(findOddTimesNum2(nums2)));

        int[] kTimesArray = generateKTimesArray(100, 100, 3, 6);
        System.out.println(Arrays.toString(kTimesArray));
        System.out.println(findKTimesNum(kTimesArray, 3, 6));
    }

    public static int findOddTimesNum(int[] nums) {
        // 数组中只有一个数出现了奇数次，其他的数都出现了偶数次，找到该数
        int eor = 0;
        for (int x : nums) {
            eor ^= x;
        }
        return eor;
    }

    public static int[] findOddTimesNum2(int[] nums) {
        // 数组中有2个数出现了奇数次，其他的数都出现了偶数次，找到这2个数
        int eor = 0;
        for (int x : nums) {
            eor ^= x;
        }
        // eor = a ^ b
        int a = eor;
        int t = a & (-a); // 任意位置为1的数，用于对nums进行分组
        for (int x : nums) {
            if ((x & t) == 0) {
                a ^= x;
            }
        }
        return new int[]{a, a ^ eor};
    }

    public static int findKTimesNum(int[] nums, int k, int M) {
        // 数组中有1个数出现了k次，其他的数都出现了M次，且k < M，找到出现k次的数
        int ans = 0;
        int[] t = new int[32];// 记录所有数每一位上1出现的总次数
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                t[i] += (x >> i) & 1;
            }
        }
        for (int i = 0; i < t.length; i++) {
            if (t[i] % M != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    public static int[] generate(int maxLen, int max, int oddCount) {
        int len = (RandomUtils.nextInt(maxLen - oddCount) | 1) + oddCount + 1;
        int[] nums = new int[len];
        for (int i = 0; i < oddCount; i++) {
            do {
                nums[i] = RandomUtils.nextInt(max);
            } while (contains(nums, nums[i], i));
        }
        for (int i = oddCount; i < len; i++) {
            int a = RandomUtils.nextInt(len);
            nums[i++] = a;
            nums[i] = a;
        }
        disarrange(nums);
        return nums;
    }

    public static boolean contains(int[] nums, int num, int len) {
        for (int i = 0; i < len; i++) {
            if (nums[i] == num) {
                return true;
            }
        }
        return false;
    }

    public static int[] generateKTimesArray(int maxLen, int max, int k, int M) {
        int t = RandomUtils.nextInt(maxLen) / M;
        int len = t * M + k;
        int[] nums = new int[len];
        int target = RandomUtils.nextInt(max);
        Arrays.fill(nums, 0, k, target);
        Set<Integer> set = new HashSet<>();
        set.add(target);
        for (int i = k; i < len; ) {
            do {
                t = RandomUtils.nextInt(max);
            } while (set.contains(t));
            Arrays.fill(nums, i, i + M, t);
            i += M;
        }
        disarrange(nums);
        return nums;
    }

    public static void disarrange(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // nums[i]随机交换一个位置的数
            int j = RandomUtils.nextInt(nums.length);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
