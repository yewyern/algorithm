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
        return (int) (Math.random() * max);
    }

    public static int nextInt(int min, int max) {
        if (max == min) {
            return min;
        }
        long bound = (long) max - min;
        return (int) (Math.random() * bound + min);
    }

    public static boolean nextBool() {
        return random.nextBoolean();
    }
}
