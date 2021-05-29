package utils;

import java.util.Arrays;
import java.util.Random;

/**
 * @author xuzhou
 * @date 2021/4/27 17:43
 */
public class RandomArray {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] nums = generate(i, 100);
            System.out.println("nums = " + Arrays.toString(nums));
            int[] nums2 = generateSortedArray(i, 100);
            System.out.println("nums = " + Arrays.toString(nums2));
        }
        int testCase = 10000;
        long start = System.nanoTime();
        for (int i = 0; i < testCase; i++) {
            generate(i, 100);
        }
        System.out.println("generate  = " + (System.nanoTime() - start) / testCase);
        start = System.nanoTime();
        for (int i = 0; i < testCase; i++) {
            generateSortedArray(i, 100);
        }
        System.out.println("generateSortedArray = " + (System.nanoTime() - start) / testCase);

    }

    public static int[] generateRandomLengthArray(int maxLen, int max) {
        return generate(random.nextInt(maxLen), 0, max);
    }

    public static int[] generate(int len, int max) {
        return generate(len, 0, max);
    }

    public static int[] generate(int len, int min, int max) {
        int[] nums = new int[len];
        int bound = max - min;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(bound) + min;
        }
        return nums;
    }

    public static int[] generateRandomLengthSortedArray(int maxLen, int max) {
        return generateRandomLengthSortedArray(0, maxLen, 0, max);
    }

    public static int[] generateRandomLengthSortedArray(int minLen, int maxLen, int minVal, int maxVal) {
        int len = maxLen == minLen ? 0 : random.nextInt(maxLen - minLen) + minLen;
        return generateSortedArray(len, minVal, maxVal);
    }

    public static int[] generateSortedArray(int len, int max) {
        return generateSortedArray(len, 0, max);
    }

    public static int[] generateSortedArray(int len, int min, int max) {
        int[] nums = new int[len];
        int bound = max - min;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(bound) + min;
        }
        Arrays.sort(nums);
        return nums;
    }

    public static int[] generateNoRepeatSortedArray(int len, int min, int max) {
        boolean[] set = new boolean[max - min];
        int[] nums = new int[len];
        int bound = max - min;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(bound) + min;
            while (set[nums[i] - min]) {
                nums[i] = random.nextInt(bound) + min;
            }
            set[nums[i] - min] = true;
        }
        Arrays.sort(nums);
        return nums;
    }

}
