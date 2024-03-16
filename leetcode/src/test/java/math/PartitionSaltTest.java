package math;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你1个2克和1个7克的砝码和一个天平，怎么在3步之内将140g盐分成90g和50g的2堆
 *
 * @author xuzhou
 * @since 2024/1/8 22:12
 */
public class PartitionSaltTest {

    private static final int[] ADDS = new int[]{2, 7, 9, 5, 0};

    private static final Map<Integer, String> USAGE_MAP = new HashMap<>();

    static {
        USAGE_MAP.put(2, "一边使用2g砝码");
        USAGE_MAP.put(7, "一边使用7g砝码");
        USAGE_MAP.put(9, "一边使用2g+7g砝码");
        USAGE_MAP.put(5, "一边使用7g，一边使用2g砝码");
        USAGE_MAP.put(0, "不使用砝码");
    }

    private final String[] log = new String[3];
    private int logIndex = 0;


    @Test
    public void test() {
        if (dfs(new int[]{140}, 3)) {
            for (String s : log) {
                System.out.println(s);
            }
            System.out.println("已分成90g和50g 2部分");
        } else {
            System.out.println("无法分成90g和50g 2部分");
        }
    }

    private boolean dfs(int[] salts, int step) {
        if (step == 0) {
            return checkSalt(salts);
        }
        int n = salts.length;
        for (int i = 0; i < n; i++) {
            for (int add : ADDS) {
                if (((salts[i] + add) & 1) == 0) {
                    int[] next = use(salts, i, add);
                    log[logIndex++] = Arrays.toString(salts) + "对" + salts[i] + USAGE_MAP.get(add) + "得到：" + Arrays.toString(next);
                    boolean success = dfs(next, step - 1);
                    if (success) {
                        return true;
                    }
                    logIndex--;
                }
            }
        }
        return false;
    }

    private int[] use(int[] salts, int i, int add) {
        int n = salts.length;
        int[] next = copyAndIgnore(salts, i);
        next[n - 1] = (salts[i] + add) >> 1;
        next[n] = salts[i] - next[n - 1];
        return next;
    }

    private int[] copyAndIgnore(int[] salts, int i) {
        int n = salts.length;
        int[] next = new int[n + 1];
        for (int j = 0, k = 0; j < n; j++) {
            if (j != i) {
                next[k++] = salts[j];
            }
        }
        return next;
    }

    private boolean checkSalt(int[] salts) {
        // 1个数等于50 或者 2数之和等于50
        boolean[] choose = new boolean[51];
        for (int salt : salts) {
            if (salt == 50) {
                return true;
            } else if (salt < 50) {
                if (choose[50 - salt]) {
                    return true;
                }
                choose[salt] = true;
            }
        }
        return false;
    }
}
