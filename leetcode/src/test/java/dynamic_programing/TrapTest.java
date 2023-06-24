package dynamic_programing;

import org.junit.Test;
import utils.RandomArray;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <p><a href="https://leetcode.cn/problems/trapping-rain-water/solutions/692342/jie-yu-shui-by-leetcode-solution-tuvc/">42. 接雨水</a>
 * <p>给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>示例 1：
 * <p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png"/>
 * <p>输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * <p>输出：6
 * <p>解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>示例 2：
 * <p>
 * <p>输入：height = [4,2,0,3,2,5]
 * <p>输出：9
 * 提示：
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 *
 * @author zhou.xu
 * @since 2020/10/27 17:59
 */
public class TrapTest {

    public int trap(int[] height) {
        // 单调队列
        int ans = 0;
        int N = height.length;
        int[] sum = new int[N];
        LinkedList<Integer> maxQueue = new LinkedList<>();
        int pre = 0;
        for (int i = 0; i < N; i++) {
            pre += height[i];
            sum[i] = pre;
            int lastMaxIndex = -1;
            while (!maxQueue.isEmpty() && height[maxQueue.peekLast()] <= height[i]) {
                lastMaxIndex = maxQueue.pollLast();
            }
            if (maxQueue.isEmpty() && lastMaxIndex >= 0) {
                ans += height[lastMaxIndex] * (i - lastMaxIndex - 1) - sum[i - 1] + sum[lastMaxIndex];
            }
            maxQueue.addLast(i);
        }
        int lastMaxIndex = maxQueue.isEmpty() ? -1 : maxQueue.pollFirst();
        while (!maxQueue.isEmpty()) {
            int currMaxIndex = maxQueue.pollFirst();
            ans += height[currMaxIndex] * (currMaxIndex - lastMaxIndex - 1) - sum[currMaxIndex - 1] + sum[lastMaxIndex];
            lastMaxIndex = currMaxIndex;
        }
        return ans;
    }

    public int trapComparison(int[] height) {
        // 暴力方法
        int ans = 0;
        int N = height.length;
        int l = 0;
        while (l < N) {
            // 先找到右边比height[l] 大的数，只有比它大的，才可能存住雨水
            if (height[l] == 0) {
                // 开头位置不能是0
                l++;
                continue;
            }
            int r = l + 1;
            while (r < N && height[r] < height[l]) {
                r++;
            }
            if (r < N) {
                for (int i = l + 1; i < r; i++) {
                    ans += height[l] - height[i];
                }
                l = r;
            } else {
                break;
            }
        }
        if (l < N) {
            // height[l] 是最高点，计算l右边能装多少
            for (int r = N - 1; r >= l; ) {
                if (height[r] == 0) {
                    r--;
                    continue;
                }
                int t = r - 1;
                while (t >= l && height[t] < height[r]) {
                    t--;
                }
                if (t >= l) {
                    for (int i = t + 1; i < r; i++) {
                        ans += height[r] - height[i];
                    }
                    r = t;
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int maxLen = 20000;
        int max = 100000;
        int tests = 100000;
        for (int i = 0; i < tests; i++) {
            int[] height = RandomArray.generateRandomLengthNoEmptyArray(maxLen, max);
            int trap = trap(height);
            int trappedComparison = trapComparison(height);
            if (trap != trappedComparison) {
                System.out.println("height = " + Arrays.toString(height));
                System.out.println("trap = " + trap);
                System.out.println("trappedComparison = " + trappedComparison);
                break;
            }
        }
    }

    @Test
    public void test2() {
        test(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1);
        test(4, 2, 0, 3, 2, 5);
    }

    public void test(int... height) {
        System.out.println("height = " + Arrays.toString(height));
        int trap = trap(height);
        System.out.println("trap = " + trap);
    }
}
