package data_structure.tree;

import java.util.LinkedList;
import java.util.Queue;
import utils.TreeNode;
import utils.TreeUtils;

/**
 * <p>给定一个二叉树，找出其最小深度。
 * <p>
 * <p>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * <p>说明: 叶子节点是指没有子节点的节点。
 * <p>
 * <p>示例:
 * <p>
 * <p>给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * <p>    3
 * <p>   / \
 * <p>  9  20
 * <p>    /  \
 * <p>   15   7
 * <p>返回它的最小深度  2.
 */
public class MinDepthTree {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public int minDepthLoop(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.poll();
                if (p.left == null && p.right == null) {
                    return depth;
                }
                if (p.left != null) {
                    queue.add(p.left);
                }
                if (p.right != null) {
                    queue.add(p.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode tree1 = TreeUtils.toTreeNodeWideFirst(3, 9, 20, null, null, 15, 7, 10, 11);
        TreeNode tree2 = TreeUtils.toTreeNodeWideFirst(3);
        TreeNode tree3 = TreeUtils.toTreeNodeWideFirst();
        MinDepthTree a = new MinDepthTree();
        System.out.println("tree1 = " + a.minDepth(tree1));
        System.out.println("tree1 = " + a.minDepthLoop(tree1));
        System.out.println("tree2 = " + tree2);
        System.out.println("tree2 = " + a.minDepth(tree2));
        System.out.println("tree2 = " + a.minDepthLoop(tree2));
        System.out.println("tree3 = " + tree3);
        System.out.println("tree3 = " + a.minDepth(tree3));
        System.out.println("tree3 = " + a.minDepthLoop(tree3));
    }
}