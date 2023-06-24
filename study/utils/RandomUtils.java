package utils;

import java.util.Random;

/**
 * @author xuzhou
 * @since 2021/4/27 17:55
 */
public class RandomUtils {

    private static final Random random = new Random(System.currentTimeMillis());

    public static int nextInt(int max) {
        if (max == 0) {
            return 0;
        }
        return random.nextInt(max);
    }

    public static int nextInt(int min, int max) {
        if (max == min) {
            return min;
        }
        long bound = (long) max - min;
        return (int) (random.nextLong(bound) + min);
    }

    public static boolean nextBool() {
        return random.nextBoolean();
    }

    public static int[] generateRandomLengthNoEmptyArray(int maxLen, int max) {
        return generate(maxLen == 1 ? 1 : nextInt(maxLen - 1) + 1, 0, max);
    }

    public static int[] generate(int len, int max) {
        return generate(len, 0, max);
    }

    public static int[] generate(int len, int min, int max) {
        int[] nums = new int[len];
        int bound = max - min;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nextInt(min, max) + min;
        }
        return nums;
    }
}
