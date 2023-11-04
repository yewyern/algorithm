package union_find_set;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <a href="https://leetcode.cn/problems/smallest-missing-genetic-value-in-each-subtree/">2003. 每棵子树内缺失的最小基因值</a>
 *
 * @author xuzhou
 * @since 2023/11/3 16:31
 */
public class SmallestMissingValueSubtreeTest {

    @Test
    public void test() {
        int[] res = smallestMissingValueSubtree(new int[]{-1, 0, 0, 0, 2}, new int[]{6, 4, 3, 2, 1});
        System.out.println(Arrays.toString(res));
    }

    private int[] parents;
    private int[] size;

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = nums.length;
        if (!containsOne(nums)) {
            int[] res = new int[n];
            Arrays.fill(res, 1);
            return res;
        }
        initUnionFind(nums);
        Node root = toTree(parents, nums);
        int[] res = new int[n];
        dfs(root, res);
        return res;
    }

    private void dfs(Node node, int[] res) {
        if (node.child.isEmpty()) {
            res[node.index] = node.val == 1 ? 2 : 1;
            return;
        }
        int max = 1;
        for (Node child : node.child) {
            dfs(child, res);
            max = Math.max(max, res[child.index]);
        }
        for (Node child : node.child) {
            union(child.val, node.val);
        }
        if (max == 1 && node.val != 1) {
            res[node.index] = 1;
        } else {
            for (int i = max; i <= res.length + 1; i++) {
                if (!isSameSet(node.val, i)) {
                    res[node.index] = i;
                    break;
                }
            }
        }
    }

    private void initUnionFind(int[] nums) {
        parents = new int[100002];
        size = new int[100002];
        for (int num : nums) {
            parents[num] = num;
            size[num] = 1;
        }
    }

    private int findFather(int i) {
        if (parents[i] != i) {
            int father = findFather(parents[i]);
            parents[i] = father;
            return father;
        }
        return i;
    }

    private boolean isSameSet(int i, int j) {
        if (i == j) {
            return true;
        }
        if (size[i] == 0 || size[j] == 0) {
            return false;
        }
        return findFather(i) == findFather(j);
    }

    private void union(int i, int j) {
        int fi = findFather(i);
        int fj = findFather(j);
        if (fi != fj) {
            if (size[fi] > size[fj]) {
                parents[fj] = fi;
                size[fi] += size[fj];
            } else {
                parents[fi] = fj;
                size[fj] += size[fi];
            }
        }
    }

    private boolean containsOne(int[] nums) {
        for (int num : nums) {
            if (num == 1) {
                return true;
            }
        }
        return false;
    }

    private Node toTree(int[] parents, int[] nums) {
        int n = nums.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, nums[i], parents[i]);
        }
        for (int i = 1; i < n; i++) {
            nodes[parents[i]].child.add(nodes[i]);
        }
        return nodes[0];
    }

    private static class Node {
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
    }
}
