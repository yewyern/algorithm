package search;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/reach-a-number/">754. 到达终点数字</a>
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 *
 * 你可以做一些数量的移动 numMoves :
 *
 * 每次你可以选择向左或向右移动。
 * 第 i 次移动（从  i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
 * 给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: target = 2
 * 输出: 3
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 -1 。
 * 第三次移动，从 -1 到 2 。
 * 示例 2:
 *
 * 输入: target = 3
 * 输出: 2
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 3 。
 * 提示:
 *
 * -1000000000 <= target <= 1000000000
 * target != 0
 *
 * @author xuzhou
 * @since 2022/11/4 9:38
 */
public class ReachANumberTest {

    public int reachNumber(int target) {
        target = Math.abs(target);
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 3;
        }
        // 1 + 2 + ... n = n(n + 1) / 2 : 1, 3, 6, 10, 15, 21
        // 1 = 1
        // 2 = 1 -2 3
        // 3 = 1 2
        // 4 = 1 2 -3 4
        // 5 = 1 2 3 4 -5
        // 6 = 1 2 3
        // 7 = 1 2 3 -4 5
        // 8 = -1 2 3 4
        // 9 = 1 -2 3 4 5
        // 10 = 1 2 3 4
        // 11 = 1 -2 3 4 5
        // 12 = 1 -2 3 4 5 -6 7
        // 13 = -1 2 3 4 5
        long n = 2;
        long sum = n * (n + 1) >> 1;
        while (sum < target) {
            n = n << 1;
            sum = n * (n + 1) >> 1;
        }
        if (sum == target) {
            return (int) n;
        }
        long l = n >> 1, r = n;
        while (l < r) {
            long mid = (l + r) >> 1;
            sum = mid * (mid + 1) >> 1;
            if (sum == target) {
                return (int) mid;
            }
            if (sum > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        n = l;
        while (true) {
            sum = n * (n + 1) >> 1;
            long diff = sum - target;
            if (diff == n) {
                return (int) n;
            }
            if ((diff & 1) == 0) {
                return (int) n;
            }
            n++;
        }
    }

    public int reachNumberComparison(int target) {
        // 内存O(2^n)
        int count = 1;
        if (target == 1 || target == -1) {
            return count;
        }
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(-1);
        while (true) {
            count++;
            List<Integer> temp = new LinkedList<>();
            for (Integer num : list) {
                int sum = num + count;
                if (sum == target) {
                    return count;
                }
                temp.add(sum);
                sum = num - count;
                if (sum == target) {
                    return count;
                }
                temp.add(sum);
            }
            list = temp;
        }
    }

    @Test
    public void test() {
        for (int i = 1; i < 100; i++) {
            int num = reachNumber(i);
            int numberComparison = reachNumberComparison(i);
            if (num != numberComparison) {
                System.out.println(
                    "target = " + i + ", num = " + num + ", numberComparison = " + numberComparison + ", n * (n + 1) / 2 = " + (numberComparison * (numberComparison + 1) >> 1));
            }
        }
    }
}
