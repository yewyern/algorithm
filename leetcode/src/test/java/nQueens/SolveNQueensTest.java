package nQueens;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/n-queens/">51. N 皇后</a>
 * <p>
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * @author xuzhou
 * @since 2023/6/1 16:52
 */
public class SolveNQueensTest {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        // 回溯算法求N皇后问题
        // 1、定义棋盘
        char[][] board = new char[n][n];
        for (char[] cs : board) {
            Arrays.fill(cs, '.');
        }
        // 2、回溯求解
        int[] placed = new int[n];
        dfs(res, board, placed, n, 0);
        return res;
    }

    private void dfs(List<List<String>> res, char[][] board, int[] placed, int n, int row) {
        if (row == n) {
            // 达到第n行
            res.add(toResult(board));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isValidPlace(placed, row, i)) {
                continue;
            }
            // 放置皇后
            placed[row] = i;
            board[row][i] = 'Q';
            dfs(res, board, placed, n, row + 1);
            // 状态回滚
            board[row][i] = '.';
        }
    }

    private List<String> toResult(char[][] board) {
        List<String> list = new ArrayList<>(board.length);
        for (char[] chars : board) {
            list.add(String.valueOf(chars));
        }
        return list;
    }

    private boolean isValidPlace(int[] placed, int row, int col) {
        for (int i = 0; i < row; i++) {
            // 1、判断是否在同一列
            if (placed[i] == col) {
                return false;
            }
            // 2、判断是否在斜线上是否存在重叠
            // 1,1 2,2
            // 2,2 1,3
            if (row - i == Math.abs(col - placed[i])) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        List<List<String>> lists = solveNQueens(4);
        System.out.println("lists = " + lists);
    }

}
