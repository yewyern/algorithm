package data_structure.array.matrix;

import junit.framework.TestCase;
import org.junit.Test;

/**3
 * <a href="https://leetcode.cn/problems/valid-sudoku/?envType=study-plan-v2&envId=top-interview-150">36. 有效的数独</a>
 * 判断一个9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字1-9在每一行只能出现一次。 数字1-9在每一列只能出现一次。 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 * <p>
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * 说明:
 *
 * <p>一个有效的数独（部分已被填充）不一定是可解的。
 * <p>只需要根据以上规则，验证已经填入的数字是否有效即可。
 * <p>给定数独序列只包含数字1-9和字符'.'。
 * <p>给定数独永远是9x9形式的。
 *
 * @author zhou.xu
 * @since 2020/8/17 13:35
 */
public class IsValidSudokuTest {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] squares = new boolean[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '1';
                    int squareIndex = i / 3 * 3 + j / 3;
                    if (rows[i][num] || columns[j][num] || squares[squareIndex][num]) {
                        return false;
                    } else {
                        rows[i][num] = true;
                        columns[j][num] = true;
                        squares[squareIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }

    @Test
    public void testIsValidSudoku() {
        char[][] b = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        test(b, true);
        char[][] b1 = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        test(b1, false);
    }

    public void test(char[][] board, boolean expected) {
        boolean res = isValidSudoku(board);
        System.out.println(res);
        TestCase.assertEquals(res, expected);
    }


}
