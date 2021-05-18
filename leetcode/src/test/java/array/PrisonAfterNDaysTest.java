package array;

import java.util.Arrays;
import org.junit.Test;

/**
 * N 天后的牢房
 * <p>
 * 8 间牢房排成一排，每间牢房不是有人住就是空着。
 * <p>
 * 每天，无论牢房是被占用或空置，都会根据以下规则进行更改：
 * <p>
 * 如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。 否则，它就会被空置。
 * <p>
 * （请注意，由于监狱中的牢房排成一行，所以行中的第一个和最后一个房间无法有两个相邻的房间。）
 * <p>
 * 我们用以下方式描述监狱的当前状态：如果第 i 间牢房被占用，则 cell[i]==1，否则 cell[i]==0。
 * <p>
 * 根据监狱的初始状态，在 N 天后返回监狱的状况（和上述 N 种变化）。
 * <p>
 *
 * @author : zhou.xu
 * @date : 2020/2/19 19:22
 */
public class PrisonAfterNDaysTest {

    public int[] prisonAfterNDays(int[] cells, int N) {
        int left, current;
        for (int i = 1; i <= N; i++) {
            left = cells[0];
            cells[0] = 0;
            for (int j = 1; j < cells.length - 1; j++) {
                current = cells[j];
                cells[j] = left == cells[j + 1] ? 1 : 0;
                left = current;
            }
            cells[cells.length - 1] = 0;
        }
        return cells;
    }

    private void print(int[] cells, int n) {
        System.out.print("Day " + n + ": ");
        System.out.println(Arrays.toString(cells));
    }

    @Test
    public void test() {
        int[] cells = {0, 1, 0, 1, 1, 0, 0, 1};
        for (int i = 0; i < 7; i++) {
            prisonAfterNDays(cells, i);
            print(cells, i);
        }
    }
}