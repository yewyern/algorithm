package data_structure.array;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/insert-interval/">57. 插入区间</a>
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 * <p>
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 * <p>
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 * <p>
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 10^5
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 10^5
 *
 * @author xuzhou
 * @since 2023/8/29 10:21
 */
public class InsertIntervalTest {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 二分法查找，再插入
        int N = intervals.length;
        if (N == 0) {
            return new int[][]{newInterval};
        }
        if (newInterval[0] > intervals[N - 1][1]) {
            return insert(intervals, newInterval, N);
        }
        if (newInterval[1] < intervals[0][0]) {
            return insert(intervals, newInterval, 0);
        }
        int insertStartIndex = getInsertIndex(intervals, newInterval[0], 0, N, 1);// 二分法查找开始插入位置（该位置如果和插入位置的区间重叠，则与该位置合并）
        int insertEndIndex = getInsertIndex(intervals, newInterval[1], insertStartIndex, N, 0); // 二分法查找结束插入位置（该位置如果和插入位置的区间重叠，则与该位置合并）
        if (insertStartIndex == insertEndIndex) {
            // 区间重叠
            if (intervals[insertEndIndex][0] == newInterval[1]) {
                intervals[insertEndIndex][0] = newInterval[0];
                return intervals;
            }
            return insert(intervals, newInterval, insertStartIndex);
        }
        if (insertEndIndex == N) {
            int[][] res = new int[insertStartIndex + 1][2];
            System.arraycopy(intervals, 0, res, 0, insertStartIndex);
            res[insertStartIndex][0] = Math.min(intervals[insertStartIndex][0], newInterval[0]);
            res[insertStartIndex][1] = Math.max(intervals[insertEndIndex - 1][1], newInterval[1]);
            return res;
        }
        if (intervals[insertEndIndex][0] == newInterval[1]) {
            int[][] res = new int[N - insertEndIndex + insertStartIndex][2];
            System.arraycopy(intervals, 0, res, 0, insertStartIndex);
            res[insertStartIndex][0] = Math.min(intervals[insertStartIndex][0], newInterval[0]);
            res[insertStartIndex][1] = intervals[insertEndIndex][1];
            System.arraycopy(intervals, insertEndIndex + 1, res, insertStartIndex + 1, N - insertEndIndex - 1);
            return res;
        }
        int[][] res = new int[N - insertEndIndex + insertStartIndex + 1][2];
        System.arraycopy(intervals, 0, res, 0, insertStartIndex + 1);
        res[insertStartIndex][0] = Math.min(intervals[insertStartIndex][0], newInterval[0]);
        res[insertStartIndex][1] = Math.max(intervals[insertEndIndex - 1][1], newInterval[1]);
        System.arraycopy(intervals, insertEndIndex, res, insertStartIndex + 1, N - insertEndIndex);
        return res;
    }

    private int[][] insert(int[][] intervals, int[] newInterval, int i) {
        int N = intervals.length;
        if (N == 0) {
            return new int[][]{newInterval};
        }
        int[][] res = new int[N + 1][];
        System.arraycopy(intervals, 0, res, 0, i);
        res[i] = newInterval;
        if (i < N) {
            System.arraycopy(intervals, i, res, i + 1, N - i);
        }
        return res;
    }

    public int getInsertIndex(int[][] intervals, int num, int l, int r, int i) {
        while (l < r) {
            int m = (l + r) >> 1;
            int a = intervals[m][i];
            if (a == num) {
                return m;
            } else if (a > num) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int N = intervals.length;
        if (N == 0) {
            return new int[][]{newInterval};
        }
        if (newInterval[0] > intervals[N - 1][1]) {
            return insert(intervals, newInterval, N);
        }
        if (newInterval[1] < intervals[0][0]) {
            return insert(intervals, newInterval, 0);
        }
        int[][] res = new int[N + 1][];
        int size = 0, cur = 0;
        while (cur < N && intervals[cur][1] < newInterval[0]) {
            res[size++] = intervals[cur++];
        }
        res[size++] = newInterval;
        while (cur < N && intervals[cur][0] <= res[size - 1][1]) {
            res[size - 1][0] = Math.min(intervals[cur][0], res[size - 1][0]);
            res[size - 1][1] = Math.max(intervals[cur++][1], res[size - 1][1]);
        }
        while (cur < N) {
            res[size++] = intervals[cur++];
        }
        if (size == N + 1) {
            return res;
        }
        return Arrays.copyOfRange(res, 0, size);
    }


    @Test
    public void test() {
//        int[][] insert = insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
        for (int i = 0; i < 100000; i++) {
            int n = RandomUtils.nextInt(20);
            int[] array = RandomArray.generateNoRepeatSortedArray(n << 1, 0, 1000);
            int[][] intervals = toIntervals(array);
            int[][] intervals2 = toIntervals(array);
            int[] newInterval = RandomArray.generateNoRepeatSortedArray(2, 0, 1000);
            int[][] res1 = insert(intervals, newInterval);
            int[][] res2 = insert2(intervals2, newInterval);
            if (!isSame(res1, res2)) {
                System.out.println("intervals = " + Arrays.deepToString(intervals) + ", newInterval = " + Arrays.toString(newInterval));
                System.out.println("res1 = " + Arrays.deepToString(res1));
                System.out.println("res2 = " + Arrays.deepToString(res2));
                int[][] res3 = insert(intervals, newInterval);
                int[][] res4 = insert2(intervals2, newInterval);
                System.out.println("res3 = " + Arrays.deepToString(res3));
                System.out.println("res4 = " + Arrays.deepToString(res4));
                break;
            }
        }
        int[][] insert = insert(new int[][]{{297, 510}}, new int[]{113, 510});
        System.out.println("insert = " + Arrays.deepToString(insert));
    }

    private int[][] toIntervals(int[] arr) {
        int n = arr.length;
        int[][] res = new int[n >> 1][];
        for (int i = 0, j = 0; i < n; i += 2, j++) {
            res[j] = new int[] {arr[i], arr[i + 1]};
        }
        return res;
    }

    private boolean isSame(int[][] intervals1, int[][] intervals2) {
        if (intervals1.length != intervals2.length) {
            return false;
        }
        for (int i = 0; i < intervals1.length; i++) {
            if (intervals1[i] == null || intervals2[i] == null) {
                return false;
            }
            if (intervals1[i].length != intervals2[i].length) {
                return false;
            }
            for (int j = 0; j < intervals1[i].length; j++) {
                if (intervals1[i][j] != intervals2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
