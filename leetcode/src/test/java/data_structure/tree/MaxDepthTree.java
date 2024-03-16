package data_structure.tree;

import utils.Pair;
import utils.TreeNode;
import utils.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 55 - I. 二叉树的深度</a>
 * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/">104. 二叉树的最大深度</a>
 * <p>给定一个二叉树，找出其最大深度。
 * <p>
 * <p>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * <p>说明: 叶子节点是指没有子节点的节点。
 * <p>
 * <p>示例：
 * <p>给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * <p>    3
 * <p>   / \
 * <p>  9  20
 * <p>    /  \
 * <p>   15   7
 * <p>返回它的最大深度 3 。
 */
public class MaxDepthTree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepthLoop(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Pair<TreeNode, Integer>> list = new LinkedList<>();
        list.add(new Pair<>(root, 1));
        int len = 1;
        while (!list.isEmpty()) {
            Pair<TreeNode, Integer> curr = list.poll();
            TreeNode p = curr.getKey();
            len = curr.getValue();
            if (p.left != null) {
                list.add(new Pair<>(p.left, len + 1));
            }
            if (p.right != null) {
                list.add(new Pair<>(p.right, len + 1));
            }
        }
        return len;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = TreeUtils.toTreeNodeWideFirst(1, 2, 2, 3, 4, 4, 3);
        TreeNode treeNode2 = TreeUtils.toTreeNodeWideFirst(1, 2, 2, null, 3, null, 3);
        TreeNode treeNode3 = TreeUtils.toTreeNodeWideFirst(9, -42, -42, null, 76, 76, null, null, 13, null, 13);
        MaxDepthTree maxDepthTree = new MaxDepthTree();
        System.out.println("maxDepthTree.maxDepth(treeNode1) = " + maxDepthTree.maxDepth(treeNode1));
        System.out.println("maxDepthTree.maxDepth(treeNode2) = " + maxDepthTree.maxDepth(treeNode2));
        System.out.println("maxDepthTree.maxDepth(treeNode3) = " + maxDepthTree.maxDepth(treeNode3));
        System.out.println("maxDepthTree.maxDepthLoop(treeNode1) = " + maxDepthTree.maxDepthLoop(treeNode1));
        System.out.println("maxDepthTree.maxDepthLoop(treeNode2) = " + maxDepthTree.maxDepthLoop(treeNode2));
        System.out.println("maxDepthTree.maxDepthLoop(treeNode3) = " + maxDepthTree.maxDepthLoop(treeNode3));
    }
}