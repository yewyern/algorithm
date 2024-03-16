package data_structure.tree;

import java.util.LinkedList;
import utils.TreeNode;
import utils.TreeUtils;

/**
 * <a href="https://leetcode.cn/problems/same-tree">100. 相同的树</a>
 * <p>给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * <p>如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入:       1         1
 * <p>          / \       / \
 * <p>         2   3     2   3
 * <p>
 * <p>        [1,2,3],   [1,2,3]
 * <p>
 * <p>输出: true
 * <p>示例 2:
 * <p>
 * <p>输入:      1          1
 * <p>          /           \
 * <p>         2             2
 * <p>
 * <p>        [1,2],     [1,null,2]
 * <p>
 * <p>输出: false
 * <p>示例 3:
 * <p>
 * <p>输入:       1         1
 * <p>          / \       / \
 * <p>         2   1     1   2
 * <p>
 * <p>        [1,2,1],   [1,1,2]
 * <p>
 * <p>输出: false
 */
public class IsSameTree {

    public static void main(String[] args) {
        IsSameTree isSameTree = new IsSameTree();
        TreeNode treeNode1 = TreeUtils.toTreeNodeDeepFirst(1, 2, 3);
        TreeNode treeNode2 = TreeUtils.toTreeNodeDeepFirst(1, 2, 3);
        System.out.println("isSameTree = " + isSameTree.isSameTree(treeNode1, treeNode2));
        System.out.println("isSameTree = " + isSameTree.isSameTreeLoop(treeNode1, treeNode2));
        TreeNode treeNode3 = TreeUtils.toTreeNodeDeepFirst(1, 2);
        TreeNode treeNode4 = TreeUtils.toTreeNodeDeepFirst(1, null, 2);
        System.out.println("isSameTree = " + isSameTree.isSameTree(treeNode3, treeNode4));
        System.out.println("isSameTree = " + isSameTree.isSameTreeLoop(treeNode3, treeNode4));
        TreeNode treeNode5 = TreeUtils.toTreeNodeDeepFirst(1, 2, 1);
        TreeNode treeNode6 = TreeUtils.toTreeNodeDeepFirst(1, 1, 2);
        System.out.println("isSameTree = " + isSameTree.isSameTree(treeNode5, treeNode6));
        System.out.println("isSameTree = " + isSameTree.isSameTreeLoop(treeNode5, treeNode6));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    public boolean isSameTreeLoop(TreeNode p, TreeNode q) {
        LinkedList<TreeNode> listP = new LinkedList<>();
        LinkedList<TreeNode> listQ = new LinkedList<>();
        listP.addLast(p);
        listQ.addLast(q);
        while (!listP.isEmpty()) {
            TreeNode p1 = listP.poll();
            TreeNode q1 = listQ.poll();
            if (isSameNode(p1, q1)) {
                if (p1 != null) {
                    listP.addLast(p1.left);
                    listP.addLast(p1.right);
                    listQ.addLast(q1.left);
                    listQ.addLast(q1.right);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isSameNode(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return true;
        }
        return false;
    }
}

