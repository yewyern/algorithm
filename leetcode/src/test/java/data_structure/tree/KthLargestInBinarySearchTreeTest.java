package data_structure.tree;

import org.junit.Test;
import utils.TreeNode;
import utils.TreeUtils;

import java.awt.*;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/description/">剑指 Offer 54. 二叉搜索树的第k大节点</a>
 * <p>给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: root = [3,1,4,null,2], k = 1
 * <p>   3
 * <p>  / \
 * <p> 1   4
 * <p>  \
 * <p>   2
 * <p>输出: 4
 * <p>示例 2:
 * <p>
 * <p>输入: root = [5,3,6,2,4,null,null,1], k = 3
 * <p>       5
 * <p>      / \
 * <p>     3   6
 * <p>    / \
 * <p>   2   4
 * <p>  /
 * <p> 1
 * <p>输出: 4
 * <p>
 * <p>限制：
 * <p>
 * <p>1 ≤ k ≤ 二叉搜索树元素个数
 *
 * @author xuzhou
 * @since 2023/7/12 18:00
 */
public class KthLargestInBinarySearchTreeTest {

    @Test
    public void test() {
        TreeNode treeNode = TreeUtils.toTreeNodeWideFirst(3, 1, 4, null, 2);
        System.out.println(kthLargest(treeNode, 1));
        TreeNode treeNode2 = TreeUtils.toTreeNodeWideFirst(5, 3, 6, 2, 4, null, null, 1);
        System.out.println(kthLargest(treeNode2, 3));
    }

    public int kthLargest(TreeNode root, int k) {
        // 循环代替递归写法，破环树结构
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        for (int i = 0; i < k;) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node);
                stack.push(node.right);
                node.right = null;
            } else {
                res = node.val;
                i++;
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return res;
    }

    public int kthLargest1(TreeNode root, int k) {
        return process(root, k, new Point(0, 0)).y;
    }

    private Point process(TreeNode root, int k, Point p) {
        if (root == null || p.x >= k) {
            return p;
        }
        process(root.right, k, p);
        if (p.x >= k) {
            return p;
        }
        p.y = root.val;
        p.x++;
        process(root.left, k, p);
        return p;
    }

    public int kthLargest2(TreeNode root, int k) {
        int[] ans = new int[k];
        process(root, k, 0, ans);
        return ans[k - 1];
    }

    private int process(TreeNode root, int k, int i, int[] nums) {
        if (root == null || i >= k) {
            return i;
        }
        i = process(root.right, k, i, nums);
        if (i >= k) {
            return i;
        }
        nums[i++] = root.val;
        return process(root.left, k, i, nums);
    }
}
