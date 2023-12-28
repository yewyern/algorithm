package data_structure.tree.binarysearch;

import utils.TreeNode;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst">230. 二叉搜索树中第K小的元素</a>
 * @author xuzhou
 * @since 2023/12/28 18:03
 */
public class KthSmallest {
    int n;
    TreeNode target;

    public int kthSmallest(TreeNode root, int k) {
        target = root;
        n = k;
        process(root);
        return target.val;
    }

    private void process(TreeNode node) {
        if (node == null) {
            return;
        }
        process(node.left);
        if (n <= 0) {
            return;
        }
        target = node;
        n--;
        process(node.right);
    }

    public int kthSmallest2(TreeNode root, int k) {
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
