package nQueens;

import org.junit.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/n-queens-ii/">52. N 皇后 II</a>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 * @author xuzhou
 * @since 2023/6/1 18:01
 */
public class TotalNQueensTest {

    public int totalNQueens(int n) {
        int[] placed = new int[n];
        int placedCols = 0;
        return dfs(placed, placedCols, n, 0);
    }

    private int dfs(int[] placed, int placedCols, int n, int row) {
        if (row == n) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!isValidPlace(placed, placedCols, row, i)) {
                // 此位置不能放置，返回
                continue;
            }
            placed[row] = i;
            placedCols |= 1 << i;
            count += dfs(placed, placedCols, n, row + 1);
            placedCols ^= 1 << i;
        }
        return count;
    }

    private boolean isValidPlace(int[] placed, int placedCols, int row, int col) {
        // 判断列是否已经放置过
        if ((placedCols | (1 << col)) == placedCols) {
            return false;
        }
        // 判断斜线上是否放置过
        for (int i = 0; i < row; i++) {
            if (row - i == Math.abs(col - placed[i])) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        for (int i = 1; i < 5; i++) {
            System.out.println(i + " : " + totalNQueens(i));
        }
    }
}
