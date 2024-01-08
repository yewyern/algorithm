package math;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给你1个2克和1个7克的砝码和一个天平，怎么在3步之内将140g盐分成90g和50g的2堆
 *
 * @author xuzhou
 * @since 2024/1/8 22:12
 */
public class PartitionSaltTest {

    @Test
    public void test() {
        process(new int[]{140}, 3);
    }

    private void process(int[] salts, int step) {
        if (step == 0) {
            boolean b = checkSalt(salts);
            if (b) {
                System.out.println("已分成90g和50g 2部分");
                System.exit(0);
            }
            return;
        }
        int n = salts.length;
        for (int i = 0; i < n; i++) {
            int[] next = use9(salts, i);
            if (next != null) {
                process(next, step - 1);
            }
            next = use7(salts, i);
            if (next != null) {
                process(next, step - 1);
            }
            next = use7And2(salts, i);
            if (next != null) {
                process(next, step - 1);
            }
            next = use2(salts, i);
            if (next != null) {
                process(next, step - 1);
            }
            next = use0(salts, i);
            if (next != null) {
                process(next, step - 1);
            }
        }
    }

    private int[] use9(int[] salts, int i) {
        if ((salts[i] & 1) == 0) {
            return null;
        }
        int n = salts.length;
        int[] next = copyAndIgnore(salts, i);
        next[n - 1] = (salts[i] + 9) >> 1;
        next[n] = salts[i] - next[n - 1];
        System.out.println(Arrays.toString(salts) + "对" + salts[i] + "一边使用9g砝码得到：" + Arrays.toString(next));
        return next;
    }

    private int[] use7And2(int[] salts, int i) {
        if ((salts[i] & 1) == 0) {
            return null;
        }
        int n = salts.length;
        int[] next = copyAndIgnore(salts, i);
        next[n - 1] = ((salts[i] + 9) >> 1) - 7;
        next[n] = salts[i] - next[n - 1];
        System.out.println(Arrays.toString(salts) + "对" + salts[i] + "一边使用7g，一边使用2g砝码得到：" + Arrays.toString(next));
        return next;
    }

    private int[] use7(int[] salts, int i) {
        if ((salts[i] & 1) == 0) {
            return null;
        }
        int n = salts.length;
        int[] next = copyAndIgnore(salts, i);
        next[n - 1] = (salts[i] + 7) >> 1;
        next[n] = salts[i] - next[n - 1];
        System.out.println(Arrays.toString(salts) + "对" + salts[i] + "一边使用7g砝码得到：" + Arrays.toString(next));
        return next;
    }

    private int[] use2(int[] salts, int i) {
        if ((salts[i] & 1) == 1) {
            return null;
        }
        int n = salts.length;
        int[] next = copyAndIgnore(salts, i);
        next[n - 1] = (salts[i] + 2) >> 1;
        next[n] = salts[i] - next[n - 1];
        System.out.println(Arrays.toString(salts) + "对" + salts[i] + "一边使用2g砝码得到：" + Arrays.toString(next));
        return next;
    }

    private int[] use0(int[] salts, int i) {
        if ((salts[i] & 1) == 1) {
            return null;
        }
        int n = salts.length;
        int[] next = copyAndIgnore(salts, i);
        next[n - 1] = salts[i] >> 1;
        next[n] = next[n - 1];
        System.out.println(Arrays.toString(salts) + "对" + salts[i] + "不使用砝码得到：" + Arrays.toString(next));
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
        // n数之和等于50
        int[] choose = new int[51];
        for (int salt : salts) {
            if (salt == 50) {
                return true;
            } else if (salt < 50) {
                if (choose[50 - salt] > 0) {
                    return true;
                }
                for (int i = 0; i < 50 - salt; i++) {
                    if (choose[i] > 0) {
                        choose[i + salt]++;
                    }
                }
                choose[salt]++;
            }
        }
        return false;
    }
}
