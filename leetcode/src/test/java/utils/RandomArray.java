package utils;

import java.util.Arrays;

/**
 * @author xuzhou
 * @since 2021/4/27 17:43
 */
public class RandomArray {


    public static int[] generateRandomLengthArray(int maxLen, int max) {
        return generate(RandomUtils.nextInt(maxLen), 0, max);
    }

    /**
     * 生成随机长度的随机整形数组
     *
     * @param minLen 数组最小长度，包含
     * @param maxLen 数组最大长度，不包含
     * @param minVal 数组中最小值，包含
     * @param maxVal 数组中最大值，不包含
     * @return int[] a, minLen <= a.len < maxLen), minVal <= a[i] < maxVal
     */
    public static int[] generateRandomLengthArray(int minLen, int maxLen, int minVal,
                                                  int maxVal) {
        int len;
        if (minLen == maxLen) {
            len = minLen;
        } else {
            len = RandomUtils.nextInt(maxLen - minLen) + minLen;
        }
        return generate(len, minVal, maxVal);
    }

    public static int[] generateRandomLengthNoEmptyArray(int maxLen, int max) {
        return generate(maxLen == 1 ? 1 : RandomUtils.nextInt(maxLen - 1) + 1, 0, max);
    }

    public static int[] generate(int len, int max) {
        return generate(len, 0, max);
    }

    public static int[] generate(int len, int min, int max) {
        int[] nums = new int[len];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = RandomUtils.nextInt(min, max) + min;
        }
        return nums;
    }

    public static int[] generateRandomLengthSortedArray(int maxLen, int max) {
        return generateRandomLengthSortedArray(0, maxLen, 0, max);
    }

    public static int[] generateRandomLengthSortedArray(int minLen, int maxLen, int minVal,
                                                        int maxVal) {
        int len = maxLen <= minLen ? 0 : RandomUtils.nextInt(maxLen - minLen) + minLen;
        return generateSortedArray(len, minVal, maxVal);
    }

    public static int[] generateSortedArray(int len, int max) {
        return generateSortedArray(len, 0, max);
    }

    public static int[] generateSortedArray(int len, int min, int max) {
        int[] nums = generate(len, min, max);
        Arrays.sort(nums);
        return nums;
    }

    public static int[] generateNoRepeatSortedArray(int len, int min, int max) {
        int[] nums = new int[len];
        NoRepeatNumberGenerator generator = new NoRepeatNumberGenerator(min, max);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = generator.nextInt() + min;
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 生成任意长度的，相邻不相等的数组
     *
     * @param len 生成的结果集的大小，len不能超出(max - min)
     * @param min
     * @param max
     * @return
     */
    public static int[] generateAdjacentNonEqualArray(int len, int min, int max) {
        if (len > max - min || max <= min) {
            throw new IllegalArgumentException();
        }
        int[] nums = new int[len];
        int bound = max - min;
        for (int i = 0; i < len; i++) {
            nums[i] = RandomUtils.nextInt(bound) + min;
            if (i > 0) {
                while (nums[i] == nums[i - 1]) {
                    nums[i] = RandomUtils.nextInt(bound) + min;
                }
            }
        }
        return nums;
    }

    public static String[] generateRandomStringArray(int maxLen, int maxStringLen) {
        String[] res = new String[RandomUtils.nextInt(maxLen)];
        for (int i = 0; i < res.length; i++) {
            res[i] = GenerateRandomString.generateRandomLenString(maxStringLen);
        }
        return res;
    }

    public static String[] generateRandomStringArrayNoEmptyString(int maxLen, int maxStringLen) {
        String[] res = new String[RandomUtils.nextInt(maxLen)];
        for (int i = 0; i < res.length; i++) {
            res[i] = GenerateRandomString.generateRandomLenStringNoEmpty(maxStringLen);
        }
        return res;
    }

    public static int[] generateRotatedSortedArray(int minLen, int maxLen, int minVal,
                                                   int maxVal) {
        int[] nums = generateRandomLengthSortedArray(minLen, maxLen, minVal, maxVal);
        return randomRotateArray(nums);
    }

    public static int[] randomRotateArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int N = nums.length;
        int k = RandomUtils.nextInt(N);
        if (k == 0) {
            return Arrays.copyOf(nums, N);
        }
        int[] ans = new int[N];
        System.arraycopy(nums, k, ans, 0, N - k);
        System.arraycopy(nums, 0, ans, N - k, k);
        return ans;
    }

}
