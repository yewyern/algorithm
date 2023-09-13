package sliding_window;

import utils.RandomUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 加油站的良好出发点问题
 * gas = [ 1 1 3 1] 表示加油站的油数量
 * cost =[ 2 2 1 1] 表示从i->i+1号加油站需要的油数量，n-1位置的数量代表从n-1到0的
 * 计算并返回一个boolean[]，从每个加油站出发是否能绕所有加油站一圈
 *
 * @author zhou.xu
 * @since 2023/6/18 13:17
 */
public class GasStation {

    public static boolean[] availableGasStation(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length) {
            return null;
        }
        int N = gas.length;
        boolean[] ans = new boolean[N];
        // 当前加油站和到达下一站所需油量的差值，负数表示需要前面有结余，正数表示有富余
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            diff[i] = gas[i] - cost[i];
        }
        // 上面差值累加和，2N长度是因为需要绕一周，从N-1位置出发的累加和也需要计算出来
        // 此累加和表示的是从0位置出发经过各个加油站后剩余的油量
        // 如果sum[i] < 0，即代表从0位置无法到达该处
        // 从1位置出发的，则如果sum[i] < sum[o]，即代表从1位置无法到达该处
        // 以此类推，从i位置出发，如果存在j位置，sum[j] < sum[i - 1]，则代表从i位置出发，无法到达j位置
        // j位置怎么得到？可使用最小值队列，当最小值都满足条件，则必然满足条件
        int[] sum = new int[2 * N - 1];
        sum[0] = diff[0];
        for (int i = 1; i < 2 * N - 1; i++) {
            sum[i] = sum[i - 1] + (i < N ? diff[i] : diff[i - N]);
        }
        LinkedList<Integer> minQueue = new LinkedList<>();
        // 使用滑动窗口，窗口长度达到N时第一次收集
        for (int i = 0; i < 2 * N - 1; i++) {
            while (!minQueue.isEmpty() && sum[minQueue.peekLast()] >= sum[i]) {
                minQueue.pollLast();
            }
            minQueue.addLast(i);
            if (i >= N - 1) {
                int preSum = i >= N ? sum[i - N] : 0;
                ans[i - N + 1] = sum[minQueue.peekFirst()] >= preSum;
                if (i - N + 1 == minQueue.peekFirst()) {
                    minQueue.pollFirst();
                }
            }
        }
        return ans;
    }

    public static boolean[] availableGasStationComparison(int[] gas, int[] cost) {
        // 暴力法
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length) {
            return null;
        }
        int N = gas.length;
        boolean[] ans = new boolean[N];
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            diff[i] = gas[i] - cost[i];
        }
        for (int l = 0; l < N; l++) {
            boolean f = true;
            int sum = 0;
            for (int r = l; r < l + N; r++) {
                sum += diff[r >= N ? r - N : r];
                if (sum < 0) {
                    f = false;
                }
            }
            ans[l] = f;
        }
        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int len = RandomUtils.nextInt(100) + 1;
            int[] gas = RandomUtils.generate(len, 1, 100);
            int[] cost = RandomUtils.generate(len, 1, 100);
            boolean[] res = availableGasStation(gas, cost);
            boolean[] res2 = availableGasStationComparison(gas, cost);
            if (!Arrays.equals(res, res2)) {
                System.out.println("gas = " + Arrays.toString(gas));
                System.out.println("cost = " + Arrays.toString(cost));
                System.out.println("res = " + Arrays.toString(res));
                System.out.println("res2 = " + Arrays.toString(res2));
                break;
            }
        }

    }
}
