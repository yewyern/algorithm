package simulate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/queens-that-can-attack-the-king">1222. 可以攻击国王的皇后</a>
 *
 * @author xuzhou
 * @since 2023/9/14 16:31
 */
public class QueensAttacktheKingTest {

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        // 初始化王后的位置地图
        boolean[][] queenMap = new boolean[8][8];
        for (int[] queen : queens) {
            queenMap[queen[0]][queen[1]] = true;
        }
        int[][] directions = new int[][]{{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] direction : directions) {
            int x = king[0], y = king[1];
            do {
                // 从王的方向找最近的一个后
                if (queenMap[x][y]) {
                    ans.add(Arrays.asList(x, y));
                    break;
                }
                x += direction[0];
                y += direction[1];
            } while (x >= 0 && x < 8 && y >= 0 && y < 8);
        }
        return ans;
    }

    public List<List<Integer>> queensAttacktheKing2(int[][] queens, int[] king) {
        Queen[] attacks = new Queen[8]; // 8个方向上可以攻击king的queens
        // 0-7的方向分别是[1,0],[1,1],[0,1],[-1,1],[-1,0],[-1,-1],[0,-1],[1,-1]
        for (int[] queen : queens) {
            int i = attackDirection(queen, king);
            if (i < 0) {
                continue;
            }
            int distance = calcDistance(queen, king);
            if (attacks[i] == null || attacks[i].dist > distance) {
                attacks[i] = new Queen(queen, distance);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Queen attack : attacks) {
            if (attack != null) {
                ans.add(Arrays.asList(attack.pos[0], attack.pos[1]));
            }
        }
        return ans;
    }

    private int attackDirection(int[] queen, int[] king) {
        if (queen[0] == king[0]) {
            return queen[1] > king[1] ? 2 : 6;
        }
        if (queen[1] == king[1]) {
            return queen[0] > king[0] ? 0 : 4;
        }
        int w = king[0] - queen[0];
        int h = king[1] - queen[1];
        if (w == h) {
            return w < 0 ? 5 : 1;
        }
        if (w == -h) {
            return w < 0 ? 3 : 7;
        }
        return -1;
    }

    private int calcDistance(int[] queen, int[] king) {
        int w = king[0] - queen[0];
        int h = king[1] - queen[1];
        return w * w + h * h;
    }

    private static class Queen {
        final int[] pos;// queen的位置
        final int dist;// 和king的距离

        public Queen(int[] pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }
    }
}
