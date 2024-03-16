package data_structure.tree.binarysearch;

import org.junit.Test;
import utils.TreeNode;
import utils.TreeUtils;

import java.util.*;


/**
 * <a href="https://leetcode.cn/problems/closest-nodes-queries-in-a-binary-search-tree/">2476. 二叉搜索树最近节点查询</a>
 * @author xuzhou
 * @since 2024/3/11 16:22
 */
public class ClosestNodesTest {

    @Test
    public void test() {
        TreeNode root = TreeUtils.toTreeNodeWideFirst(144,62,197,7,132,179,200,null,12,66,133,null,192,null,null,null,42,63,102,null,141,191,null,34,47,null,null,94,122,null,null,null,null,33,null,null,54,72,null,104,128,28,null,null,null,null,87,null,null,null,129);
        List<List<Integer>> lists = closestNodes(root, Arrays.asList(191));
        System.out.println(lists);
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> ans = new ArrayList<>(queries.size());
        Integer[] arr = bstToSortedArray(root);
        for (Integer query : queries) {
            ans.add(getRange(arr, query));
        }
        return ans;
    }

    private List<Integer> getRange(Integer[] arr, Integer target) {
        int n = arr.length;
        int i = binarySearch(arr, target);
        if (i == n) {
            return Arrays.asList(arr[n - 1], -1);
        }
        if (Objects.equals(arr[i], target)) {
            return Arrays.asList(target, target);
        }
        if (i == 0) {
            return Arrays.asList(-1, arr[0]);
        }
        return Arrays.asList(arr[i - 1], arr[i]);
    }

    private int binarySearch(Integer[] arr, Integer target) {
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (Objects.equals(arr[m], target)) {
                return m;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private Integer[] bstToSortedArray(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        pushLeft(root, stack);
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            pushLeft(node.right, stack);
        }
        return res.toArray(new Integer[0]);
    }

    private void pushLeft(TreeNode root, LinkedList<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public List<List<Integer>> closestNodes1(TreeNode root, List<Integer> queries) {
        // 树形结构会超时 T_T
        List<List<Integer>> ans = new ArrayList<>(queries.size());
        for (Integer query : queries) {
            ans.add(closestNodes1(root, query));
        }
        return ans;
    }

    private List<Integer> closestNodes1(TreeNode root, Integer query) {
        List<Integer> res = Arrays.asList(-1, -1);
        process(root, query, res);
        return res;
    }

    private void process(TreeNode root, Integer query, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.val == query) {
            res.set(0, query);
            res.set(1, query);
        } else if (root.val > query) {
            res.set(1, root.val);
            process(root.left, query, res);
        } else {
            res.set(0, root.val);
            process(root.right, query, res);
        }
    }
}
