package union_find_set;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/number-of-closed-islands/">1254. 统计封闭岛屿的数目</a>
 *
 * @author zhou.xu
 * @since 2023/10/30 21:49
 */
public class NumberOfClosedIslandTest {

    @Test
    public void test() {
        closedIsland(new int[][]{{0, 0, 1, 1, 0, 1, 0, 0, 1, 0}, {1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}});
    }

    public int closedIsland(int[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int closed = infect(grid, i, j);
                    if (closed == 1) {
                        islands++;
                    }
                }
            }
        }
        return islands;
    }

    private int infect(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return 0;
        }
        if (grid[i][j] != 0) {
            return 1;
        }
        grid[i][j] = 2;
        return infect(grid, i + 1, j)
                & infect(grid, i - 1, j)
                & infect(grid, i, j + 1)
                & infect(grid, i, j - 1);
    }
}
