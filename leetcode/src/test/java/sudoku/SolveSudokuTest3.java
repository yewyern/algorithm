package sudoku;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import org.junit.Test;

/**
 * @author zhou.xu
 * @date 2020/9/23 16:20
 */
public class SolveSudokuTest3 {

    // 每行有的数字
    private final int[] rows = new int[9];
    // 每列有的数字
    private final int[] cols = new int[9];
    // 每个块有的数字
    private final int[][] blocks = new int[3][3];
    private final LinkedList<int[]> spaces = new LinkedList<>();
    private final Stack<int[]> stack = new Stack<>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    flip(i, j, board[i][j] - '1');
                } else {
                    // 坐标行，坐标列，数字，类型（1-唯一值，2-尝试值）, mask（回溯的时候用）
                    spaces.add(new int[]{i, j, 0, 0, 0});
                }
            }
        }
        boolean forward = true;
        while (!spaces.isEmpty()) {
            if (forward) {
                // 先用枚举尝试解决
                boolean modified;
                do {
                    modified = false;
                    int size = spaces.size();
                    for (int k = 0; k < size; k++) {
                        int[] space = spaces.remove();
                        int i = space[0], j = space[1];
                        // int为32位，翻转之后，前23位数为1，需要去除干扰
                        int mask = ~(rows[i] | cols[j] | blocks[i / 3][j / 3]) & 0x1ff;
                        if (mask == 0) {
                            // 有空格出现冲突，需要回溯
                            forward = false;
                            spaces.add(space);
                        } else if ((mask & mask - 1) == 0) {
                            // 如果只有1个位置为1
                            int digit = Integer.bitCount(mask - 1);
                            space[2] = digit;
                            space[3] = 1;
                            placeDigit(board, space);
                            stack.push(space);
                            modified = true;
                        } else {
                            // 多个可能
                            spaces.add(space);
                        }
                    }
                } while (modified);
                // 枚举解决完了
                if (spaces.isEmpty()) {
                    break;
                }
                if (forward) {
                    // 枚举解决不完,尝试一把
                    int[] space = spaces.remove();
                    int i = space[0], j = space[1];
                    // int为32位，翻转之后，前23位数为1，需要去除干扰
                    int mask = ~(rows[i] | cols[j] | blocks[i / 3][j / 3]) & 0x1ff;
                    // 取最后一位的未知
                    int digitMask = mask & (-mask);
                    int digit = Integer.bitCount(digitMask - 1);
                    mask &= mask - 1;
                    space[2] = digit;
                    space[3] = 2;
                    space[4] = mask;
                    placeDigit(board, space);
                    stack.push(space);
                }
            } else {
                // 回溯
                int[] last = stack.pop();
                while (last[3] == 1 && !stack.isEmpty()) {
                    int i = last[0], j = last[1], digit = last[2];
                    // 翻转
                    flip(i, j, digit);
                    board[i][j] = '.';
                    // 加回到空格
                    spaces.add(last);
                    last = stack.pop();
                }
                if (last[3] == 1) {
                    // 如果最后一个是唯一值，没得尝试了
                    break;
                }
                int i = last[0], j = last[1], lastDigit = last[2], mask = last[4];
                flip(i, j, lastDigit);
                board[i][j] = '.';
                // = 0 表示都尝试过了，继续回溯
                if (mask != 0) {
                    int digitMask = mask & (-mask);
                    int digit = Integer.bitCount(digitMask - 1);
                    flip(i, j, digit);
                    mask &= mask - 1;
                    last[2] = digit;
                    last[3] = 2;
                    last[4] = mask;
                    stack.push(last);
                    board[i][j] = (char) (digit + '1');
                    forward = true;
                } else {
                    // 加回到空格
                    spaces.add(last);
                }
            }
        }
    }

    private void placeDigit(char[][] board, int[] space) {
        int i = space[0], j = space[1], digit = space[2];
        board[i][j] = (char) (digit + '1');
        flip(i, j, digit);
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
