package greedy_algorithm;

import org.junit.Test;
import utils.RandomArray;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/pizza-with-3n-slices/">1388. 3n 块披萨</a>
 * 给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨：
 * <p>
 * 你挑选 任意 一块披萨。
 * Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。
 * Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。
 * 重复上述过程直到没有披萨剩下。
 * 每一块披萨的大小按顺时针方向由循环数组 slices 表示。
 * <p>
 * 请你返回你可以获得的披萨大小总和的最大值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：slices = [1,2,3,4,5,6]
 * 输出：10
 * 解释：选择大小为 4 的披萨，Alice 和 Bob 分别挑选大小为 3 和 5 的披萨。然后你选择大小为 6 的披萨，Alice 和 Bob 分别挑选大小为 2 和 1 的披萨。你获得的披萨总大小为 4 + 6 = 10 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：slices = [8,9,8,6,1,1]
 * 输出：16
 * 解释：两轮都选大小为 8 的披萨。如果你选择大小为 9 的披萨，你的朋友们就会选择大小为 8 的披萨，这种情况下你的总和不是最大的。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= slices.length <= 500
 * slices.length % 3 == 0
 * 1 <= slices[i] <= 1000
 *
 * @author xuzhou
 * @since 2023/8/21 17:55
 */
public class MaxSizeSlicesTest {

    @Test
    public void test() {
        System.out.println(maxSizeSlices(new int[]{15, 11, 14, 8, 13, 12, 15, 15, 1}));
        System.out.println(maxSizeSlices(new int[]{8, 9, 8, 6, 1, 1}));
        System.out.println(maxSizeSlices(new int[]{8, 1, 1, 6, 1, 9}));
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generate(9, 1, 20);
            int res1 = maxSizeSlices2(nums);
            int res2 = maxSizeSlices(nums);
            if (res2 != res1) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                break;
            }
        }
    }

    public int maxSizeSlices(int[] slices) {
        // 贪心算法：
        // 如果三个数中间的数最大，正常情况下选中间数，如果不选中间数，一定是左右2个数都会选
        // 不存在左右2个数小于中间数，还不选的可能
        // 先对索引进行值排序，然后按大到小取
        // 参考leetcode题解写的反悔贪心
        // 核心在于一个值选择后，用左右2个的值-本身的值替换自身，保持了3个数2种可能，即选中间的最大数，或旁边的2个数，
        // 同时要保证如果确实反悔取到左右2个数的时候，要保证左右2个数的相邻的数不能被选
        // java自带的PriorityQueue，无法在值变更的时候，重新heapify，自己写个堆实现
        int n = slices.length;
        int m = n / 3;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            node.val = slices[i];
            node.heapIndex = i;
            if (i > 0) {
                node.left = nodes[i - 1];
                nodes[i - 1].right = node;
            }
            nodes[i] = node;
        }
        nodes[0].left = nodes[n - 1];
        nodes[n - 1].right = nodes[0];
        Heap heap = new Heap(nodes);
        int max = 0;
        for (int i = 0; i < m; i++) {
            Node cur = heap.peek();
            max += cur.val;
            Node left = cur.left;
            Node right = cur.right;
            cur.val = left.val + right.val - cur.val;
            if (cur.val > 0) {
                heap.siftDown(cur.heapIndex);
            } else {
                heap.remove(cur.heapIndex);
            }
            cur.left = left.left;
            cur.left.right = cur;
            cur.right = right.right;
            cur.right.left = cur;
            left.val = 0;
            heap.remove(left.heapIndex);
            right.val = 0;
            heap.remove(right.heapIndex);
        }
        return max;
    }

    private static class Heap {
        final Node[] nodes;
        int size;

        public Heap(Node[] nodes) {
            this.nodes = nodes;
            this.size = nodes.length;
            heapify();
        }

        Node peek() {
            return nodes[0];
        }

        void remove(int index) {
            size--;
            if (size == index) {
                return;
            }
            Node swap = nodes[size];
            swap(index, size);
            siftDown(index);
            if (swap.heapIndex == index) {
                siftUp(index);
            }
        }

        void heapify() {
            for (int i = (size >> 1) - 1; i >= 0; i--) {
                siftDown(i);
            }
        }

        void siftUp(int index) {
            while (index > 0) {
                int p = (index - 1) >> 1;
                if (nodes[p].val < nodes[index].val) {
                    swap(index, p);
                    index = p;
                } else {
                    break;
                }
            }
        }

        void siftDown(int index) {
            while (index < size) {
                int l = (index << 1) + 1;
                int r = (index << 1) + 2;
                int max = max(index, l, r);
                if (max == index) {
                    return;
                }
                swap(index, max);
                index = max;
            }
        }

        int max(int i, int j, int k) {
            return max(i, max(j, k));
        }

        int max(int i, int j) {
            if (i < size && j < size) {
                return nodes[i].val >= nodes[j].val ? i : j;
            }
            return i < size ? i : j;
        }

        void swap(int i, int j) {
            Node a = nodes[i];
            Node b = nodes[j];
            a.heapIndex = j;
            b.heapIndex = i;
            nodes[i] = b;
            nodes[j] = a;
        }
    }


    public int maxSizeSlices2(int[] slices) {
        // 贪心算法：
        // 如果三个数中间的数最大，正常情况下选中间数，如果不选中间数，一定是左右2个数都会选
        // 不存在左右2个数小于中间数，还不选的可能
        // 先对索引进行值排序，然后按大到小取
        // 参考leetcode题解写的反悔贪心
        // 核心在于一个值选择后，用左右2个的值-本身的值替换自身，保持了3个数2种可能，即选中间的最大数，或旁边的2个数，
        // 同时要保证如果确实反悔取到左右2个数的时候，要保证左右2个数的相邻的数不能被选
        int n = slices.length;
        int m = n / 3;
        List<Node> nodes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            node.val = slices[i];
            if (i > 0) {
                node.left = nodes.get(i - 1);
                nodes.get(i - 1).right = node;
            }
            nodes.add(node);
        }
        nodes.get(0).left = nodes.get(n - 1);
        nodes.get(n - 1).right = nodes.get(0);
        PriorityQueue<Node> queue = new PriorityQueue<>(nodes);
        int max = 0;
        for (int i = 0; i < m; i++) {
            Node cur = queue.poll();
            max += cur.val;
            Node left = cur.left;
            Node right = cur.right;
            cur.val = left.val + right.val - cur.val;
            queue.remove(left);//这个是o(n)的，所以最终是o(n^2)
            queue.remove(right);
            cur.left = left.left;
            cur.left.right = cur;
            cur.right = right.right;
            cur.right.left = cur;
            if (cur.val > 0) {
                queue.offer(cur);
            }
        }
        return max;
    }

    private static class Node implements Comparable<Node> {
        int val;
        int heapIndex;
        Node left;
        Node right;

        @Override
        public int compareTo(Node other) {
            return other.val - val;
        }

    }


    public int maxSizeSlices3(int[] slices) {
        // 贪心算法：
        // 如果三个数中间的数最大，正常情况下选中间数，如果不选中间数，一定是左右2个数都会选
        // 不存在左右2个数小于中间数，还不选的可能
        // 先对索引进行值排序，然后按大到小取
        int n = slices.length;
        int m = n / 3; // 总共可以取m次
        boolean[] selected = new boolean[n];
        int[] sortedIndexes = mergeSort(slices);
        // fixme 答案是对的，但是不是贪心了，变成回溯了
        return getMaxSelect(slices, sortedIndexes, selected, 0, 0, m);
    }

    private int getMaxSelect(int[] slices, int[] sortedIndexes, boolean[] selected, int index, int preSum, int count) {
        if (count == 0) {
            return preSum;
        }
        int n = slices.length;
        while (index < n && besideSelected(selected, sortedIndexes[index], n)) {
            index++;
        }
        if (index == n) {
            return 0;
        }
        selected[sortedIndexes[index]] = true;
        int max = getMaxSelect(slices, sortedIndexes, selected, index + 1, preSum + slices[sortedIndexes[index]], count - 1);
        selected[sortedIndexes[index]] = false;
        if (index < n - 1) {
            max = Math.max(max, getMaxSelect(slices, sortedIndexes, selected, index + 1, preSum, count));
        }
        if (index < n - 2) {
            max = Math.max(max, getMaxSelect(slices, sortedIndexes, selected, index + 2, preSum, count));
        }
        return max;
    }

    private boolean besideSelected(boolean[] selected, int i, int n) {
        if (i == 0) {
            return selected[i + 1] || selected[n - 1];
        }
        if (i == n - 1) {
            return selected[0] || selected[i - 1];
        }
        return selected[i + 1] || selected[i - 1];
    }

    private int[] mergeSort(int[] slices) {
        int n = slices.length;
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        for (int step = 1; step < n; step <<= 1) {
            int l = 0, m = step;
            while (m < n) {
                merge(slices, indexes, l, m, m + step);
                l = m + step;
                m = l + step;
            }
        }
        return indexes;
    }

    private void merge(int[] slices, int[] indexes, int l, int m, int r) {
        r = Math.min(r, slices.length);
        int n = r - l;
        int[] temp = new int[n];
        int p0 = l, p1 = m;
        for (int i = 0; i < n; i++) {
            if (p0 < m && p1 < r) {
                temp[i] = slices[indexes[p0]] >= slices[indexes[p1]] ? indexes[p0++] : indexes[p1++];
            } else if (p0 < m) {
                temp[i] = indexes[p0++];
            } else {
                temp[i] = indexes[p1++];
            }
        }
        System.arraycopy(temp, 0, indexes, l, n);
    }

}
