package data_structure.heap;

import java.util.Comparator;

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
public class MedianFinder2 {

    private static final Heap minHeap = new Heap(25000, Comparator.naturalOrder()); // 小顶堆，存放上半部分
    private static final Heap maxHeap = new Heap(25000, Comparator.reverseOrder()); // 大顶堆，存放下半部分


    /**
     * initialize your data structure here.
     */
    public MedianFinder2() {
        minHeap.size = 0;
        maxHeap.size = 0;
    }

    public void addNum(int num) {
        // 1、如果堆都是空的，先往大顶堆放
        if (maxHeap.size == 0) {
            maxHeap.add(num);
            return;
        }
        // 2、如果数大于大顶堆的堆顶，先往小顶堆放，反之，放到大顶堆中
        if (num > maxHeap.peek()) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }
        // 3、平衡2个堆的长度，即2个堆长度不能超过1
        if (maxHeap.size - minHeap.size > 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size - maxHeap.size > 1) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        return minHeap.size == maxHeap.size ? (maxHeap.peek() + minHeap.peek()) / 2.0 : (maxHeap.size > minHeap.size ? maxHeap.peek() : minHeap.peek());
    }

    private static class Heap {
        private final int[] values;
        private final Comparator<Integer> comparator;
        private int size = 0;

        public Heap(int capacity, Comparator<Integer> comparator) {
            this.values = new int[capacity];
            this.comparator = comparator;
        }

        public void add(int a) {
            int i = size++;
            values[i] = a;
            while (i > 0) {
                int pi = (i - 1) >> 1;
                int max = max(pi, i);
                if (max == pi) {
                    break;
                }
                swap(i, pi);
                i = pi;
            }
        }

        public int peek() {
            return values[0];
        }

        public int poll() {
            int res = values[0];
            size--;
            values[0] = values[size];
            int i = 0;
            while (i < size) {
                int lc = (i << 1) + 1;
                int rc = (i << 1) + 2;
                lc = lc < size ? lc : i;
                rc = rc < size ? rc : i;
                int max = max(i, lc, rc);
                if (max == i) {
                    break;
                }
                swap(i, max);
                i = max;
            }
            return res;
        }

        private void swap(int i, int j) {
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }


        private int max(int i, int lc, int rc) {
            return max(i, max(lc, rc));
        }

        private int max(int i, int j) {
            return comparator.compare(values[i], values[j]) > 0 ? j : i;
        }

    }

    public static void main(String[] args) {
        MedianFinder2 medianFinder2 = new MedianFinder2();
        medianFinder2.addNum(1);
        medianFinder2.addNum(2);
        System.out.println(medianFinder2.findMedian());
        medianFinder2.addNum(3);
        System.out.println(medianFinder2.findMedian());
    }
}