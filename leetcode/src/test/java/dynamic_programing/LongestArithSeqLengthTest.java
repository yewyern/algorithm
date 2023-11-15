package dynamic_programing;

import org.junit.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/longest-arithmetic-subsequence">1027. 最长等差数列</a>
 * @author xuzhou
 * @since 2023/9/14 14:10
 */
public class LongestArithSeqLengthTest {

    @Test
    public void test() {
        System.out.println(longestArithSeqLength(new int[] {3, 6}));
    }
    public int longestArithSeqLength(int[] a) {
        // 大神题解
        int ans = 0, n = a.length;
        int[][] f = new int[n][1001];
        for (int i = 1; i < n; ++i)
            for (int j = i - 1; j >= 0; --j) {
                int d = a[i] - a[j] + 500; // +500 防止出现负数
                if (f[i][d] == 0) {
                    f[i][d] = f[j][d] + 1; // 默认的 1 在下面返回时加上
                    ans = Math.max(ans, f[i][d]);
                }
            }
        return ans + 1;
    }

    public int longestArithSeqLength0(int[] nums) {
        // 官方题解
        // 先找最大值最小值，确定最大差值
        int max = -1, min = 501;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int diff = max - min; // 差值最大绝对值
        int ans = 1;
        for (int d = -diff; d <= diff; d++) {
            int[] f = new int[max + 1]; // 上一个数找到的数列长度
            for (int num : nums) {
                int pre = num - d; // 差值为d的情况下上一个数是啥
                if (pre >= min && pre <= max && f[pre] > 0) {
                    f[num] = Math.max(f[num], f[pre] + 1);
                    ans = Math.max(ans, f[num]);
                }
                f[num] = Math.max(f[num], 1);
            }
        }
        return ans;
    }

    public int longestArithSeqLength1(int[] nums) {
        int n = nums.length;
        int[][] map = new int[500][]; // map[差值+250][上一个索引] = 当前长度
        int max = 2;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j] + 250; // 差值
                if (diff < 0 || diff >= 500) {
                    continue;
                }
                if (map[diff] == null) {
                    map[diff] = new int[n];
                    Arrays.fill(map[diff], 1);
                }
                map[diff][i] = map[diff][j] + 1;
                max = Math.max(max, map[diff][i]);
            }
        }
        return max;
    }

    public int longestArithSeqLength2(int[] nums) {
        Map<Integer/*差值*/, Map<Integer/*上一个索引*/, Integer/*当前长度*/>> map = new HashMap<>();
        int max = 2;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j]; // 差值
                Map<Integer/*上一个索引*/, Integer/*当前长度*/> countMap = map.getOrDefault(diff, new HashMap<>());
                int count = countMap.getOrDefault(j, 1) + 1;
                countMap.put(i, count);
                map.put(diff, countMap);
                max = Math.max(max, count);
            }
        }
        return max;
    }
}
