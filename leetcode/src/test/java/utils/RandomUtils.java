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
        long l = random.nextLong(bound);
        return (int) l + min;
    }

    public static boolean nextBool() {
        return random.nextBoolean();
    }
}
