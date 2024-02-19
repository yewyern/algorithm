package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/merge-intervals/">56. 合并区间</a>
 * @author xuzhou
 * @since 2024/2/19 16:25
 */
public class MergeIntervalsTest {

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n < 2) {
            return intervals;
        }
        // 对区间按照左边界升序排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int size = 0;
        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < n; i++) {
            int[] currentInterval = intervals[i];
            // 如果当前区间的左边界大于上一区间的右边界，则代表它们不重叠，将上一区间添加到结果中
            if (currentInterval[0] > end) {
                intervals[size++] = new int[]{start, end};
                start = currentInterval[0];
                end = currentInterval[1];
            } else {
                // 否则，它们重叠，更新右边界为两个区间右边界中的较大值
                end = Math.max(end, currentInterval[1]);
            }
        }
        // 不要忘记将最后一个区间添加到结果中
        intervals[size++] = new int[]{start, end};
        return Arrays.copyOfRange(intervals, 0, size);
    }
}
