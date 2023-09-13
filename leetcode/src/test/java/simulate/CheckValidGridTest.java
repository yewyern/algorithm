package simulate;

/**
 * <a href="https://leetcode.cn/problems/check-knight-tour-configuration">2596. 检查骑士巡视方案</a>
 *
 * @author xuzhou
 * @since 2023/9/13 15:29
 */
public class CheckValidGridTest {

    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int[][] steps = new int[n * n][];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                steps[grid[i][j]] = new int[] {i, j};
            }
        }
        for (int i = 1; i < n * n; i++) {
            boolean valid = isValid(steps[i], steps[i - 1]);
            if (!valid) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(int[] cur, int[] pre) {
        return (Math.abs(cur[0] - pre[0]) == 1 && Math.abs(cur[1] - pre[1]) == 2)
                || (Math.abs(cur[0] - pre[0]) == 2 && Math.abs(cur[1] - pre[1]) == 1);
    }

    public static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
