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
        // todo 二分法查找，再插入
        int N = intervals.length;
        if (N == 0) {
            return new int[][]{newInterval};
        }
        int n = N << 1;
        int[] insertIndex0 = getInsertIndex(intervals, newInterval[0], 0, n);
        int[] insertIndex1 = getInsertIndex(intervals, newInterval[1], insertIndex0[0], n);
        System.out.println("insertIndex0 = " + Arrays.toString(insertIndex0));
        System.out.println("insertIndex1 = " + Arrays.toString(insertIndex1));
        int[][] res = new int[N + 1][];
//        int size = (insertIndex0 & 1) == 0 ? insertIndex0 >> 1 : (insertIndex0 >> 1) + 1;
//        System.arraycopy(intervals, 0, res, 0, size);

        return res;
    }

    private int[] merge(int[] a, int[] b) {
        return new int[] {Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }

    public int[] getInsertIndex(int[][] intervals, int num, int start, int end) {
        while (start < end) {
            int mid = (start + end) >> 1;
            int a = intervals[mid >> 1][mid & 1];
            if (a == num) {
                return new int[] {mid, 1};
            } else if (a > num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return new int[] {start, 0};
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int N = intervals.length;
        if (N == 0) {
            return new int[][]{newInterval};
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
                break;
            }
        }
        int[][] insert = insert(new int[][]{{1, 2}, {6, 9}}, new int[]{3, 5});
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
