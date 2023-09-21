package data_structure.tree;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 32 - III. 从上到下打印二叉树 III</a>
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * <p>
 * <p>
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 节点总数 <= 1000
 *
 * @author zhou.xu
 * @since 2023/7/5 22:46
 */
public class LevelOrderTraversalAlternatingTest {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 0));
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            if (node.val == null) {
                continue;
            }
            if (res.size() <= node.level) {
                res.add(new ArrayList<>());
            }
            res.get(node.level).add(node.val.val);
            queue.addLast(new Node(node.val.left, node.level + 1));
            queue.addLast(new Node(node.val.right, node.level + 1));
        }
        for (int i = 0; i < res.size(); i++) {
            if (i % 2 == 1) {
                res.set(i, reverse(res.get(i)));
            }
        }
        return res;
    }

    private static class Node {
        final TreeNode val;
        final int level;

        public Node(TreeNode val, int level) {
            this.val = val;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrderComparison(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        process(root, res, 0);
        for (int i = 0; i < res.size(); i++) {
            if (i % 2 == 1) {
                res.set(i, reverse(res.get(i)));
            }
        }
        return res;
    }

    private void process(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        List<Integer> l = res.get(level);
        l.add(root.val);
        process(root.left, res, level + 1);
        process(root.right, res, level + 1);
    }

    private List<Integer> reverse(List<Integer> l) {
        List<Integer> res = new ArrayList<>();
        for (int i = l.size() - 1; i >= 0; i--) {
            res.add(l.get(i));
        }
        return res;
    }
}
