package data_structure.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/">373. 查找和最小的 K 对数字</a>
 * @author xuzhou
 * @since 2024/1/9 17:13
 */
public class KSmallestPairsTest {

    @Test
    public void test() {
        System.out.println(kSmallestPairs(new int[] {1, 2}, new int[] {3}, 2));
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        boolean baseOnNums1 = m <= n;
        List<List<Integer>> ans = new LinkedList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        if (baseOnNums1) {
            for (int i = 0; i < m; i++) {
                queue.offer(new Node(i, 0, nums1[i] + nums2[0]));
            }
        } else {
            for (int i = 0; i < n; i++) {
                queue.offer(new Node(0, i, nums1[0] + nums2[i]));
            }
        }
        while (k > 0 && !queue.isEmpty()) {
            k--;
            Node node = queue.poll();
            ans.add(Arrays.asList(nums1[node.i], nums2[node.j]));
            if (baseOnNums1 && node.j < n - 1) {
                node.j++;
                node.val = nums1[node.i] + nums2[node.j];
                queue.offer(node);
            } else if (!baseOnNums1 && node.i < m - 1) {
                node.i++;
                node.val = nums1[node.i] + nums2[node.j];
                queue.offer(node);
            }
        }
        return ans;
    }

    private class Node implements Comparable<Node> {
        int i;
        int j;
        int val;

        public Node(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return val - o.val;
        }
    }
}
