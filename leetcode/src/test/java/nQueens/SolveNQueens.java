package nQueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @author zhou.xu
 * @date 2020/11/12 17:33
 */
public class SolveNQueens {

    private char[][] board;
    private List<List<String>> ans;
    private long places;

    public List<List<String>> solveNQueens(int n) {
        if (n < 1 || n == 2) {
            return Collections.emptyList();
        }
        if (n == 1) {
            return Collections.singletonList(Collections.singletonList("Q"));
        }
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        ans = new ArrayList<>();
        places = (1 << n) - 1;
        dfs(n, 0);
        return ans;
    }

    private void dfs(int n, int row) {
        // 001000
        // 001000
        for (int i = 0; i < n; i++) {
            if (((1 << i) & places) > 0) {
                // 当前位置没放过
                places ^= 1 << i;
                board[row][i] = 'Q';
                if (row == n - 1) {
                    ans.add(Arrays.stream(board).map(String::valueOf).collect(Collectors.toList()));
                } else {
                    dfs(n, row + 1);
                }
                // 状态回溯
                board[row][i] = '.';
                places ^= 1 << i;
            }
        }
    }

    public void test(int n) {
        List<List<String>> res = solveNQueens(n);
        res.forEach(a -> {
            System.out.println("[");
            a.forEach(System.out::println);
            System.out.println("]");
        });
    }

    @Test
    public void test() {
        test(4);
    }
}
