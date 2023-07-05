package data_structure.tree;

import org.junit.Test;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/">102. 二叉树的层序遍历</a>
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 * <p>
 * 输入：root = []
 * 输出：[]
 *
 * @author xuzhou
 * @since 2023/6/30 16:58
 */
public class LevelOrderTraversalTest {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            List<Integer> values = new ArrayList<>();
            List<TreeNode> children = new ArrayList<>();
            for (TreeNode node : nodes) {
                values.add(node.val);
                if (node.left != null) {
                    children.add(node.left);
                }
                if (node.right != null) {
                    children.add(node.right);
                }
            }
            res.add(values);
            nodes = children;
        }
        return res;
    }

    public List<List<Integer>> levelOrderComparison(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            List<Integer> values = new ArrayList<>();
            List<TreeNode> children = new ArrayList<>();
            for (TreeNode node : nodes) {
                values.add(node.val);
                if (node.left != null) {
                    children.add(node.left);
                }
                if (node.right != null) {
                    children.add(node.right);
                }
            }
            res.add(values);
            nodes = children;
        }
        return res;
    }

    public void process(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        List<Integer> l = res.size() > level ? res.get(level) : new ArrayList<>();
        l.add(root.val);
        process(root.left, res, level + 1);
        process(root.right, res, level + 1);
    }

    @Test
    public void test() {

    }
}
