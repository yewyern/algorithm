package dynamic_programing;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 *
 * @author zhou.xu
 * @since 2023/11/20 20:51
 */
public class CardInLineTest {

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int[] arr = RandomArray.generate(RandomUtils.nextInt(1, 5), 1, 20);
            int res1 = maxScore(arr);
            int res2 = maxScore2(arr);
            if (res1 != res2) {
                System.out.println(Arrays.toString(arr));
                System.out.println(res1);
                System.out.println(res2);
                maxScore2(arr);
                break;
            }
        }
    }

    public int maxScore(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] offensive = new int[n][n];
        int[][] defensive = new int[n][n];
        for (int i = 0; i < n; i++) {
            offensive[i][i] = arr[i];
        }
        for (int L = n - 2; L >= 0; L--) {
            for (int R = L + 1; R < n; R++) {
                offensive[L][R] = Math.max(arr[L] + defensive[L + 1][R], arr[R] + defensive[L][R - 1]);
                defensive[L][R] = Math.min(offensive[L + 1][R], offensive[L][R - 1]);
            }
        }
        return offensive[0][n - 1];
    }

    public int maxScore2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return offensive(arr, 0, arr.length - 1);
    }

    private int offensive(int[] arr, int L, int R) {
        // 先手在[L...R]范围拿牌
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + defensive(arr, L + 1, R);
        int p2 = arr[R] + defensive(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    private int defensive(int[] arr, int L, int R) {
        // 后手在[L...R]范围拿牌
        if (L == R) {
            return 0;
        }
        // 先手肯定会拿走大的，留给我小的
        int p1 = offensive(arr, L + 1, R);
        int p2 = offensive(arr, L, R - 1);
        return Math.min(p1, p2);
    }
}
