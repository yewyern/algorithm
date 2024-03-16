package greedy_algorithm;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/earliest-possible-day-of-full-bloom/">2136. 全部开花的最早一天</a>
 * 你有 n 枚花的种子。每枚种子必须先种下，才能开始生长、开花。播种需要时间，种子的生长也是如此。给你两个下标从 0 开始的整数数组 plantTime 和 growTime ，每个数组的长度都是 n ：
 * <p>
 * plantTime[i] 是 播种 第 i 枚种子所需的 完整天数 。每天，你只能为播种某一枚种子而劳作。无须 连续几天都在种同一枚种子，但是种子播种必须在你工作的天数达到 plantTime[i] 之后才算完成。
 * growTime[i] 是第 i 枚种子完全种下后生长所需的 完整天数 。在它生长的最后一天 之后 ，将会开花并且永远 绽放 。
 * 从第 0 开始，你可以按 任意 顺序播种种子。
 * <p>
 * 返回所有种子都开花的 最早 一天是第几天。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：plantTime = [1,4,3], growTime = [2,3,1]
 * 输出：9
 * 解释：灰色的花盆表示播种的日子，彩色的花盆表示生长的日子，花朵表示开花的日子。
 * 一种最优方案是：
 * 第 0 天，播种第 0 枚种子，种子生长 2 整天。并在第 3 天开花。
 * 第 1、2、3、4 天，播种第 1 枚种子。种子生长 3 整天，并在第 8 天开花。
 * 第 5、6、7 天，播种第 2 枚种子。种子生长 1 整天，并在第 9 天开花。
 * 因此，在第 9 天，所有种子都开花。
 * 示例 2：
 * <p>
 * <p>
 * 输入：plantTime = [1,2,3,2], growTime = [2,1,2,1]
 * 输出：9
 * 解释：灰色的花盆表示播种的日子，彩色的花盆表示生长的日子，花朵表示开花的日子。
 * 一种最优方案是：
 * 第 1 天，播种第 0 枚种子，种子生长 2 整天。并在第 4 天开花。
 * 第 0、3 天，播种第 1 枚种子。种子生长 1 整天，并在第 5 天开花。
 * 第 2、4、5 天，播种第 2 枚种子。种子生长 2 整天，并在第 8 天开花。
 * 第 6、7 天，播种第 3 枚种子。种子生长 1 整天，并在第 9 天开花。
 * 因此，在第 9 天，所有种子都开花。
 * 示例 3：
 * <p>
 * 输入：plantTime = [1], growTime = [1]
 * 输出：2
 * 解释：第 0 天，播种第 0 枚种子。种子需要生长 1 整天，然后在第 2 天开花。
 * 因此，在第 2 天，所有种子都开花。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == plantTime.length == growTime.length
 * 1 <= n <= 10^5
 * 1 <= plantTime[i], growTime[i] <= 10^4
 *
 * @author xuzhou
 * @since 2023/8/31 17:16
 */
public class EarliestFullBloomTest {

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int len = RandomUtils.nextInt(1, 20);
            int[] plantTime = RandomArray.generate(len, 1, 100);
            int[] growTime = RandomArray.generate(len, 1, 100);
            int res1 = earliestFullBloom(plantTime, growTime);
            int res2 = earliestFullBloom2(plantTime, growTime);
            if (res1 != res2) {
                System.out.println("出错了！");
                System.out.println("plantTime = " + Arrays.toString(plantTime));
                System.out.println("growTime = " + Arrays.toString(growTime));
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                break;
            }
        }
//        int[] plantTime = new int[] {27,5,24,17,27,4,23,16,6,26,13,17,21,3,9,10,28,26,4,10,28,2};
//        int[] growTime = new int[] {26,9,14,17,6,14,23,24,11,6,27,14,13,1,15,5,12,15,23,27,28,12};
//        System.out.println(earliestFullBloom(plantTime, growTime));
    }

    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int max = 0;
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, (i, j) -> growTime[j] - growTime[i]);
        int totalPlantTime = 0;
        for (Integer index : indexes) {
            totalPlantTime += plantTime[index];
            max = Math.max(max, totalPlantTime + growTime[index]);
        }
        return max;
    }

    public int earliestFullBloom0(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int max = 0;
        Flower[] flowers = new Flower[n];
        for (int i = 0; i < n; i++) {
            flowers[i] = new Flower(plantTime[i], growTime[i]);
        }
        // 堆写法是倒着写的，正着也是一样的，growTime最长的最先种
        Arrays.sort(flowers, Comparator.reverseOrder());
        int totalPlantTime = 0;
        for (Flower flower : flowers) {
            totalPlantTime += flower.plantTime;
            max = Math.max(max, totalPlantTime + flower.growTime);
        }
        return max;
    }

    public int earliestFullBloom1(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int totalPlantTime = 0;
        Flower[] flowers = new Flower[n];
        for (int i = 0; i < n; i++) {
            totalPlantTime += plantTime[i];
            flowers[i] = new Flower(plantTime[i], growTime[i]);
        }
        for (int i = (n >> 1) - 1; i >= 0; i--) {
            siftDown(flowers, i, n);
        }
        int max = 0;
        for (int i = n; i > 0; i--) {
            max = Math.max(max, totalPlantTime + flowers[0].growTime);
            totalPlantTime -= flowers[0].plantTime;
            swap(flowers, 0, i - 1);
            siftDown(flowers, 0, i - 1);
        }
        return max;
    }

    private void siftDown(Flower[] flowers, int i, int end) {
        while (i < end) {
            int top = top(flowers, i, (i << 1) + 1, (i << 1) + 2, end);
            if (top == i) {
                break;
            }
            swap(flowers, top, i);
            i = top;
        }
    }

    private int top(Flower[] flowers, int i, int j, int k, int end) {
        return topIndex(flowers, i, topIndex(flowers, j, k, end), end);
    }

    private int topIndex(Flower[] flowers, int i, int j, int end) {
        if (i < end && j < end) {
            return flowers[i].compareTo(flowers[j]) < 0 ? i : j;
        }
        return i < end ? i : j;
    }

    private void swap(Flower[] flowers, int i, int j) {
        Flower flower = flowers[i];
        flowers[i] = flowers[j];
        flowers[j] = flower;
    }

    public int earliestFullBloom2(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int totalPlantTime = 0;
        List<Flower> flowers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            totalPlantTime += plantTime[i];
            flowers.add(new Flower(plantTime[i], growTime[i]));
        }
        int max = 0;
        PriorityQueue<Flower> queue = new PriorityQueue<>(flowers);
        while (!queue.isEmpty()) {
            max = Math.max(max, totalPlantTime + queue.peek().growTime);
            Flower flower = queue.poll();
            totalPlantTime -= flower.plantTime;
        }
        return max;
    }

    private static class Flower implements Comparable<Flower> {
        final int plantTime;
        final int growTime;

        public Flower(int plantTime, int growTime) {
            this.plantTime = plantTime;
            this.growTime = growTime;
        }

        @Override
        public int compareTo(Flower o) {
            return growTime - o.growTime;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Flower.class.getSimpleName() + "[", "]")
                    .add("growTime=" + growTime)
                    .toString();
        }
    }
}
