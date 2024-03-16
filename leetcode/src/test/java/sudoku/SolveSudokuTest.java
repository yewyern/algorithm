package sudoku;

import java.util.Arrays;
import org.junit.Test;

/**
 * @author zhou.xu
 * @since 2020/9/22 17:36
 */
public class SolveSudokuTest {

    private final int[][] map = new int[9][9];
    private final int[] notSolveInRow = new int[9];
    private int tryCount = 0;

    public void solveSudoku(char[][] board) {
        Arrays.stream(map).forEach(item -> Arrays.fill(item, 511));
        Arrays.fill(notSolveInRow, 511);
        easySolveSudoku(board);
    }

    public void easySolveSudoku(char[][] board) {
        int count;
        do {
            count = 9;
            tryCount = 0;
            for (int i = 0; i < board.length; i++) {
                if (notSolveInRow[i] == 0) {
                    count--;
                } else {
                    for (int j = 0; j < board[i].length; j++) {
                        solve(board, i, j);
                    }
                }
            }
            printSudoku(board);
        } while (count > 0 && tryCount > 0);
    }

    public void solve(char[][] board, int row, int col) {
        if ((notSolveInRow[row] & (1 << col)) == 0) {
            // 处理过了
            return;
        }
        if (board[row][col] != '.') {
            map[row][col] = 1 << (board[row][col] - '1');
            notSolveInRow[row] &= ~(1 << col);
            for (int i = 0; i < 9; i++) {
                if (board[row][i] == '.') {
                    map[row][i] &= ~map[row][col];
                }
            }
            for (int i = 0; i < 9; i++) {
                if (board[i][col] == '.') {
                    map[i][col] &= ~map[row][col];
                }
            }
            int fistRow = row / 3 * 3;
            int fistCol = col / 3 * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[fistRow + i][fistCol + j] == '.') {
                        map[fistRow + i][fistCol + j] &= ~map[row][col];
                    }
                }
            }
        } else {
            int bit = map[row][col];
            if ((bit & (bit - 1)) == 0) {
                tryCount++;
                // 只有一位是1
                int num = 1;
                while ((bit >> num) != 0) {
                    num++;
                }
                board[row][col] = (char) ('0' + num);
                solve(board, row, col);
            } else {
                int bitMap1 = 0;
                int bitMap2 = 0;
                for (int i = 0; i < 9; i++) {
                    if (board[row][i] == '.' && i != col) {
                        bitMap1 |= map[row][i];
                        bitMap2 ^= map[row][i];
                        if (bitMap2 == 0) {
                            map[row][col] ^= bitMap1;
                            if ((map[row][col] & (map[row][col] - 1)) == 0) {
                                solve(board, row, col);
                                return;
                            }
                        }
                    }
                }
                bitMap1 = 0;
                bitMap2 = 0;
                for (int i = 0; i < 9; i++) {
                    if (board[i][col] == '.' && i != row) {
                        bitMap1 |= map[i][col];
                        bitMap2 ^= map[i][col];
                        if (bitMap2 == 0) {
                            map[row][col] ^= bitMap1;
                            if ((map[row][col] & (map[row][col] - 1)) == 0) {
                                solve(board, row, col);
                                return;
                            }
                        }
                    }
                }
                int fistRow = row / 3 * 3;
                int fistCol = col / 3 * 3;
                bitMap1 = 0;
                bitMap2 = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[fistRow + i][fistCol + j] == '.' && fistRow + i != row && fistCol + j != col) {
                            bitMap1 |= map[fistRow + i][fistCol + j];
                            bitMap2 ^= map[fistRow + i][fistCol + j];
                            if (bitMap2 == 0) {
                                map[row][col] ^= bitMap1;
                                if ((map[row][col] & (map[row][col] - 1)) == 0) {
                                    solve(board, row, col);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void test(String... strs) {
        char[][] board = new char[strs.length][];
        for (int i = 0; i < board.length; i++) {
            board[i] = strs[i].toCharArray();
        }
        printSudoku(board);
        solveSudoku(board);
        printSudoku(board);
    }

    public void printSudoku(char[][] board) {
        for (char[] cs : board) {
            for (char c : cs) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    public void printBit(int num) {
        System.out.println(Integer.toBinaryString(num));
    }

    @Test
    public void test() {
//        test("53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5",
//            "....8..79");
//        test("..9748...", "7........", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..", "...8.3.2.", "........6",
//            "...2759..");
        printBit(0);
        printBit(-1);
        int mask = 8;
        int digitMask = mask & (-mask);
        int digit = Integer.bitCount(digitMask - 1);
        System.out.println("digit = " + digit);
    }
}
