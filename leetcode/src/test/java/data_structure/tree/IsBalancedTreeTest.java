package data_structure.tree;

import org.junit.Test;
import utils.TreeNode;
import utils.TreeUtils;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/balanced-binary-tree/">110. 平衡二叉树</a>
 * <a href="https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 55 - II. 平衡二叉树</a>
 * <p>给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * <p>本题中，一棵高度平衡二叉树定义为：
 * <p>
 * <p>一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * <p>    3
 * <p>   / \
 * <p>  9  20
 * <p>    /  \
 * <p>   15   7
 * <p>返回 true 。
 * <p>
 * <p>示例 2:
 * <p>
 * <p>给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * <p>       1
 * <p>      / \
 * <p>     2   2
 * <p>    / \
 * <p>   3   3
 * <p>  / \
 * <p> 4   4
 * <p>返回 false 。
 *
 * @author xzer
 */
public class IsBalancedTreeTest {

    public boolean isBalanced(TreeNode root) {
        // 循环写法
        if (root == null) {
            return true;
        }
        Set<TreeNode> visited = new HashSet<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(null, 0);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null && !visited.contains(node.left)) {
                stack.push(node);
                stack.push(node.left);
            } else if (node.right != null && !visited.contains(node.right)) {
                stack.push(node);
                stack.push(node.right);
            } else {
                Integer leftDepth = map.get(node.left);
                Integer rightDepth = map.get(node.right);
                if (Math.abs(leftDepth - rightDepth) > 1) {
                    return false;
                }
                visited.add(node);
                map.put(node, Math.max(leftDepth, rightDepth) + 1);
            }
        }
        return true;
    }

    public boolean isBalanced2(TreeNode root) {
        return process(root)[0] < 2;
    }

    public int[] process(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = process(root.left);
        if (left[0] > 1) {
            return left;
        }
        int[] right = process(root.right);
        if (right[0] > 1) {
            return right;
        }
        if (left[1] >= right[1]) {
            return new int[]{left[1] - right[1], left[1] + 1};
        } else {
            return new int[]{right[1] - left[1], right[1] + 1};
        }
    }

    @Test
    public void test() {
        TreeNode tree1 = TreeUtils.toTreeNodeWideFirst(3, 9, 20, null, null, 15, 7);
        TreeNode tree2 = TreeUtils.toTreeNodeWideFirst(1, 2, 2, 3, 3, null, null, 4, 4);
        assert isBalanced(tree1);
        assert !isBalanced(tree2);
    }

}