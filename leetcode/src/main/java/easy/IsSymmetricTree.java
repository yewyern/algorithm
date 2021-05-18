package easy;

import java.util.LinkedList;
import java.util.Queue;
import utils.TreeNode;
import utils.TreeUtils;

/**
 * <p>给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * <p>例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * <p>    1
 * <p>   / \
 * <p>  2   2
 * <p> / \ / \
 * <p>3  4 4  3
 * <p>但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * <p>    1
 * <p>   / \
 * <p>  2   2
 * <p>   \   \
 * <p>   3    3
 */
class IsSymmetricTree {

    public boolean isSymmetricLoop(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root.left);
        list.add(root.right);
        while (list.size() > 1) {
            TreeNode p = list.poll();
            TreeNode q = list.poll();
            if (p == null && q == null) {
            } else if (p == null || q == null) {
                return false;
            } else if (p.val != q.val) {
                return false;
            } else {
                list.add(p.left);
                list.add(q.right);
                list.add(p.right);
                list.add(q.left);
            }
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    /**
     * 递归解法1
     *
     * @param node1
     * @param node2
     * @return
     */
    public boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 != null && node2 != null && node1.val == node2.val) {
            return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
        }
        return false;
    }

    /**
     * 递归解法2
     *
     * @param nodes
     * @return
     */
    public boolean isSymmetric2(TreeNode... nodes) {
        if (nodes != null && nodes.length > 0) {
            boolean isAllNull = true;
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] != null) {
                    isAllNull = false;
                }
            }
            if (isAllNull) {
                return true;
            }
            for (int i = 0; i < nodes.length / 2; i++) {
                if (!TreeUtils.isSameNode(nodes[i], nodes[nodes.length - i - 1])) {
                    return false;
                }
            }
            TreeNode[] treeNodes = new TreeNode[nodes.length * 2];
            for (int i = 0; i < nodes.length; i++) {
                treeNodes[i * 2] = nodes[i].left;
                treeNodes[i * 2 + 1] = nodes[i].right;
            }
            return isSymmetric2(treeNodes);
        }
        return true;
    }

    public static void main(String[] args) {
        IsSymmetricTree isSameTree = new IsSymmetricTree();
        TreeNode treeNode1 = TreeUtils.toTreeNodeWideFirst(1, 2, 2, 3, 4, 4, 3);
        TreeNode treeNode2 = TreeUtils.toTreeNodeWideFirst(1, 2, 2, null, 3, null, 3);
        TreeNode treeNode3 = TreeUtils.toTreeNodeWideFirst(9, -42, -42, null, 76, 76, null, null, 13, null, 13);
//        System.out.println("isSameTree1 true = " + isSameTree.isSymmetricLoop(treeNode1));
//        System.out.println("isSameTree2 false = " + isSameTree.isSymmetricLoop(treeNode2));
        System.out.println("isSameTree3 false = " + isSameTree.isSymmetricLoop(treeNode3));
        System.out.println("isSameTree3 false = " + isSameTree.isSymmetric(treeNode3));
    }

}