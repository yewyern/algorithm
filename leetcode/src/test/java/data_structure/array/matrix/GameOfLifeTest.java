package data_structure.array.matrix;

/**
 * <a href="https://leetcode.cn/problems/game-of-life">289. 生命游戏</a>
 * @author xuzhou
 * @since 2023/12/21 17:48
 */
public class GameOfLifeTest {

    public void gameOfLife(int[][] board) {
        process(board, 0, 0);
    }

    private void process(int[][] board, int row, int col) {
        if (row == board.length) {
            return;
        }
        if (col == board[row].length) {
            process(board, row + 1, 0);
            return;
        }
        int state = nextState(board, row, col);
        process(board, row, col + 1);
        board[row][col] = state;
    }

    public void gameOfLife2(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] next = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = nextState(board, i, j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = next[i][j];
            }
        }
    }

    private int nextState(int[][] board, int row, int col) {
        int lives = countLives(board, row, col);
        if (board[row][col] == 0) {
            return lives == 3 ? 1 : 0;
        }
        return lives == 2 || lives == 3 ? 1 : 0;
    }

    private int countLives(int[][] board, int row, int col) {
        int m = board.length;
        int lives = 0;
        if (row > 0) {
            for (int i = col - 1; i <= col + 1; i++) {
                lives += getLive(board, row - 1, i);
            }
        }
        lives += getLive(board, row, col - 1);
        lives += getLive(board, row, col + 1);
        if (row < m - 1) {
            for (int i = col - 1; i <= col + 1; i++) {
                lives += getLive(board, row + 1, i);
            }
        }
        return lives;
    }

    private int getLive(int[][] board, int row, int col) {
        return col >= 0 && col < board[0].length ? board[row][col] : 0;
    }
}
