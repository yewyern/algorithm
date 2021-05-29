package data_structure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import org.junit.Test;
import utils.TreeNode;
import utils.TreeUtils;

/**
 * <p>给定一个二叉树，返回它的前序遍历。
 * <p>
 * <p>示例:
 * <p>
 * <p>输入: [1,null,2,3]
 * <p>   1
 * <p>    \
 * <p>     2
 * <p>    /
 * <p>   3
 * <p>
 * <p>输出: [1,2,3]
 * <p>进阶:递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author zhou.xu
 * @date 2020/10/27 11:36
 */
public class PreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return res;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }

    public void preorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorderTraversal(root.left, res);
        preorderTraversal(root.right, res);
    }

    @Test
    public void test() {
        TreeNode node = TreeUtils.toTreeNodeWideFirst(1, 4, 2, 3);
        System.out.println("node = " + node);
        List<Integer> list = preorderTraversal(node);
        System.out.println(list);
    }
}
