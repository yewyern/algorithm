package union_find_set;

/**
 * <a href="https://leetcode.cn/problems/surrounded-regions/">130. 被围绕的区域</a>
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * @author xuzhou
 * @since 2023/12/29 14:28
 */
public class SurroundedRegions {

    public void solve(char[][] board) {
        // 先从边界对'O'进行感染（改成'o'），所有边界感染完之后，遍历整个数组，将'o'改成'O'，其他字符改成'X'
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            infect(board, i, 0);
            infect(board, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            infect(board, 0, i);
            infect(board, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] == 'o' ? 'O' : 'X';
            }
        }
    }

    private void infect(char[][] board, int r, int c) {
        if (board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'o';
        if (r > 0) {
            infect(board, r - 1, c);
        }
        if (r < board.length - 1) {
            infect(board, r + 1, c);
        }
        if (c > 0) {
            infect(board, r, c - 1);
        }
        if (c < board[r].length - 1) {
            infect(board, r, c + 1);
        }
    }
}
