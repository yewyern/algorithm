package data_structure.tree.binarysearch;

import utils.TreeNode;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/binary-search-tree-iterator">173. 二叉搜索树迭代器</a>
 * @author xuzhou
 * @since 2023/12/27 15:49
 */
public class BSTIterator {

    LinkedList<TreeNode> stack = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        push(root);
    }

    public int next() {
        TreeNode node = stack.pop();
        push(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void push(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

}
