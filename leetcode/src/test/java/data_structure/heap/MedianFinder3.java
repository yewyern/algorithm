package data_structure.heap;

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
public class MedianFinder3 {

    private static final int[] minHeap = new int[25000]; // 小顶堆，存放上半部分
    private static final int[] maxHeap = new int[25000]; // 大顶堆，存放下半部分

    private static int maxSize = 0;
    private static int minSize = 0;
    private static boolean even = true; // 是否是偶数长度

    /**
     * 84ms解法
     * initialize your data structure here.
     */
    public MedianFinder3() {
        maxSize = 0;
        minSize = 0;
        even = true;
    }

    public void addNum(int num) {
        even = !even;
        // 1、如果堆都是空的，往大顶堆放
        if (maxSize == 0) {
            maxHeap[maxSize++] = num;
            return;
        }
        // 2、如果2个堆长度一样，需要往大顶堆放 （小顶堆最小值和num）中的最小值
        if (maxSize == minSize) {
            add(maxHeap, maxSize, maxSize++, Math.min(num, minHeap[0]), false);
            if (num > minHeap[0]) {
                add(minHeap, minSize, 0, num, true);
            }
            return;
        }
        // 3、长度不一样，往小顶堆放（大顶堆最大值和num）中的最大值
        add(minHeap, minSize, minSize++, Math.max(num, maxHeap[0]), true);
        if (maxHeap[0] > num) {
            add(maxHeap, maxSize, 0, num, false);
        }
    }

    public double findMedian() {
        return even ? (maxHeap[0] + minHeap[0]) / 2.0 : maxHeap[0];
    }

    private void add(int[] arr, int size, int i, int num, boolean asc) {
        if (i == 0) {
            arr[0] = num;
            siftDown(arr, size, asc);
        } else {
            arr[size] = num;
            siftUp(arr, size, asc);
        }
    }

    private void siftUp(int[] arr, int i, boolean asc) {
        // 将i位置的数向上升，使得arr成为一个堆
        while (i > 0) {
            int pi = (i - 1) >> 1;
            int siftIndex = siftIndex(arr, i, pi, asc);
            if (siftIndex != i) {
                break;
            }
            swap(arr, pi, i);
            i = pi;
        }
    }

    private void siftDown(int[] arr, int size, boolean asc) {
        // 将0位置的数向下沉，使arr成为一个堆
        int p = 0;
        while (p < size) {
            int pl = (p << 1) + 1;
            int pr = (p << 1) + 2;
            int siftIndex = siftIndex(arr, p, pl, pr, size, asc);
            if (siftIndex == p) {
                break;
            }
            swap(arr, siftIndex, p);
            p = siftIndex;
        }
    }

    private int siftIndex(int[] arr, int i, int j, int k, int size, boolean asc) {
        if (j < size && k < size) {
            return siftIndex(arr, i, j, k, asc);
        }
        if (j < size) {
            return siftIndex(arr, i, j, asc);
        }
        return k < size ? siftIndex(arr, i, k, asc) : i;
    }

    private int siftIndex(int[] arr, int i, int j, int k, boolean asc) {
        return siftIndex(arr, i, siftIndex(arr, j, k, asc), asc);
    }

    private int siftIndex(int[] arr, int i, int j, boolean asc) {
        return asc ? (arr[i] < arr[j] ? i : j) : (arr[i] > arr[j] ? i : j);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        MedianFinder3 medianFinder = new MedianFinder3();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
//        for (int i = 100; i > 0; i--) {
//            medianFinder.addNum(i);
//            System.out.println(medianFinder.findMedian());
//        }
    }
}