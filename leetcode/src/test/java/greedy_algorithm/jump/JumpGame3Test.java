package greedy_algorithm.jump;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/jump-game-iii/">1306. 跳跃游戏 III</a>
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 * <p>
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
 * <p>
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [4,2,3,0,3,1,2], start = 5
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
 * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 2：
 * <p>
 * 输入：arr = [4,2,3,0,3,1,2], start = 0
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 0 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 3：
 * <p>
 * 输入：arr = [3,0,2,1,2], start = 2
 * 输出：false
 * 解释：无法到达值为 0 的下标 1 处。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 *
 * @author zhou.xu
 * @since 2022/11/11 17:09
 */
public class JumpGame3Test {

    public boolean canReachBFS(int[] arr, int start) {
        // BFS
        int N = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            if (i < 0 || i >= N) {
                continue;
            }
            if (arr[i] == 0) {
                return true;
            }
            if (arr[i] > 0) {
                arr[i] = -arr[i];
                queue.add(i + arr[i]);
                queue.add(i - arr[i]);
            }
        }
        return false;
    }

    public boolean canReachDFS(int[] arr, int start) {
        // DFS
        int N = arr.length;
        Deque<Integer> stack = new LinkedList<>();
        stack.add(start);
        while (!stack.isEmpty()) {
            Integer i = stack.pollLast();
            if (i < 0 || i >= N) {
                continue;
            }
            if (arr[i] == 0) {
                return true;
            }
            if (arr[i] > 0) {
                arr[i] = -arr[i];
                stack.add(i + arr[i]);
                stack.add(i - arr[i]);
            }
        }
        return false;
    }

    public boolean canReach(int[] arr, int start) {
        // 递归
        if (start >= 0 && start < arr.length && arr[start] >= 0) {
            if (arr[start] == 0) {
                return true;
            }
            arr[start] = -arr[start];
            return canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));
        System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 0));
        System.out.println(canReach(new int[]{3, 0, 2, 1, 2}, 2));
    }
}
