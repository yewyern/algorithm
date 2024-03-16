package data_structure.heap;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof">剑指 Offer 41. 数据流中的中位数</a>
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例 1：
 * <p>
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * 示例 2：
 * <p>
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 * 注意：本题与主站 295 题相同：<a href="https://leetcode-cn.com/problems/find-median-from-data-stream/">295. 数据流的中位数</a>
 */
public class MedianFinder {

    private static final int[] nums = new int[50000];
    private int size;

    /**
     * 300ms解法
     * initialize your data structure here.
     */
    public MedianFinder() {
        size = 0;
    }

    public void addNum(int num) {
        // 插入排序
        int insertIndex = getInsertIndex(num);
        System.arraycopy(nums, insertIndex, nums, insertIndex + 1, size - insertIndex);
        nums[insertIndex] = num;
        size++;
    }

    private int getInsertIndex(int num) {
        // 优化：二分查找插入点，62ms
        int l = 0, r = size;
        while (l < r) {
            int m = (l + r) >> 1;
            if (nums[m] < num) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    private int getInsertIndex2(int num) {
        // 普通遍历查找插入点，233ms
        int insertIndex = 0;
        while (insertIndex < size && nums[insertIndex] <= num) {
            insertIndex++;
        }
        return insertIndex;
    }

    public double findMedian() {
        int mid = size >> 1;
        return size % 2 == 1 ? nums[mid] : (nums[mid] + nums[mid - 1]) / 2.0;
    }
}