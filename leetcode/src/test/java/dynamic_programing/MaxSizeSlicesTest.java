package dynamic_programing;

import org.junit.Test;
import utils.RandomArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/pizza-with-3n-slices/">1388. 3n 块披萨</a>
 * 给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨：
 * <p>
 * 你挑选 任意 一块披萨。
 * Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。
 * Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。
 * 重复上述过程直到没有披萨剩下。
 * 每一块披萨的大小按顺时针方向由循环数组 slices 表示。
 * <p>
 * 请你返回你可以获得的披萨大小总和的最大值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：slices = [1,2,3,4,5,6]
 * 输出：10
 * 解释：选择大小为 4 的披萨，Alice 和 Bob 分别挑选大小为 3 和 5 的披萨。然后你选择大小为 6 的披萨，Alice 和 Bob 分别挑选大小为 2 和 1 的披萨。你获得的披萨总大小为 4 + 6 = 10 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：slices = [8,9,8,6,1,1]
 * 输出：16
 * 解释：两轮都选大小为 8 的披萨。如果你选择大小为 9 的披萨，你的朋友们就会选择大小为 8 的披萨，这种情况下你的总和不是最大的。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= slices.length <= 500
 * slices.length % 3 == 0
 * 1 <= slices[i] <= 1000
 *
 * @author xuzhou
 * @since 2023/8/21 17:55
 */
public class MaxSizeSlicesTest {

    @Test
    public void test() {
        System.out.println(maxSizeSlices(new int[]{8, 9, 8, 6, 1, 1}));
//        System.out.println(maxSizeSlices(new int[]{8, 1, 1, 6, 1, 9}));
//        System.out.println(maxSizeSlices(new int[]{6, 3, 1, 2, 6, 2, 4, 3, 10, 4, 1, 4, 6, 5, 5, 3, 4, 7, 6, 5, 8, 7, 3, 8, 8, 1, 7, 1, 7, 8}));
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generate(12, 1, 20);
            int res1 = maxSizeSlices1(nums);
            int res2 = maxSizeSlices(nums);
            if (res2 != res1) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                break;
            }
        }
    }


    public int maxSizeSlices(int[] slices) {
        // 动态规划
        int n = slices.length; // 总共n块
        return Math.max(process(slices, 0, n - 1), process(slices, 1, n));
    }

    private int process(int[] slices, int start, int end) {
        int n = slices.length; // 总共n块
        int m = n / 3; // 总共可以取m次
        int[] a0 = new int[m]; // 不包含前一个
        int[] a1 = new int[m]; // 包含前一个
        int max = 0;
        for (int i = start; i < end; i++) {
            int slice = slices[i];
            for (int j = 0; j < m; j++) {
                // 本次结束之后的结果肯定是前2个位置的最大值
                a1[j] = Math.max(a1[j], a0[j]);
            }
            int[] a2 = new int[m];
            for (int j = 0; j < m; j++) {
                // 对比不包含前一个的结果和加上本次的结果
                a2[j] = Math.max(slice + (j == 0 ? 0 : a0[j - 1]), a0[j]);
                if (a0[j] == 0) {
                    // 之前还没计算过，跳出
                    break;
                }
            }
            max = Math.max(max, a2[m - 1]);
            a0 = a1;
            a1 = a2;
        }
        return max;
    }

    public int maxSizeSlices1(int[] slices) {
        // 超出时间限制
        int m = slices.length / 3; // 总共可以取m次
        int[] selected = new int[m];
        return process(slices, selected, 0, 0);
    }

    private int process(int[] slices, int[] selected, int index, int preSum) {
        int n = slices.length; // 总共n块
        int m = selected.length; // 总共可以取m次
        if (index == m) {
            return preSum;
        }
        int max = 0;
        int start = index == 0 ? 0 : selected[index - 1] + 2;
        int end = index == 0 || selected[0] > 0 ? n : n - 1;
        end = end - ((m - index - 1) << 1);
        for (int i = start; i < end; i++) {
            selected[index] = i;
            max = Math.max(max, process(slices, selected, index + 1, preSum + slices[i]));
            selected[index] = 0;
        }
        return max;
    }

    public int maxSizeSlices2(int[] slices) {
        // 超出内存限制
        int n = slices.length; // 总共n块
        if (n == 3) {
            return max(slices[0], slices[1], slices[2]);
        }
        int m = n / 3; // 总共可以取m次
        int max = 0;
        // 数组长度为4，分别放置当前最左索引，当前最右索引，当前取的次数，当前累计
        List<int[]> current = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<int[]> next = new ArrayList<>(current);
            if (i <= (n >> 1)) {
                next.add(newState(i, i, 1, slices[i]));
            }
            for (int[] state : current) {
                if (i == n - 1 && state[0] != 0 || i < n - 1) if (i > state[1] + 1) {
                    if (state[2] == m - 1) {
                        max = Math.max(state[3] + slices[i], max);
                    } else {
                        next.add(newState(state[0], i, state[2] + 1, state[3] + slices[i]));
                    }
                }
            }
            current = next;
        }
        return max;
    }

    private int[] newState(int l, int r, int cnt, int sum) {
        return new int[]{l, r, cnt, sum};
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}
