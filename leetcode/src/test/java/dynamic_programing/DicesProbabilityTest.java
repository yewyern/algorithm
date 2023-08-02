package dynamic_programing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 60. n个骰子的点数</a>
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 * <p>
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 11
 *
 * @author xuzhou
 * @since 2023/8/1 17:48
 */
public class DicesProbabilityTest {

    private static final double[] single = {1d / 6, 1d / 6, 1d / 6, 1d / 6, 1d / 6, 1d / 6};
    private static final List<double[]> list = new ArrayList<>(11);

    static {
        list.add(single);
    }

    public double[] dicesProbability(int n) {
        int N = list.size();
        if (n <= N) {
            return list.get(n - 1);
        }
        for (int i = N; i < n; i++) {
            // last表示上次摇骰子点数的概率
            double[] last = list.get(i - 1);
            int len = 5 * i + 6;
            double[] curr = new double[len];
            int mid = (len + 1) >> 1;
            for (int k = 0; k < mid; k++) {
                double p = 0;
                // 这次要加上一个骰子，计算本次的概率
                for (int j = 0; j < 6 && j <= k; j++) {
                    // 本次可以加上的点数j，概率是single[j]
                    p += single[j] * last[k - j];
                }
                curr[k] = p;
                curr[len - k - 1] = p;
            }
            list.add(curr);
        }
        return list.get(n - 1);
    }

    @Test
    public void test() {
        dicesProbability(2);
    }
}
