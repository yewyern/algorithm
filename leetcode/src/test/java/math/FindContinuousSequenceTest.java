package math;

import org.junit.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 57 - II. 和为s的连续正数序列</a>
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= target <= 10^5
 *
 * @author xuzhou
 * @since 2023/8/8 15:27
 */
public class FindContinuousSequenceTest {

    public int[][] findContinuousSequence(int target) {
        // 长度范围[2,n]，n的取值是当起始为1时，序列和最接近target
        // 当长度确定时，很容易得到序列的内容，如：15，长度为2，必然是[7,8]
        // 对于奇数长度，中点位置刚好是target / len
        // 对于偶数长度，中点位置是target / len，且小数点后必须是0.5
        int maxLen = calcMaxLen(target);
        int[][] res = new int[maxLen][];
        int size = 0;
        for (int len = maxLen; len > 1; len--) {
            if ((len & 1) == 0 && target % len == len >> 1) {
                // 偶数长度
                int start = target / len - (len >> 1) + 1;// 起始值
                res[size++] = newArray(start, len);
            }
            if ((len & 1) == 1 && target % len == 0) {
                // 奇数长度
                int start = target / len - (len >> 1);// 起始值
                res[size++] = newArray(start, len);
            }
        }
        return Arrays.copyOf(res, size);
    }

    private int calcMaxLen(int N) {
        double sqrt = Math.sqrt((N << 3) + 1);
        return ((int) sqrt - 1) >> 1;
    }

    private int[] newArray(int start, int len) {
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = start++;
        }
        return res;
    }

    @Test
    public void test() {
        int[][] continuousSequence = findContinuousSequence(98160);
        System.out.println(Arrays.deepToString(continuousSequence));
    }
}
