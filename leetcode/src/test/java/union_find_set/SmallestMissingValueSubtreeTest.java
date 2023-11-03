package union_find_set;

import org.junit.Test;

import java.util.*;


/**
 * <a href="https://leetcode.cn/problems/smallest-missing-genetic-value-in-each-subtree/">2003. 每棵子树内缺失的最小基因值</a>
 * @author xuzhou
 * @since 2023/11/3 16:31
 */
public class SmallestMissingValueSubtreeTest {

    @Test
    public void test() {
        int[] res = smallestMissingValueSubtree(new int[]{-1, 0, 0, 2}, new int[]{1, 2, 3, 4});
        System.out.println(Arrays.toString(res));
    }

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = nums.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, nums[i], parents[i]);
        }
        for (int i = 1; i < n; i++) {
            nodes[parents[i]].child.add(nodes[i]);
        }
        UnionFind unionFind = new UnionFind(nodes);
        int[] res = new int[n];
        dfs(nodes[0], unionFind, res);
        return res;
    }

    private void dfs(Node node, UnionFind unionFind, int[] res) {
        if (node.child.isEmpty()) {
            res[node.index] = node.val == 1 ? 2 : 1;
        }
        for (Node child : node.child) {
            dfs(child, unionFind, res);
        }
        for (Node child : node.child) {
            unionFind.union(child.val, node.val);
        }
        for (int i = 1; i <= res.length + 1; i++) {
            if (!unionFind.isSameSet(node.val, i)) {
                res[node.index] = i;
                break;
            }
        }
    }

    private static class UnionFind {
        Map<Integer, Node> nodes;
        Map<Node, Node> parents;
        Map<Node, Integer> size;

        public UnionFind(Node[] arr) {
            this.nodes = new HashMap<>();
            this.parents = new HashMap<>();
            this.size = new HashMap<>();
            for (Node node : arr) {
                nodes.put(node.val, node);
                parents.put(node, node);
                size.put(node, 1);
            }
        }

        private Node find(Node node) {
            if (parents.get(node) == node) {
                return node;
            }
            parents.put(node, find(parents.get(node)));
            return parents.get(node);
        }

        public boolean isSameSet(int a, int b) {
            if (a == b) {
                return true;
            }
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return find(nodes.get(a)) == find(nodes.get(b));
        }

        public void union(int a, int b) {
            Node fatherA = find(nodes.get(a));
            Node fatherB = find(nodes.get(b));
            if (fatherA != fatherB) {
                Integer aSize = size.get(fatherA);
                Integer bSize = size.get(fatherB);
                if (aSize >= bSize) {
                    parents.put(fatherB, fatherA);
                    size.put(fatherA, aSize + bSize);
                    size.remove(fatherB);
                } else {
                    parents.put(fatherA, fatherB);
                    size.put(fatherB, aSize + bSize);
                    size.remove(fatherA);
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int index;
        int val;
        int parent;
        List<Node> child;
        public Node(int index, int val, int parent) {
            this.index = index;
            this.val = val;
            this.parent = parent;
            child = new ArrayList<>();
        }

        @Override
        public int compareTo(Node other) {
            return other.parent - this.parent;
        }
    }
}
