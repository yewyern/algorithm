package data_structure.array;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array">421. 数组中两个数的最大异或值</a>
 *
 * @author xuzhou
 * @since 2023/11/6 15:07
 */
public class FindMaximumXORTest {

    private static final int[] ONE_BITS = new int[] {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824};

    @Test
    public void test() {
        System.out.println(findMaximumXOR(new int[] {14,70,53,83,49,91,36,80,92,51,66,70}));
    }

    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        Node root = new Node(31);
        for (int num : nums) {
            Node cur = root;
            for (int i = 30; i >= 0; i--) {
                if ((num & ONE_BITS[i]) != 0) {
                    cur.one = cur.one == null ? new Node(i, num) : cur.one;
                    cur = cur.one;
                } else {
                    cur.zero = cur.zero == null ? new Node(i, num) : cur.zero;
                    cur = cur.zero;
                }
            }
        }
        while (root.zero == null || root.one == null) {
            root = root.zero != null ? root.zero : root.one;
        }
        return dfs(root.zero, root.one);
    }

    private int dfs(Node a, Node b) {
        if (a == null || b == null) {
            return 0;
        }
        if (a.pos == 0) {
            return a.val ^ b.val;
        }
        if ((a.zero != null && b.one != null) || (a.one != null && b.zero != null)) {
            return Math.max(dfs(a.zero, b.one), dfs(a.one, b.zero));
        } else {
            return Math.max(dfs(a.zero, b.zero), dfs(a.one, b.one));
        }
    }

    private static class Node {
        int pos;
        int val;
        Node zero;
        Node one;

        public Node(int pos) {
            this.pos = pos;
        }

        public Node(int pos, int val) {
            this.pos = pos;
            this.val = val;
        }
    }

    public int findMaximumXOR2(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(nums[i] ^ nums[j], max);
            }
        }
        return max;
    }
}
