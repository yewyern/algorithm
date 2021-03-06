package utils;

import java.util.Random;

/**
 * @author xuzhou
 * @date 2021/4/27 17:55
 */
public class RandomUtils {

    private static final Random random = new Random(System.currentTimeMillis());

    public static int nextInt(int max) {
        return random.nextInt(max);
    }

    public static int nextInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
