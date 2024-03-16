package simulate;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/escape-the-spreading-fire/">2258. 逃离火灾</a>
 *
 * @author xuzhou
 * @since 2023/11/10 16:26
 */
public class EscapeFromSpreadingFireTest {

    private static final int MAXIMUM = 1000000000;
    private static final int[][] DEST = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    @Test
    public void test() {
        System.out.println(maximumMinutes(new int[][]{{0, 2, 0, 0, 0, 0, 0}, {0, 0, 0, 2, 2, 1, 0}, {0, 2, 0, 0, 1, 2, 0}, {0, 0, 2, 2, 2, 0, 2}, {0, 0, 0, 0, 0, 0, 0}}));
    }

    public int maximumMinutes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] g = new int[m][n];
        Queue<Position> fires = new LinkedList<>();
        Queue<Position> man = new LinkedList<>();
        man.add(new Position(0, 0));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = -grid[i][j];
                if (grid[i][j] == 1) {
                    fires.add(new Position(i, j));
                }
            }
        }
        boolean arrived = false;
        int step = 1;
        while (true) {
            Queue<Position> nextFires = new LinkedList<>();
            Queue<Position> nextMan = new LinkedList<>();
            while (!fires.isEmpty()) {
                Position p = fires.poll();
                for (int[] dest : DEST) {
                    Position next = fire(p.row + dest[0], p.col + dest[1], g);
                    if (next != null) {
                        nextFires.add(next);
                        if (next.row == m - 1 && next.col == n - 1) {
                            if (arrived) {
                                return step - g[next.row][next.col] - 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                }
            }
            if (nextFires.isEmpty()) {
                return MAXIMUM;
            }
            while (!man.isEmpty()) {
                Position p = man.poll();
                for (int[] dest : DEST) {
                    Position next = move(p.row + dest[0], p.col + dest[1], g, step);
                    if (next != null) {
                        nextMan.add(next);
                        if (next.row == m - 1 && next.col == n - 1) {
                            arrived = true;
                        }
                    }
                }
            }
            if (nextMan.isEmpty() && !arrived) {
                return -1;
            }
            fires = nextFires;
            man = nextMan;
            step++;
        }
    }

    private Position fire(int row, int col, int[][] g) {
        int m = g.length;
        int n = g[0].length;
        if (row < 0 || col < 0 || row >= m || col >= n || g[row][col] < 0) {
            return null;
        }
        if (row == m - 1 && col == n - 1) {
            return new Position(row, col);
        }
        g[row][col] = -1;
        return new Position(row, col);
    }

    private Position move(int row, int col, int[][] g, int step) {
        if (row < 0 || col < 0 || row >= g.length || col >= g[row].length || g[row][col] != 0) {
            return null;
        }
        g[row][col] = step;
        return new Position(row, col);
    }


    private static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
