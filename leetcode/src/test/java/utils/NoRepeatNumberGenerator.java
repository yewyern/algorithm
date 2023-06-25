package utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhou.xu
 * @since 2023/6/23 21:17
 */
public class NoRepeatNumberGenerator {

    private final int min;
    private final int max;
    private int maxRetryCount;
    private boolean[] arraySet;
    private Set<Integer> hashSet;

    public NoRepeatNumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
        this.maxRetryCount = 10;
        long bound = (long) max - min;
        if (bound > 0 && bound <= 1024) {
            arraySet = new boolean[max - min];
        } else {
            hashSet = new HashSet<>();
        }
    }

    public NoRepeatNumberGenerator(int min, int max, int maxRetryCount) {
        this(min, max);
        this.maxRetryCount = maxRetryCount;
    }

    private boolean contains(int num) {
        if (arraySet != null) {
            return arraySet[num - min];
        }
        return hashSet.contains(num);
    }

    private void record(int num) {
        if (arraySet != null) {
            arraySet[num - min] = true;
        } else {
            hashSet.add(num);
        }
    }

    public int nextInt() {
        int res = RandomUtils.nextInt(min, max);
        int count = 0;
        while (count <= maxRetryCount && contains(res)) {
            if (count == maxRetryCount) {
                throw new IllegalArgumentException("重试" + maxRetryCount + "次仍未随机到可用数字，请检查数字范围!");
            }
            res = RandomUtils.nextInt(min, max);
            count++;
        }
        record(res);
        return res;
    }
}
