package data_structure.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

/**
 * 给你一个整数数组 nums 和一个整数 k
 * 找到最大长度的子数组，使子数组每个数的和等于 k
 *
 * 例如：
 * nums = [4,3,5,6,3,2,1,1,3,2,2,1,1,4,5], k = 6
 * 返回4 , 最长子数组为[2,2,1,1]
 */
public class GetMaxLength {

    public int getMaxLength(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        // 滑动窗口法,
        // 时间复杂度：O(n)，双指针最多都走一遍，2n
        // 空间复杂度：O(1)
        int l = 0;
        int r = 0;
        int len = 0;
        int sum = nums[0];
        while (true) {
//            System.out.println("l = " + l + ", r = " + r + ", sum = " + sum + ", len = " + len);
            if (sum > k) {
                if (l == nums.length - 1) {
                    break;
                }
                sum -= nums[l++];
            } else {
                if (sum == k) {
                    len = Math.max(len, r - l + 1);
                }
                if (r == nums.length - 1) {
                    break;
                }
                sum += nums[++r];
            }
        }
        return len;
    }

    public int getMaxLengthTrie(int[] nums, int k) {
        // 前缀和算法
        int len = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//important, 如果没有，可能会失去[0-i]刚好=k，且nums[i]=k的值
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                // 0 - i = sum
                // 0 - m = sum - k
                // m - i = sum - (sum - k) = k
                len = Math.max(len, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    public int getMaxLengthComparison(int[] nums, int k) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length && sum <= k; j++) {
                sum += nums[j];
                if (sum == k) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }

    @Test
    public void testByComparison() {
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(10, 100);
            int k = RandomUtils.nextInt(100);
            int maxLength = getMaxLength(nums, k);
            int maxLengthComparison = getMaxLengthComparison(nums, k);
            int getMaxLengthTrie = getMaxLengthTrie(nums, k);
            if (maxLength != getMaxLengthTrie) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("k = " + k);
                System.out.println("maxLength  = " + maxLength);
                System.out.println("maxLength2 = " + maxLengthComparison);
                System.out.println("maxLength2 = " + getMaxLengthTrie);
                System.out.println("--------------------------");
            }
        }
    }

    @Test
    public void test() {
        int[] nums = {4, 3, 5, 6, 3, 2, 1, 1, 3, 2, 2, 1, 1, 4, 5};
        int maxLength = getMaxLength(nums, 6);
        System.out.println("maxLength = " + maxLength);
        int maxLength2 = getMaxLength(new int[]{487, 489, 182, 118, 497, 21, 30, 226, 448}, 118);
        System.out.println("maxLength2 = " + maxLength2);
    }
}
