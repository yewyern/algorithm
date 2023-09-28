package dynamic_programing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/longest-arithmetic-subsequence">1027. 最长等差数列</a>
 * @author xuzhou
 * @since 2023/9/14 14:10
 */
public class LongestArithSeqLengthTest {

    public int longestArithSeqLength(int[] nums) {
        // todo 时间复杂度待优化
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
