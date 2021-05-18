package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import org.junit.Test;

/**
 * @author zhou.xu
 * @date 2020/9/23 16:20
 */
public class SolveSudokuTest2 {

    // 每行有的数字
    private final int[] rows = new int[9];
    // 每列有的数字
    private final int[] cols = new int[9];
    // 每个块有的数字
    private final int[][] blocks = new int[3][3];
    private final LinkedList<int[]> spaces = new LinkedList<>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    flip(i, j, board[i][j] - '1');
                } else {
                    spaces.add(new int[]{i, j});
                }
            }
        }
        // 循环遍历只有一个可能性的方格
        boolean modified;
        do {
            modified = false;
            int size = spaces.size();
            for (int k = 0; k < size; k++) {
                int[] pos = spaces.remove();
                int i = pos[0], j = pos[1];
                // int为32位，翻转之后，前23位数为1，需要去除干扰
                int mask = ~(rows[i] | cols[j] | blocks[i / 3][j / 3]) & 0x1ff;
                if (mask != 0 && (mask & (mask - 1)) == 0) {
                    // 如果只有1个位置为1
                    int digit = Integer.bitCount(mask - 1);
                    board[i][j] = (char) (digit + '1');
                    flip(i, j, digit);
                    modified = true;
                } else {
                    spaces.add(pos);
                }
            }
        } while (modified);
        if (spaces.isEmpty()) {
            return;
        }
        // 循环回溯法
        // 没解决的方格
        final List<int[]> spaceList = new ArrayList<>(spaces);
        spaces.clear();
        // 尝试解决的路径
        final Stack<int[]> stack = new Stack<>();
        boolean forward = true;
        int index = 0;
        while (index >= 0 && index < spaceList.size()) {
            int[] pos = spaceList.get(index);
            int i = pos[0], j = pos[1];
            if (forward) {
                int mask = ~(rows[i] | cols[j] | blocks[i / 3][j / 3]) & 0x1ff;
                if (mask == 0) {
                    forward = false;
                    index--;
                } else {
                    int digitMask = mask & (-mask);
                    int digit = Integer.bitCount(digitMask - 1);
                    flip(i, j, digit);
                    mask &= mask - 1;
                    stack.push(new int[]{digit, mask});
                    board[i][j] = (char) (digit + '1');
                    index++;
                }
            } else {
                int[] pop = stack.pop();
                int lastDigit = pop[0], mask = pop[1];
                flip(i, j, lastDigit);
                if (mask == 0) {
                    index--;
                } else {
                    int digitMask = mask & (-mask);
                    int digit = Integer.bitCount(digitMask - 1);
                    flip(i, j, digit);
                    mask &= mask - 1;
                    stack.push(new int[]{digit, mask});
                    board[i][j] = (char) (digit + '1');
                    forward = true;
                    index++;
                }
            }
        }
    }

    /**
     * 翻转指定数字的位
     */
    private void flip(int row, int col, int digit) {
        int bit = 1 << digit;
        rows[row] ^= bit;
        cols[col] ^= bit;
        blocks[row / 3][col / 3] ^= bit;
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

    public void printMask(int mask) {
        for (; mask != 0; mask &= mask - 1) {
            int digitMask = mask & (-mask);
            int digit = Integer.bitCount(digitMask - 1);
            System.out.print(digit + 1);
        }
        System.out.println();
    }

    public void init() {
        Arrays.fill(rows, 0);
        Arrays.fill(cols, 0);
        for (int[] block : blocks) {
            Arrays.fill(block, 0);
        }
    }

    public void test(String... strs) {
        char[][] board = new char[strs.length][];
        for (int i = 0; i < board.length; i++) {
            board[i] = strs[i].toCharArray();
        }
        init();
        printSudoku(board);
        solveSudoku(board);
        printSudoku(board);
    }

    @Test
    public void test() {
//        test("53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5",
//            "....8..79");
        test("..9748...", "7........", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..", "...8.3.2.", "........6",
            "...2759..");
    }
}
