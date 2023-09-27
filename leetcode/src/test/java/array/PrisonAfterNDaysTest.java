package array;

import java.util.Arrays;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/prison-cells-after-n-days/">957. N 天后的牢房</a>
 *
 * @author : zhou.xu
 * @since : 2020/2/19 19:22
 */
public class PrisonAfterNDaysTest {

    public int[] prisonAfterNDays(int[] cells, int n) {
        int[] map = new int[128]; // 保存牢房不同状态出现的天数
        int[][] queue = new int[128][]; // 按顺序保存牢房状态
        int size = 1;
        int[] next = cells;
        int i = 1;
        int loopStart = 0;
        for (; i <= n; i++) {
            next = next(next);
            int number = toNumber(next);
            if ((loopStart = map[number]) > 0) {
                break;
            }
            map[number] = i;
            queue[size++] = next;
        }
        // 第i天出现重复
        if (i >= n) {
            return next;
        }
        int leftDays = (n - i) % (i - loopStart);
        return queue[loopStart + leftDays];
    }

    private int[] next(int[] cells) {
        int n = cells.length;
        int[] next = new int[n];
        for (int i = 1; i < n - 1; i++) {
            next[i] = ~(cells[i - 1] ^ cells[i + 1]) & 1;
        }
        return next;
    }

    private int toNumber(int[] cells) {
        int ans = 0;
        for (int i = 0; i < cells.length; i++) {
            ans |= cells[i] << i;
        }
        return ans;
    }

    private void print(int[] cells, int n) {
        System.out.print("Day " + n + ": ");
        System.out.println(Arrays.toString(cells));
    }

    @Test
    public void test() {
        int[] cells = {1, 0, 0, 1, 0, 0, 1, 0};
        int[] nDays = prisonAfterNDays(cells, 1000000000);
        print(nDays, 1000000000);
    }
}