package simulate;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/walking-robot-simulation/">874. 模拟行走机器人</a>
 * 机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 commands ：
 * <p>
 * -2 ：向左转 90 度
 * -1 ：向右转 90 度
 * 1 <= x <= 9 ：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点  obstacles[i] = (xi, yi) 。
 * <p>
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。
 * <p>
 * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）
 * <p>
 * <p>
 * 注意：
 * <p>
 * 北表示 +Y 方向。
 * 东表示 +X 方向。
 * 南表示 -Y 方向。
 * 西表示 -X 方向。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：commands = [4,-1,3], obstacles = []
 * 输出：25
 * 解释：
 * 机器人开始位于 (0, 0)：
 * 1. 向北移动 4 个单位，到达 (0, 4)
 * 2. 右转
 * 3. 向东移动 3 个单位，到达 (3, 4)
 * 距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25
 * 示例 2：
 * <p>
 * 输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出：65
 * 解释：机器人开始位于 (0, 0)：
 * 1. 向北移动 4 个单位，到达 (0, 4)
 * 2. 右转
 * 3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
 * 4. 左转
 * 5. 向北走 4 个单位，到达 (1, 8)
 * 距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= commands.length <= 10^4
 * commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9].
 * 0 <= obstacles.length <= 10^4
 * -3 * 10^4 <= xi, yi <= 3 * 10^4
 * 答案保证小于 231
 *
 * @author zhou.xu
 * @since 2023/7/19 21:23
 */
public class RobotWalkingSimulationTest {

    // 北:[0,1],东:[1,0]，南：[0,-1],西：[-1,0]
    private static final int[][] DIRECTIONS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public int robotSim(int[] commands, int[][] obstacles) {
        // todo
        int direction = 0, x = 0, y = 0;
        int max = 0;
        Map<Integer, List<Integer>> obstaclesX = resolveObstacles(obstacles, 0);
        Map<Integer, List<Integer>> obstaclesY = resolveObstacles(obstacles, 1);
        for (int command : commands) {
            if (command < 0) {
                direction = changeDirection(direction, command);
            } else {
                x = checkObstacles(obstaclesY.get(y), x, command, DIRECTIONS[direction][0]);
                y = checkObstacles(obstaclesX.get(x), y, command, DIRECTIONS[direction][1]);
                max = Math.max(max, x * x + y * y);
            }
        }
        return max;
    }

    private int changeDirection(int direction, int change) {
        if (change == -1) {
            return direction == 3 ? 0 : direction + 1;
        } else {
            return direction == 0 ? 3 : direction - 1;
        }
    }

    private Map<Integer, List<Integer>> resolveObstacles(int[][] obstacles, int keyIndex) {
        if (obstacles == null || obstacles.length == 0) {
            return new HashMap<>();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] obstacle : obstacles) {
            List<Integer> list = map.getOrDefault(obstacle[keyIndex], new ArrayList<>());
            list.add(obstacle[keyIndex == 1 ? 0 : 1]);
            map.put(obstacle[keyIndex], list);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            entry.getValue().sort(Comparator.naturalOrder());
        }
        return map;
    }

    private int checkObstacles(List<Integer> obstacles, int start, int step, int direction) {
        if (direction == 0 || obstacles == null || obstacles.size() == 0) {
            return start + step * direction;
        }
        for (int i = 0; i < step; i++) {
            start += direction;
            int index = Arrays.binarySearch(obstacles.toArray(), start);
            if (index >= 0) {
                return start - direction;
            }
        }
        return start;
    }

}
