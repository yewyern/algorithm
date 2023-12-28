package data_structure.tree.binarysearch;

import utils.TreeNode;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst">230. 二叉搜索树中第K小的元素</a>
 * @author xuzhou
 * @since 2023/12/28 18:03
 */
public class KthSmallest {

    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        push(stack, root);
        TreeNode node = root;
        for (int i = 0; i < k; i++) {
            node = stack.pop();
            push(stack, node.right);
        }
        return node.val;
    }

    private void push(LinkedList<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
