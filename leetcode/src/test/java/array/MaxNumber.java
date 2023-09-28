package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/create-maximum-number">321. 拼接最大数</a>
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * <p>
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * <p>
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 示例 2:
 * <p>
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 示例 3:
 * <p>
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 *
 * @author : zhou.xu
 * @since : 2020/12/2 10:06
 */
public class MaxNumber {

    static class Node implements Comparable<Node> {

        final int value;
        final int array;
        final int index;

        public Node(int value, int array, int index) {
            this.value = value;
            this.array = array;
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(Node o) {
            if (array == o.array) {
                return index - o.index;
            }
            return o.value - this.value;
        }

        @Override
        public String toString() {
            return "Node{" + "value=" + value + ", array=" + array + ", index=" + index + '}';
        }
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        Node[] nodes = new Node[m + n];
        int p = 0;
        for (int i = 0; i < nums1.length; i++) {
            nodes[p++] = new Node(nums1[i], 0, i);
        }
        for (int i = 0; i < nums2.length; i++) {
            nodes[p++] = new Node(nums2[i], 1, i);
        }
        Arrays.sort(nodes);
        System.out.println("nodes = " + Arrays.toString(nodes));
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = nodes[i].value;
        }
        return ans;
    }

    public void test(int[] nums1, int[] nums2, int k, int[] expected) {
        System.out.println("nums1 = " + Arrays.toString(nums1));
        System.out.println("nums2 = " + Arrays.toString(nums2));
        System.out.println("k = " + k);
        int[] maxNumber = maxNumber(nums1, nums2, k);
        System.out.println("maxNumber = " + Arrays.toString(maxNumber));
        System.out.println("expected = " + Arrays.toString(expected));
        System.out.println("----------------------------------");
    }

    @Test
    public void test() {
        test(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5, new int[]{9, 8, 6, 5, 3});
        test(new int[]{6, 7}, new int[]{6, 0, 4}, 5, new int[]{6, 7, 6, 0, 4});
        test(new int[]{3, 9}, new int[]{8, 9}, 3, new int[]{9, 8, 9});
    }
}