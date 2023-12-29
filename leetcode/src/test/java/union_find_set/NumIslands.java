package union_find_set;

/**
 * <a href="https://leetcode.cn/problems/number-of-islands">200. 岛屿数量</a>
 * @author xuzhou
 * @since 2023/12/29 11:22
 */
public class NumIslands {

    public int numIslands(char[][] grid) {
        // 感染
        int islands = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    infect(grid, r, c);
                    islands++;
                }
            }
        }
        return islands;
    }

    private void infect(char[][] grid, int r, int c) {
        if (grid[r][c] != '1') {
            return;
        }
        grid[r][c] = '2';
        if (r > 0) {
            infect(grid, r - 1, c);
        }
        if (r < grid.length - 1) {
            infect(grid, r + 1, c);
        }
        if (c > 0) {
            infect(grid, r, c - 1);
        }
        if (c < grid[r].length - 1) {
            infect(grid, r, c + 1);
        }
    }

    int[] parents;
    int[] size;
    int islands;

    public int numIslands2(char[][] grid) {
        // 并查集
        int rows = grid.length;
        int cols = grid[0].length;
        int range = rows * cols;
        parents = new int[range];
        size = new int[range];
        for (int i = 0; i < range; i++) {
            parents[i] = i;
            size[i] = 1;
        }
        islands = 0;
        for (char[] row : grid) {
            for (char c : row) {
                if (c == '1') {
                    islands++;
                }
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 向上合并
                if (grid[i][j] == '1' && grid[i - 1][j] == '1') {
                    int p = i * cols + j;
                    union(p, p - cols);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                // 向左合并
                if (grid[i][j] == '1' && grid[i][j - 1] == '1') {
                    int p = i * cols + j;
                    union(p, p - 1);
                }
            }
        }
        return islands;
    }

    private void checkAndUnion(char[][] grid, int r1, int c1, int r2, int c2) {
        if (grid[r2][c2] != '1') {
            return;
        }
        int rows = grid.length;
        int cols = grid[0].length;
    }

    private int getParent(int i) {
        if (parents[i] != i) {
            parents[i] = getParent(parents[i]);
        }
        return parents[i];
    }

    private void union(int i, int j) {
        int p1 = getParent(i);
        int p2 = getParent(j);
        if (p1 != p2) {
            if (size[p1] > size[p2]) {
                parents[p2] = p1;
                size[p1] += size[p2];
            } else {
                parents[p1] = p2;
                size[p2] += size[p1];
            }
            islands--;
        }
    }
}
