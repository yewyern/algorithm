package test;

import org.junit.Test;

/**
 * @author xzer
 */
public class YanghuiSanjiao {

    /**
     * 打印杨辉三角
     * 0 1 2 3
     * 0 [1]
     * 1 [1 1]
     * 2 [1 2 1]
     * 3 [1 3 3 1]
     *
     * @param n 要打印几层
     */
    public void print(int n) {
        if (n < 1) {
            return;
        }
        int[] last = {1};
        print(last);
        for (int i = 1; i < n; i++) {
            int l = i + 1;
            int[] curr = new int[l];
            curr[0] = 1;
            curr[l - 1] = 1;
            for (int j = 1; j < l - 1; j++) {
                curr[j] = last[j - 1] + last[j];
            }
            print(curr);
            last = curr;
        }
    }

    public void print(int[] a) {
        for (int j : a) {
            System.out.printf("%10d", j);
        }
        System.out.println();
    }

    @Test
    public void test() {
        print(30);
    }
}
