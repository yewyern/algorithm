package greedy_algorithm;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/gas-station/">134. 加油站</a>
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 * <p>
 * 输入: gas = [2,3,4], cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * <p>
 * <p>
 * 提示:
 * <p>
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 10^5
 * 0 <= gas[i], cost[i] <= 10^4
 *
 * @author xuzhou
 * @since 2023/7/24 16:51
 */
public class GasStationTest {

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int n = RandomUtils.nextInt(1, 10);
            int[] gas = RandomArray.generate(n, 0, 10);
            int[] cost = RandomArray.generate(n, 0, 10);
            int res1 = canCompleteCircuit(gas, cost);
            int res2 = canCompleteCircuit2(gas, cost);
            if (res1 != res2) {
                System.out.println("gas = " + Arrays.toString(gas));
                System.out.println("cost = " + Arrays.toString(cost));
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                canCompleteCircuit(gas, cost);
                break;
            }
        }
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int N = gas.length;
        int min = gas[0] -cost[0];
        int minIndex = 0;
        int pre = 0;
        for (int i = 0; i < N; i++) {
            pre += gas[i] - cost[i];
            if (pre < min) {
                min = pre;
                minIndex = i;
            }
        }
        return pre >= 0 ? (min >= 0 ? 0 : minIndex + 1) : -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int N = gas.length;
        int start = 0;
        while (start < N) {
            int gasSum = 0;
            int costSum = 0;
            boolean canComplete = true;
            for (int i = start; i < N + start; i++) {
                int p = i < N ? i : i - N;
                gasSum += gas[p];
                costSum += cost[p];
                if (gasSum < costSum) {
                    canComplete = false;
                    break;
                }
            }
            if (canComplete) {
                return start;
            }
            start++;
        }
        return -1;
    }
}
