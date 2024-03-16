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
        int k = 0;
        // 找到刚好大于target的全加序列
        while (target > 0) {
            k++;
            target -= k;
        }
        // target = -target;
        // 此时的target<= k
        // 如果target是偶数，只要翻转target/2的符号，即可满足
        // 如果target是奇数
        // 1、k是偶数，k + 1是奇数，target + k + 1为偶数，可翻转(target + k + 1)/2
        // 2、k是奇数，k + 1是偶数，target + k + 1为奇数，target + k + 1 + k + 2为偶数，可翻转(target + k + 1 + k + 2)/2
        return target % 2 == 0 ? k : k + 1 + k % 2;
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
