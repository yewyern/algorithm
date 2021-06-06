package data_structure.array;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

/**
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 *
 * 子数组大小 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [23,2,4,6,7], k = 6
 * 输出：true
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 * 示例 2：
 *
 * 输入：nums = [23,2,6,4,7], k = 6
 * 输出：true
 * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
 * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 * 示例 3：
 *
 * 输入：nums = [23,2,6,4,7], k = 13
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 */
public class CheckSubArraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        // map<前缀和对k取模，索引>
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // important
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    public static boolean checkSubarraySum2(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) {
                return true;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) {
                    return true;
                }
            }

            // TODO leetcode通过且时间复杂度第一的代码，不能通过对数器
            if (sum < k) {
                break;
            }
        }
        return false;
    }

    public boolean checkSubarraySumComparison(int[] nums, int k) {
        // 暴力方法
        if (nums.length < 2) {
            return false;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test() {

        int[] nums = RandomArray.generate(1000_00, 1, 1_000_000_000);
//        checkSubarraySumComparison(new int[]{23, 2, 4, 6, 7}, 6);
//        checkSubarraySumComparison(new int[]{23, 2, 6, 4, 7}, 6);
//        checkSubarraySumComparison(new int[]{23, 2, 6, 4, 7}, 13);
//        try {
//            BufferedReader bufferedReader = new BufferedReader(
//                new FileReader("C:\\Users\\xzer\\Desktop\\testcase.txt"));
//            String line = bufferedReader.readLine();
//            String[] sps = line.split(",");
//            int[] nums = new int[sps.length];
//            for (int i = 0; i < sps.length; i++) {
//                nums[i] = Integer.parseInt(sps[i]);
//            }
//            int k = Integer.parseInt(bufferedReader.readLine());
        int k = RandomUtils.nextInt(1, Integer.MAX_VALUE);
        System.out.println("k = " + k);
        long s = System.nanoTime() / 1000;
        boolean b = checkSubarraySum(nums, k);
        long s1 = System.nanoTime() / 1000;
        boolean b2 = checkSubarraySum2(nums, k);
        long s2 = System.nanoTime() / 1000;
        boolean b3 = checkSubarraySumComparison(nums, k);
        long s3 = System.nanoTime() / 1000;
        System.out.println(s1 - s);
        System.out.println("b   = " + b);
        System.out.println(s2 - s1);
        System.out.println("b2  = " + b2);
        System.out.println(s3 - s2);
        System.out.println("b3  = " + b3);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
