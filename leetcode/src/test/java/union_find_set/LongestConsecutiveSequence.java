package union_find_set;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/longest-consecutive-sequence">128. 最长连续序列</a>
 * @author xuzhou
 * @since 2023/12/22 10:24
 */
public class LongestConsecutiveSequence {

    private Map<Integer, Integer> indexes;
    private int[] parents;
    private int[] size;
    private int maxSize;

    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        indexes = new HashMap<>(n);
        parents = new int[n];
        size = new int[n];
        maxSize = 1;
        for (int i = 0; i < n; i++) {
            indexes.put(nums[i], i);
            parents[i] = i;
            size[i] = 1;
        }
        for (int num : nums) {
            union(num, num - 1);
            union(num, num + 1);
        }
        return maxSize;
    }

    private int getParent(int i) {
        if (parents[i] != i) {
            parents[i] = getParent(parents[i]);
        }
        return parents[i];
    }

    private void union(int a, int b) {
        Integer i = indexes.get(a);
        Integer j = indexes.get(b);
        if (j == null) {
            return;
        }
        int p1 = getParent(i);
        int p2 = getParent(j);
        if (p1 != p2) {
            if (size[p1] >= size[p2]) {
                parents[p2] = p1;
                size[p1] += size[p2];
                maxSize = Math.max(maxSize, size[p1]);
            } else {
                parents[p1] = p2;
                size[p2] += size[p1];
                maxSize = Math.max(maxSize, size[p2]);
            }
        }
    }

    public int longestConsecutive2(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        Arrays.sort(nums);
        int maxSize = 1;
        int size = 1;
        int last = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == last) {
                continue;
            }
            if (nums[i] == last + 1) {
                size++;
                maxSize = Math.max(maxSize, size);
            } else {
                size = 1;
            }
            last = nums[i];
        }
        return maxSize;
    }

}
