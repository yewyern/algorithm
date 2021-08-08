package sudoku;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhou.xu
 * @date 2020/9/27 17:32
 */
public class SudokuSolver implements Runnable {

    private volatile boolean stop = false;
    private volatile boolean pause = true;
    // 每行有的数字
    private final int[] rows = new int[9];
    // 每列有的数字
    private final int[] cols = new int[9];
    // 每个块有的数字
    private final int[][] blocks = new int[3][3];
    private final LinkedList<int[]> spaces = new LinkedList<>();
    private final Stack<int[]> stack = new Stack<>();
    private boolean forward = true;
    private SudokuBean[][] ground;
    private final long sleepInterval = 100;

    @Override
    public void run() {
        while (!stop) {
            while (!pause) {
                solve();
                sleep();
            }
            LockSupport.park();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(sleepInterval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        Arrays.fill(rows, 0);
        Arrays.fill(cols, 0);
        for (int[] block : blocks) {
            Arrays.fill(block, 0);
        }
        spaces.clear();
        stack.clear();
        forward = true;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public synchronized void setPause(Thread t) {
        this.pause = !pause;
        if (!pause) {
            LockSupport.unpark(t);
        }
    }

    public void addSpace(int x, int y, int digit, int type, int mask) {
        spaces.add(new int[]{x, y, digit, type, mask});
    }


    public void solve() {
        if (!spaces.isEmpty()) {
            if (forward) {
                // 先用枚举尝试解决
                boolean modified = false;
                for (int k = 0; k < spaces.size(); k++) {
                    int[] space = spaces.remove();
                    int i = space[0], j = space[1];
                    // int为32位，翻转之后，前23位数为1，需要去除干扰
                    int mask = ~(rows[i] | cols[j] | blocks[i / 3][j / 3]) & 0x1ff;
                    if (mask == 0) {
                        // 有空格出现冲突，需要回溯
                        forward = false;
                        spaces.add(space);
                        break;
                    } else if ((mask & mask - 1) == 0) {
                        // 如果只有1个位置为1
                        int digit = Integer.bitCount(mask - 1);
                        space[2] = digit;
                        space[3] = 1;
                        placeDigit(space);
                        stack.push(space);
                        modified = true;
                        break;
                    } else {
                        // 多个可能
                        spaces.add(space);
                    }
                }

                if (!modified) {
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
                    placeDigit(space);
                    stack.push(space);
                }
            } else {
                // 回溯
                if (!stack.isEmpty()) {
                    int[] last = stack.pop();
                    clearDigit(last);
                    int i = last[0], j = last[1], mask = last[4];
                    // = 0 表示都尝试过了，继续回溯
                    if (last[3] == 2 && mask != 0) {
                        int digitMask = mask & (-mask);
                        int digit = Integer.bitCount(digitMask - 1);
                        flip(i, j, digit);
                        mask &= mask - 1;
                        last[2] = digit;
                        last[3] = 2;
                        last[4] = mask;
                        stack.push(last);
                        placeDigit(last);
                        forward = true;
                    } else {
                        spaces.add(last);
                    }
                }
            }
        }
    }

    private void placeDigit(int[] space) {
        int i = space[0], j = space[1], digit = space[2], type = space[3];
        ground[i][j].setState(SudokuBean.INIT + type);
        ground[i][j].setValue(digit + 1);
        flip(i, j, digit);
    }

    private void clearDigit(int[] space) {
        int i = space[0], j = space[1], digit = space[2];
        ground[i][j].setState(SudokuBean.BLANK);
        ground[i][j].setValue(0);
        flip(i, j, digit);
    }

    /**
     * 翻转指定数字的位
     */
    public void flip(int row, int col, int digit) {
        int bit = 1 << digit;
        rows[row] ^= bit;
        cols[col] ^= bit;
        blocks[row / 3][col / 3] ^= bit;
    }

    public void setGround(SudokuBean[][] ground) {
        this.ground = ground;
    }
}
