package data_structure.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import org.junit.Test;
import utils.TreeNode;
import utils.TreeUtils;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal">145. 二叉树的后序遍历</a>
 * @author zhou.xu
 * @since 2022/11/3 21:36
 */
public class BinaryTreePostorderTraversalTest {

    public List<Integer> postorderTraversalIterator(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderTraversal(root, res);
        return res;
    }

    public void postorderTraversal(TreeNode root, List<Integer> res) {
        // 递归遍历
        if (root != null) {
            postorderTraversal(root.left, res);
            postorderTraversal(root.right, res);
            res.add(root.val);
        }
    }

    public List<Integer> postorderTraversalMy(TreeNode root) {
        // 迭代遍历-会破坏树形结构，右左中遍历
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {
                res.add(cur.val);
                continue;
            }
            stack.push(cur);
            if (cur.right != null) {
                stack.push(cur.right);
                cur.right = null;
            }
            if (cur.left != null) {
                stack.push(cur.left);
                cur.left = null;
            }
        }
        return res;
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        // 迭代遍历-官方答案
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(postorderTraversal(TreeUtils.toTreeNodeWideFirst(1, null, 2, 3)));
        System.out.println(postorderTraversal(TreeUtils.toTreeNodeWideFirst(1)));
        System.out.println(postorderTraversal(TreeUtils.toTreeNodeWideFirst()));
    }
}
