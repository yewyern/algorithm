package data_structure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import utils.TreeNode;
import utils.TreeUtils;

/**
 * <p>给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * <p>例如：
 * <p>给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * <p>    3
 * <p>   / \
 * <p>  9  20
 * <p>    /  \
 * <p>   15   7
 * <p>返回其自底向上的层次遍历为：
 * <p>
 * <p>[
 * <p>  [15,7],
 * <p>  [9,20],
 * <p>  [3]
 * <p>]
 */
public class LevelOrderBottom {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        levelOrderBottom(res, Collections.singletonList(root));
        return res;
    }

    public void levelOrderBottom(List<List<Integer>> res, List<TreeNode> nodes) {
        List<Integer> list = new ArrayList<>();
        List<TreeNode> childNodes = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node != null) {
                list.add(node.val);
                childNodes.add(node.left);
                childNodes.add(node.right);
            }
        }
        if (list.size() > 0) {
            res.add(0, list);
        }
        if (childNodes.size() > 0) {
            levelOrderBottom(res, childNodes);
        }
    }

    public List<List<Integer>> levelOrderBottomLoop(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.poll();
                temp.add(Objects.requireNonNull(p).val);
                if (p.left != null) {
                    queue.add(p.left);
                }
                if (p.right != null) {
                    queue.add(p.right);
                }
            }
            res.add(0, temp);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = TreeUtils.toTreeNodeWideFirst(1, 2, 2, 3, 4, 4, 3);
        TreeNode treeNode2 = TreeUtils.toTreeNodeWideFirst(1, 2, 2, null, 3, null, 3);
        TreeNode treeNode3 = TreeUtils.toTreeNodeWideFirst(9, -42, -42, null, 76, 76, null, null, 13, null, 13);
        LevelOrderBottom levelOrderBottom = new LevelOrderBottom();
        System.out
            .println("levelOrderBottom.levelOrderBottom(treeNode1) = " + levelOrderBottom.levelOrderBottom(treeNode1));
        System.out
            .println("levelOrderBottom.levelOrderBottom(treeNode2) = " + levelOrderBottom.levelOrderBottom(treeNode2));
        System.out
            .println("levelOrderBottom.levelOrderBottom(treeNode3) = " + levelOrderBottom.levelOrderBottom(treeNode3));
        System.out
            .println("levelOrderBottom.levelOrderBottomLoop(treeNode1) = " + levelOrderBottom
                .levelOrderBottomLoop(treeNode1));
        System.out
            .println("levelOrderBottom.levelOrderBottomLoop(treeNode2) = " + levelOrderBottom
                .levelOrderBottomLoop(treeNode2));
        System.out
            .println("levelOrderBottom.levelOrderBottomLoop(treeNode3) = " + levelOrderBottom
                .levelOrderBottomLoop(treeNode3));
    }
}