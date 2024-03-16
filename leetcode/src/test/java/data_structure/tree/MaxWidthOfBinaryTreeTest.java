package data_structure.tree;

import org.junit.Test;
import utils.TreeNode;
import utils.TreeUtils;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/maximum-width-of-binary-tree/?envType=study-plan-v2&envId=bytedance-2023-spring-sprint">662. 二叉树最大宽度</a>
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * <p>
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * <p>
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * <p>
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 *
 * @author zhou.xu
 * @since 2023/7/2 15:31
 */
public class MaxWidthOfBinaryTreeTest {

    public int widthOfBinaryTree(TreeNode root) {
        // 按层遍历
        int max = 1;
        int level = 0;
        int l = 0, r = 0;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, root));
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            if (node.treeNode.left != null) {
                queue.addLast(new Node(node.level + 1, node.index * 2, node.treeNode.left));
            }
            if (node.treeNode.right != null) {
                queue.addLast(new Node(node.level + 1, node.index * 2 + 1, node.treeNode.right));
            }
            if (node.level != level) {
                level = node.level;
                max = Math.max(max, r - l + 1);
                l = node.index;
            } else {
                r = node.index;
            }
        }
        return Math.max(max, r - l + 1);
    }

    private static class Node {
        final int level;
        final int index;
        final TreeNode treeNode;

        public Node(int level, int index, TreeNode treeNode) {
            this.level = level;
            this.index = index;
            this.treeNode = treeNode;
        }
    }

    @Test
    public void test() {
        TreeNode root = TreeUtils.toTreeNodeWideFirst(1, 3, 2, 5, 3, null, 9);
        System.out.println(widthOfBinaryTree(root));
    }
}
